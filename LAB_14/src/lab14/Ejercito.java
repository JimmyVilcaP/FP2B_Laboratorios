package lab14;
import java.util.*;

public class Ejercito {
	private int reinoID;
	private int ID;
	private String nombre;
	private ArrayList<Soldado> soldados;
	
	public Ejercito(int reinoID, int ID, String nombre) {
		this.reinoID = reinoID;
		this.ID=ID;
		this.nombre=nombre;
		this.soldados = new ArrayList<>();
		generarSoldados();
	}
	public String getNombre() {
		return nombre;
	}
	public int getId() {
		return ID;
	}
	public ArrayList<Soldado> getSoldados() {
        return soldados;
    }
	
	public void generarSoldados() {
		int cantidad = generarAleatorio();
		String n;
		for(int i=0; i<cantidad; i++) {
			n="Soldado"+(i+1);
			soldados.add(agregarSoldado(i+1));
		}
	}
	public Soldado agregarSoldado(int id) {
		Soldado s;
		while(true) {
			String n="E"+ID+"Soldado"+id;
			int fil=(int)(Math.random()*10);
			int col=(int)(Math.random()*10);
			int ataque=(int)(Math.random()*5+1);
			int defensa=(int)(Math.random()*5+1);
			int vida=(int)(Math.random()*5+1);
			
			s = new Soldado(n, ataque, defensa, vida, fil, col, id, true);
			return s;
		}
	}
	@Override
    public String toString() {
        return "R" + reinoID + "E" + ID;	
    }
	
	
	public static int generarAleatorio() {
		return (int)(Math.random()*10+1);
	}
}
