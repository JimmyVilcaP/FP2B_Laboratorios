package lab21;
import java.util.*;

public class Ejercito {
    private Reino reino;
    private String nombre;
    private int id;
    private ArrayList<Soldado> misSoldados;
    private static final int MAX_SOLDADOS = 10;
    private Random random = new Random();

    public Ejercito(Reino reino, String nombre, int id) {
        this.reino = reino;
        this.nombre = nombre;
        this.id = id;
        this.misSoldados = new ArrayList<>();
        autogenerarSoldados((int)(Math.random()*10+1));
    }

    public void autogenerarSoldados(int cantidad) {
        Random random = new Random();
        for (int i = 0; i < cantidad && misSoldados.size() < MAX_SOLDADOS; i++) {
            String tipoSoldado = random.nextBoolean() ? (random.nextBoolean() ? "Caballero" : "Espadachin") : 
                                                        (random.nextBoolean() ? "Arquero" : "Lancero");
            
            // Creamos el soldado según su tipo
            if (tipoSoldado.equals("Espadachin")) {
                misSoldados.add(new Espadachin(i, id));
            } else if (tipoSoldado.equals("Caballero")) {
                misSoldados.add(new Caballero(i, id)); 
            } else if (tipoSoldado.equals("Arquero")) {
                misSoldados.add(new Arquero(i, id));
            } else if (tipoSoldado.equals("Lancero")) {
                misSoldados.add(new Lancero(i, id));
            }
        }
    }
    
    public String getNombre() {
    	return nombre;
    }
    
    public ArrayList<Soldado> getMisSoldados() {
        return misSoldados;
    }

    @Override
    public String toString() {
        return "R" + this.getNombre().charAt(0) + "E" + id;
    }
    public int getID() {
        return id;
    }
    
    public Soldado obtenerSoldadoConMayorVida() {
        Soldado maxVidaSoldado = null;
        for (Soldado soldado : misSoldados) {
            if (maxVidaSoldado == null || soldado.getVidaActual() > maxVidaSoldado.getVidaActual()) {
                maxVidaSoldado = soldado;
            }
        }
        return maxVidaSoldado;
    }
    public void mostrarDatosSoldados() {
    	System.out.printf("%-10s | %-11s | %-7s | %-7s | %-7s%n",
                "REFERENCIA",
                "NOMBRE",
                "VIDA",
                "ATAQUE",
                "DEFENSA");
        System.out.println("-------------------------------------------------------");

    	for (int i = 0; i < misSoldados.size(); i++) {
            System.out.printf("%-10s | %-11s | %-7s | %-7s | %-7d%n",
                    misSoldados.get(i).mostrarREF(),
                    misSoldados.get(i).getNombre(),
                    misSoldados.get(i).getVidaActual(),
            		misSoldados.get(i).getAtaque(),
            		misSoldados.get(i).getDefensa());
        }
    }
    public double obtenerPromedioVida() {
        int totalVida = 0;
        int totalSoldados = misSoldados.size();

        for (Soldado soldado : misSoldados) {
            totalVida += soldado.getVidaActual();
        }

        return totalSoldados > 0 ? (double) totalVida / totalSoldados : 0;
    }
    public int vidaTotal() {
    	int totalVida = 0;
    	
    	for (Soldado soldado : misSoldados) {
            totalVida += soldado.getVidaActual();
        }
    	
    	return totalVida;
    }
    public void mostrarRanking() {
        ordenarPorVida();
    }
    public void ordenarPorVida() {
        ArrayList<Soldado> soldadosOrdenados = new ArrayList<>(misSoldados);

        // Algoritmo de burbuja para ordenar los soldados por vida de mayor a menor
        for (int i = 0; i < soldadosOrdenados.size(); i++) {
            for (int j = 0; j < soldadosOrdenados.size() - 1 - i; j++) {
                Soldado s1 = soldadosOrdenados.get(j);
                Soldado s2 = soldadosOrdenados.get(j + 1);
                
                if (s1.getVidaActual() < s2.getVidaActual()) {
                    soldadosOrdenados.set(j, s2);
                    soldadosOrdenados.set(j + 1, s1);
                }
            }
        }

        this.mostrarDatosSoldadosOrdenados(soldadosOrdenados);
    }
    public void mostrarDatosSoldadosOrdenados(ArrayList<Soldado> soldadosOrdenados) {
        System.out.printf("%-10s | %-11s | %-7s | %-7s | %-7s%n",
                "REFERENCIA", "NOMBRE", "VIDA", "ATAQUE", "DEFENSA");
        System.out.println("-------------------------------------------------------");

		for (int i = 0; i < soldadosOrdenados.size(); i++) {
		  System.out.printf("%-10s | %-11s | %-7d | %-7d | %-7d%n",
		                    soldadosOrdenados.get(i).mostrarREF(),
		                    soldadosOrdenados.get(i).getNombre(),
		                    soldadosOrdenados.get(i).getVidaActual(),
		                    soldadosOrdenados.get(i).getAtaque(),
		                    soldadosOrdenados.get(i).getDefensa());
		}
    }

}