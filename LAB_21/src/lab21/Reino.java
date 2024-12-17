package lab21;
import java.util.*;

public class Reino {
    private String nombre;
    private Ejercito ejercito;
    private int reinoId;

    public Reino(String nombre, int reinoId) {
        this.nombre = nombre;
        this.reinoId = reinoId;
        ejercito = new Ejercito(this, "Ejército "+reinoId, 1);
    }

    public String getNombre() {
        return nombre;
    }
    public int getID() {
    	return reinoId;
    }

    public Ejercito getEjercito() {
        return ejercito;
    }
    
    @Override
    public String toString() {
        return "Reino: " + nombre + " ejércitos.";
    }
}
