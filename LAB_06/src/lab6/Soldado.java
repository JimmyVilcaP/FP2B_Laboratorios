package lab6;

public class Soldado {
	private String nombre;
	private int vida, fila, columna, ejercito;
	
	public void setNombre(String n) {
		nombre=n;
	}
	public String getNombre() {
		return nombre;
	}
	
	public void setVida(int v) {
		vida=v;
	}
	public int getVida() {
		return vida;
	}
	
	public void setFila(int f) {
		fila=f;
	}
	public int getFila() {
		return fila;
	}
	
	public void setColumna(int c) {
		columna=c;
	}
	public int getColumna() {
		return columna;
	}
	
	public void setEjercito(int e) {
		ejercito=e;
	}
	public int getEjercito() {
		return ejercito;
	}
}