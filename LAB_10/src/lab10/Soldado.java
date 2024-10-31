package lab10;

public class Soldado {
    private String nombre;
    private int ejercito, nivelAtaque, nivelDefensa, vidaActual, velocidad, fila, columna;
    private boolean vive;
    private Actitud actitud;
    public static final String RESET = "\u001B[0m";
    public static final String ROJO = "\u001B[31m";
    public static final String VERDE = "\u001B[32m";
    
    public enum Actitud {
        DEFENSIVA, OFENSIVA, HUIDA
    }

    public Soldado() {
        this.vive = false;
    }

    public Soldado(String nombre, int nivelAtaque, int nivelDefensa, int vidaInicial, int fila, int columna, int ejercito, boolean vive) {
        this.nombre = nombre;
        this.nivelAtaque = nivelAtaque;
        this.nivelDefensa = nivelDefensa;
        this.vidaActual = vidaInicial;
        this.vive = vive;
        this.fila = fila;
        this.columna = columna;
        this.ejercito = ejercito;
        this.actitud = Actitud.DEFENSIVA;
    }

    // Métodos de comportamiento
    public void atacar() {
        if (!vive) return;
        actitud = Actitud.OFENSIVA;
        avanzar();
    }
    public void defender() {
        if (!vive) return;
        actitud = Actitud.DEFENSIVA;
        velocidad = 0;
    }
    public void huir() {
        if (!vive) return;
        actitud = Actitud.HUIDA;
        velocidad += 2;
    }
    public void retroceder() {
        if (!vive) return;
        if (velocidad > 0) {
            defender();
        } else {
            velocidad--;
        }
    }
    public void serAtacado(int ataque) {
        if (!vive) return;
        vidaActual -= ataque;
        if (vidaActual <= 0) {
            morir();
        }
    }
    public void avanzar() {
        velocidad++;
    }
    public void morir() {
        vive = false;
        actitud = null;
        velocidad = 0;
    }

    // Set's y Get's
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getNombre() {
        return nombre;
    }

    public void setAtaque(int nivelAtaque) {
        this.nivelAtaque = nivelAtaque;
    }
    public int getAtaque() {
        return nivelAtaque;
    }
    
    public void setDefensa(int nivelDefensa) {
        this.nivelDefensa = nivelDefensa;
    }
    public int getDefensa() {
        return nivelDefensa;
    }
    
    public void setVidaActual(int vidaActual) {
        this.vidaActual = vidaActual;
    }
    public int getVidaActual() {
        return vidaActual;
    }
    public void setFila(int fila) {
    	this.fila = fila;
    }
    public int getFila() {
    	return fila;
    }
    public void setColumna(int columna) {
    	this.columna = columna;
    }
    public int getColumna() {
    	return columna;
    }
    public int getVelocidad() {
        return velocidad;
    }
    public Actitud getActitud() {
        return actitud;
    }
    public int getEjercito() {
    	return ejercito;
    }
    public boolean estaVivo() {
        return vive;
    }

    // Método para mostrar datos
    public String toString() {
        char columnaLetra = (char) ('A' + columna);
        String estadoVive = vive 
                ? VERDE + "Sí" + RESET 
                : ROJO + "No" + RESET;
        
        return String.format(" %-10s | %-3s | %-3s | %-3s | %-3s | %-4s | %-3s |  %-4s  | %-10s",
                nombre, 
                columnaLetra, 
                (fila + 1), 
                nivelAtaque, 
                nivelDefensa, 
                vidaActual, 
                velocidad, 
                estadoVive, 
                actitud);
    }
}
