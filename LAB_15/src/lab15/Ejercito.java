package lab15;
import java.util.*;

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
        this.reinoID = reinoId;
        this.id = id;
        this.misSoldados = new ArrayList<>();
    }

    public void autogenerarSoldados(int cantidad) {
        Random random = new Random();
        for (int i = 0; i < cantidad && misSoldados.size() < MAX_SOLDADOS; i++) {
        	String n="Soldado"+i;
        	int ataque = random.nextInt(5) + 1;
            int defensa = random.nextInt(5) + 1;
            int vida = random.nextInt(5) + 1;
            int velocidad = random.nextInt(5) + 1;
            Soldado nuevoSoldado = new Soldado(n, ataque, defensa, vida, i, true, id, reinoID);
            misSoldados.add(nuevoSoldado);
        }
    }

    // Método getReino para devolver el reino al que pertenece el ejército
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
        return "R" + reino.getNombre().charAt(0) + "E" + id;
    }
    public int getReinoID() {
        return reinoID;
    }
}

