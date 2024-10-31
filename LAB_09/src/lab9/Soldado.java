package lab9;

public class Soldado {
    private String nombre;
    private int nivelAtaque, nivelDefensa, vidaActual, velocidad;
    private boolean vive;
    private Actitud actitud;
    
    public enum Actitud {
        DEFENSIVA, OFENSIVA, HUIDA
    }

    public Soldado() {
        this.vive = false;
    }

    public Soldado(String nombre, int nivelAtaque, int nivelDefensa, int vidaInicial, boolean vive) {
        this.nombre = nombre;
        this.nivelAtaque = nivelAtaque;
        this.nivelDefensa = nivelDefensa;
        this.vidaActual = vidaInicial;
        this.vive = vive;
        this.actitud = Actitud.DEFENSIVA;
    }

    public Soldado(String nombre, int nivelAtaque, int nivelDefensa, int vidaInicial) {
        this(nombre, nivelAtaque, nivelDefensa, vidaInicial, true);
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
    private void morir() {
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
    
    public int getVelocidad() {
        return velocidad;
    }
    public Actitud getActitud() {
        return actitud;
    }
    public boolean estaVivo() {
        return vive;
    }

    // Método para mostrar datos
    public void mostrarDatos() {
        System.out.println( "Nombre: "+nombre+" | Ataque: "+nivelAtaque+" | Defensa: "+nivelDefensa
        				+" | Vida: "+vidaActual+" | Velocidad: "+velocidad+" | Vive: "+vive
        				+" | Actitud: "+actitud);
    }

}
