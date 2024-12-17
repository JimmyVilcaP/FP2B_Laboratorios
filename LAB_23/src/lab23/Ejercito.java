package lab23;
import java.util.*;

public class Ejercito {
    private Reino reino;
    private String nombre;
    private int id;
    private ArrayList<Soldado> misSoldados;
    private static final int MAX_SOLDADOS = 9;
    private Random random = new Random();
    private boolean unidadEspecialCreada = false;
    
    public Ejercito(Reino reino, String nombre, int id) {
        this.reino = reino;
        this.nombre = nombre;
        this.id = id;
        this.misSoldados = new ArrayList<>();
        autogenerarSoldados((int)(Math.random()*10+1));
    }

    public void autogenerarSoldados(int cantidad) {
        Random random = new Random();
        if (!unidadEspecialCreada) {
            String tipoEspecial = obtenerUnidadEspecialPorReino(this.reino.getNombre());
            if (tipoEspecial != null) {
                crearUnidadEspecial(tipoEspecial);
                unidadEspecialCreada = true;
            }
        }

        // Crear los soldados comunes, sin crear más de la unidad especial ya creada
        for (int i = 2; i < cantidad && misSoldados.size() < MAX_SOLDADOS; i++) {
            String tipoSoldado = random.nextBoolean() ? (random.nextBoolean() ? "Caballero" : "Espadachin") : 
                                                    (random.nextBoolean() ? "Arquero" : "Lancero");

            if (misSoldados.size() < MAX_SOLDADOS) {
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
    }
    private void crearUnidadEspecial(String tipoUnidadEspecial) {
        int idEspecial = 1;

        if (tipoUnidadEspecial.equals("EspadachinReal")) {
            misSoldados.add(new EspadachinReal(idEspecial, id));
        } else if (tipoUnidadEspecial.equals("CaballeroFranco")) {
            misSoldados.add(new CaballeroFranco(idEspecial, id));
        } else if (tipoUnidadEspecial.equals("EspadachinTeutonico")) {
            misSoldados.add(new EspadachinTeutonico(idEspecial, id));
        } else if (tipoUnidadEspecial.equals("EspadachinConquistador")) {
            misSoldados.add(new EspadachinConquistador(idEspecial, id));
        } else if (tipoUnidadEspecial.equals("CaballeroMoro")) {
            misSoldados.add(new CaballeroMoro(idEspecial, id));
        }
    }
    private String obtenerUnidadEspecialPorReino(String reino) {
        switch (reino) {
            case "Inglaterra":
                return "EspadachinReal";
            case "Francia":
                return "CaballeroFranco";
            case "Sacro Imperio":
                return "EspadachinTeutonico";
            case "Castilla Aragón":
                return "EspadachinConquistador";
            case "Moros":
                return "CaballeroMoro";
            default:
                return null;
        }
    }
    
    public Reino getReino() {
    	return reino;
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
        System.out.printf("%-10s | %-2s | %-11s | %-7s | %-7s | %-7s%n",
                    "REFERENCIA",
                    "ID",
                    "NOMBRE",
                    "VIDA",
                    "ATAQUE",
                    "DEFENSA");
        System.out.println("-------------------------------------------------------");

        for (int i = 0; i < misSoldados.size(); i++) {
            Soldado soldado = misSoldados.get(i);
            
            if (soldado instanceof CaballeroFranco || soldado instanceof EspadachinReal || 
                soldado instanceof EspadachinTeutonico || soldado instanceof EspadachinConquistador || 
                soldado instanceof CaballeroMoro) {
                continue;
            }

            System.out.printf("%-10s | %-2s | %-11s | %-7s | %-7s | %-7d%n",
                    soldado.mostrarREF(),
                    misSoldados.indexOf(soldado),
                    soldado.getNombre(),
                    soldado.getVidaActual(),
                    soldado.getAtaque(),
                    soldado.getDefensa());
        }

        for (Soldado soldado : misSoldados) {
            if (soldado instanceof CaballeroFranco || soldado instanceof EspadachinReal || 
                soldado instanceof EspadachinTeutonico || soldado instanceof EspadachinConquistador || 
                soldado instanceof CaballeroMoro) {
                System.out.println("\nUnidad Especial:");

                System.out.println("-------------------------------------------------------");
                System.out.printf("%-10s | %-2s  | %-11s | %-7s | %-7s | %-7d%n",
                        soldado.mostrarREF(),
                        misSoldados.indexOf(soldado),
                        soldado.getNombre(),
                        soldado.getVidaActual(),
                        soldado.getAtaque(),
                        soldado.getDefensa());
                break;  
            }
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