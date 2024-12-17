package lab18;
import java.util.ArrayList;
import java.util.Random;

public class Ejercito {
    private Reino reino;
    private int reinoID;
    private String nombre;
    private int id;
    private ArrayList<Soldado> misSoldados;
    private static final int MAX_SOLDADOS = 10;
    
    public Ejercito(String nombre) {
        this.nombre = nombre;
        this.misSoldados = new ArrayList<>();
    }
    // Constructor que recibe el nombre y el reino
    public Ejercito(Reino reino, String nombre, int id, int reinoId) {
        this.reino = reino;
        this.nombre = nombre;
        this.id = id;
        this.reinoID = reinoId;
        this.misSoldados = new ArrayList<>();
    }

    public void autogenerarSoldados(int cantidad) {
        Random random = new Random();
        for (int i = 0; i < cantidad && misSoldados.size() < MAX_SOLDADOS; i++) {
            String tipoSoldado = random.nextBoolean() ? (random.nextBoolean() ? "Caballero" : "Espadachin") : "Arquero"; // Aleatorio entre 3 tipos

            if (tipoSoldado.equals("Espadachin")) {
                misSoldados.add(new Espadachin(reino.getNombre(), i, id, reinoID));
            } else if (tipoSoldado.equals("Caballero")) {
                misSoldados.add(new Caballero(reino.getNombre(), i, id, reinoID));
            } else if (tipoSoldado.equals("Arquero")) {
                misSoldados.add(new Arquero(reino.getNombre(), i, id, reinoID));
            }
        }
    }
    
    // Método getReino para devolver el reino al que pertenece el ejército
    public Reino getReino() {
        return reino;
    }
    public String getReinoNombre() {
        return reino.getNombre();
    }
    public String getNombre() {
    	return nombre;
    }
    
    public ArrayList<Soldado> getMisSoldados() {
        return misSoldados;
    }

    @Override
    public String toString() {
        return "R" + reino.getNombre().charAt(0) + "E" + id;
    }
    public int getID() {
        return id;
    }
    public int getReinoID() {
        return reinoID;
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
        for (Soldado soldado : misSoldados) {
            System.out.println(soldado.toString() + " | Vida: " + soldado.getVidaActual());
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
    public void mostrarRanking() {
        ordenarPorVida();

        for (Soldado soldado : misSoldados) {
            System.out.println(soldado.toString() + " | Vida: " + soldado.getVidaActual());
        }
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

        System.out.println("Soldados ordenados por vida:");
        for (Soldado soldado : soldadosOrdenados) {
            System.out.println(soldado.toString() + " | Vida: " + soldado.getVidaActual());
        }
    }
}