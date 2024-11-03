package lab14;

public class Soldado {
	private String nombre;
	private int ejercito, nivelAtaque, nivelDefensa, vidaActual, velocidad, fila, columna;
    private boolean vive;
    private Actitud actitud;
	
    public enum Actitud {
        DEFENSIVA, OFENSIVA, HUIDA
    }
	public Soldado(String nombre, int nivelAtaque, int nivelDefensa, int vidaInicial, int fila, int columna, int ejercito, boolean vive) {
		this.nombre=nombre;
		this.nivelAtaque = nivelAtaque;
        this.nivelDefensa = nivelDefensa;
        this.vidaActual = vidaInicial;
        this.vive = vive;
        this.fila = fila;
        this.columna = columna;
        this.ejercito = ejercito;
        this.actitud = Actitud.DEFENSIVA;
	}
	
    public String getNombre() {
        return nombre;
    }
}
