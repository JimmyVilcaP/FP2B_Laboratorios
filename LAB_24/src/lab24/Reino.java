package lab24;

import java.io.Serializable;

public class Reino implements Serializable  {
	private static final long serialVersionUID = 2L;
    private String nombre;
    private Ejercito ejercito;
    private int reinoId;

    public Reino(String nombre, int reinoId) {
        this.nombre = nombre;
        this.reinoId = reinoId;
        ejercito = new Ejercito(this, "Ejército "+reinoId, reinoId);
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
