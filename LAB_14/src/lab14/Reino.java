package lab14;
import java.util.*;

public class Reino {
	private int ID;
	private String nombre;
	public ArrayList<Ejercito> ejercitos;
	
	public Reino(int ID, String nombre) {
		this.ID=ID;
		this.nombre=nombre;
        this.ejercitos = new ArrayList<>();
        generarEjercitos();
	}
	
	public String getNombre() {
		return nombre;
	}
	public int getId() {
		return ID;
	}
	public ArrayList<Ejercito> getEjercitos() {
        return ejercitos;
    }
	
	public void generarEjercitos() {
		int cantidad = generarAleatorio();
		String n;
		for(int i=0; i<cantidad; i++) {
			n="Ejercito"+(i+1);
			ejercitos.add(new Ejercito(ID, i+1, n));
		}
	}
	public static int generarAleatorio() {
		return (int)(Math.random()*10+1);
	}
}
