package lab17;
import java.util.*;

public class Reino {
    private String nombre;
    private ArrayList<Ejercito> ejercitos;
    private int reinoId;

    public Reino(String nombre, int reinoId) {
        this.nombre = nombre;
        this.reinoId = reinoId;
        this.ejercitos = new ArrayList<>();
        generarEjercitos();
    }

    private void generarEjercitos() {
        Random random = new Random();
        int numeroEjercitos = random.nextInt(10) + 1; // De 1 a 10 ejércitos
        for (int i = 0; i < numeroEjercitos; i++) {
            ejercitos.add(new Ejercito(this, "Ejército " + (i + 1), i, reinoId));
            ejercitos.get(i).autogenerarSoldados(random.nextInt(10) + 1); // De 1 a 10 soldados
        }
    }

    public String getNombre() {
        return nombre;
    }
    public int getID() {
    	return reinoId;
    }
    public ArrayList<Ejercito> getEjercitos() {
        return ejercitos;
    }

    @Override
    public String toString() {
        return "Reino: " + nombre + " con " + ejercitos.size() + " ejércitos.";
    }
}
