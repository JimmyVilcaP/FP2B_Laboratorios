package lab13;

public class Soldado {
    private String nombre;
    private int ejercito, nivelAtaque, nivelDefensa, vidaActual, velocidad, fila, columna;
    private boolean vive;
    private Actitud actitud;
    private static int totalSoldados = 0;
    private static int contadorEjercito1 = 0;
    private static int contadorEjercito2 = 0;
    public static final int MAXIMO_SOLDADOS = 10;
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
        
        totalSoldados++;
        if(ejercito==1) contadorEjercito1++;
        else if(ejercito==2) contadorEjercito2++;
    }
    public static int obtenerMaximoSoldadosPorEjercito() {
        return MAXIMO_SOLDADOS;
    }
    public static int totalSoldados() {
        return totalSoldados;
    }

    public static int soldadosEjercito1() {
        return contadorEjercito1;
    }

    public static int soldadosEjercito2() {
        return contadorEjercito2;
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
        totalSoldados--;
        if(this.getEjercito()==1) contadorEjercito1--;
        else if(this.getEjercito()==2) contadorEjercito2--;
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

	public void clone(Soldado e) {
		this.nombre = e.nombre; 
	    this.nivelAtaque = e.nivelAtaque;
	    this.nivelDefensa = e.nivelDefensa;
	    this.vidaActual = e.vidaActual;
	    this.vive = e.vive;
	    this.fila = e.fila;
	    this.columna = e.columna;
	    this.ejercito = e.ejercito;
	    this.actitud = e.actitud;
        totalSoldados++;
        if(ejercito==1) contadorEjercito1++;
        else if(ejercito==2) contadorEjercito2++;
    }

	public boolean comparar(Soldado soldado) {
		return this.nombre.equals(soldado.nombre) &&
			   this.nivelAtaque == soldado.nivelAtaque &&
			   this.nivelDefensa == soldado.nivelDefensa &&
			   this.vidaActual == soldado.vidaActual &&
			   this.vive == soldado.vive;
	}
    public Soldado sumar(Soldado otro) {
        this.nivelAtaque += otro.nivelAtaque;
        this.nivelDefensa += otro.nivelDefensa;
        this.vidaActual += otro.vidaActual;
        this.velocidad += otro.velocidad;
        return this;
    }
}
