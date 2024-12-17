package lab21;

public class Soldado {
    private String nombre, reino;
    private int ejercitoID, id, nivelAtaque, nivelDefensa, nivelVida, vidaActual, velocidad, fila, columna;
    private boolean vive;
    private Actitud actitud;
    private static int totalSoldados = 0;
    private static int contadorEjercito1 = 0;
    private static int contadorEjercito2 = 0;
    public static final int MAXIMO_SOLDADOS = 10;
    public static final String RESET = "\u001B[0m";
    public static final String ROJO = "\u001B[31m";
    public static final String VERDE = "\u001B[32m";

    // Enumeración de actitudes del soldado
    public enum Actitud {
        DEFENSIVA, OFENSIVA, HUIDA
    }

    // Constructor por defecto
    public Soldado() {
        this.vive = false;
    }

    // Constructor con parámetros
    public Soldado(String nombre, int nivelAtaque, int nivelDefensa, int nivelVida, int id, boolean vive, int ejercitoID) {
        this.nombre = nombre;
        this.nivelAtaque = nivelAtaque;
        this.nivelDefensa = nivelDefensa;
        this.nivelVida = nivelVida;
        this.vidaActual = nivelVida;
        this.vive = vive;
        this.id = id;
        this.ejercitoID = ejercitoID;
        this.actitud = Actitud.DEFENSIVA;
    }

    // Métodos estáticos para obtener información general sobre soldados
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

    // Métodos de comportamiento del soldado
    public void atacar(Soldado enemigo) {
        if (!this.vive) {
            System.out.println(this.nombre + " ya está muerto y no puede atacar.");
            return;
        }

        int danio = this.nivelAtaque - enemigo.nivelDefensa;
        if (danio > 0) {
            enemigo.vidaActual -= danio;
            System.out.println(this.nombre + " atacó a " + enemigo.getNombre() + " e infligió " + danio + " de daño.");
            if (enemigo.vidaActual <= 0) {
                enemigo.morir();
            }
        } else {
            System.out.println(this.nombre + " no pudo penetrar la defensa de " + enemigo.getNombre());
        }
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
        int danio = ataque - this.nivelDefensa;
        vidaActual -= Math.max(danio, 0); // El daño no puede ser negativo
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

    // Getters y Setters
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
        if (vidaActual <= 0) {
            morir();
        }
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

    public int getId() {
        return id;
    }
    
    public int getEjercitoId() {
        return ejercitoID;
    }

    public boolean estaVivo() {
        return vive;
    }

    // Método para mostrar datos
    public String mostrarREF() {
        String tipoInicial;
        if (this instanceof Espadachin) {
            tipoInicial = "E"; // Espadachín
        } else if (this instanceof Caballero) {
            tipoInicial = "C"; // Caballero
        } else if (this instanceof Arquero) {
            tipoInicial = "A"; // Arquero
        } else if (this instanceof Lancero) {
        	tipoInicial = "L"; // Lancero
        } else {
            tipoInicial = "X";
        }
        return "E" + ejercitoID + tipoInicial + id;
    }

    @Override
    public String toString() {
        return this.mostrarREF() 
        		+ " | " + this.getNombre()
        		+ " | " + this.getVidaActual();
    }
    // Métodos adicionales
    public void clone(Soldado e) {
        this.nombre = e.nombre; 
        this.nivelAtaque = e.nivelAtaque;
        this.nivelDefensa = e.nivelDefensa;
        this.vidaActual = e.vidaActual;
        this.vive = e.vive;
        this.fila = e.fila;
        this.columna = e.columna;
        this.id = e.id;
        this.actitud = e.actitud;
    }

    public boolean comparar(Soldado soldado) {
        return this.nombre.equals(soldado.nombre) &&
        	   this.reino.equals(soldado.reino) &&
               this.nivelAtaque == soldado.nivelAtaque &&
               this.nivelDefensa == soldado.nivelDefensa &&
               this.vidaActual == soldado.vidaActual;
    }

    public Soldado sumar(Soldado otro) {
        this.nivelAtaque += otro.nivelAtaque;
        this.nivelDefensa += otro.nivelDefensa;
        this.vidaActual += otro.vidaActual;
        this.velocidad += otro.velocidad;
        return this;
    }
    public void setActitud(Actitud actitud) {
        this.actitud = actitud;
    }
}