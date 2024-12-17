package lab22;

public class CaballeroMoro extends Soldado {
    private int cantidadFlechas;  // Habilidad especial: Flechas
    private int poderEnvestir;    // Poder extra al envestir

    public CaballeroMoro(int id, int ejercitoID) {
        super("CaballeroMoro" + id, 12, 7, 13, id, true, ejercitoID);  // Vida inicial 13
        this.cantidadFlechas = 5;  // Inicialmente tiene 5 flechas
        this.poderEnvestir = 3;    // Poder de envestir inicial
    }

    @Override
    public void atacar(Soldado enemigo) {
        if (cantidadFlechas > 0) {
            System.out.println(this.getNombre() + " dispara una flecha a " + enemigo.getNombre());
            cantidadFlechas--;
            // Daño de flecha
            int danio = 3 + (int)(Math.random() * 3);  // Daño de flecha
            enemigo.serAtacado(danio);
        } else {
            int danio = this.nivelAtaque - enemigo.nivelDefensa;
            enemigo.serAtacado(danio);
        }
    }

    @Override
    public void defender() {
        System.out.println(this.getNombre() + " se defiende con su espada.");
    }

    public void evolucionar() {
        if (nivelVida < 16) {
            this.nivelVida += 2;
            this.nivelAtaque += 1;
            this.cantidadFlechas += 2;  // Aumenta la cantidad de flechas
            this.poderEnvestir += 1;    // Aumenta el poder de envestir
            System.out.println(this.getNombre() + " ha evolucionado a un nuevo nivel.");
        }
    }

    public void usarHabilidadEspecial() {
        if (cantidadFlechas > 0) {
            System.out.println(this.getNombre() + " lanza una flecha.");
            cantidadFlechas--;
        } else {
            System.out.println(this.getNombre() + " no tiene flechas disponibles.");
        }
    }

    public void envestir(Soldado enemigo) {
        int danio = poderEnvestir + this.nivelAtaque - enemigo.nivelDefensa;
        enemigo.serAtacado(danio);
        System.out.println(this.getNombre() + " ha envestido a " + enemigo.getNombre() + " con poder " + poderEnvestir);
    }

    @Override
    public String toString() {
        return super.toString() + " | Flechas restantes: " + cantidadFlechas + " | Poder de Envestir: " + poderEnvestir;
    }
}
