package lab22;

public class EspadachinTeutonico extends Soldado {
    private int cantidadJabalinas;  // Habilidad especial: Jabalinas
    private boolean modoTortuga;    // Defensa especial: Modo Tortuga

    public EspadachinTeutonico(int id, int ejercitoID) {
        super("EspadachínTeutónico" + id, 10, 7, 13, id, true, ejercitoID);  // Vida inicial 13
        this.cantidadJabalinas = 3;  // Inicialmente tiene 3 jabalinas
        this.modoTortuga = false;    // Modo Tortuga desactivado
    }

    @Override
    public void atacar(Soldado enemigo) {
        if (cantidadJabalinas > 0) {
            System.out.println(this.getNombre() + " lanza una jabalina a " + enemigo.getNombre());
            cantidadJabalinas--;
            // Daño de jabalina
            int danio = 4 + (int)(Math.random() * 3);  // Daño de jabalina
            enemigo.serAtacado(danio);
        } else {
            int danio = this.nivelAtaque - enemigo.nivelDefensa;
            enemigo.serAtacado(danio);
        }
    }

    @Override
    public void defender() {
        if (modoTortuga) {
            System.out.println(this.getNombre() + " se pone en modo tortuga, aumentando su defensa.");
            this.nivelDefensa += 3;  // Aumenta defensa en modo tortuga
        } else {
            System.out.println(this.getNombre() + " se defiende con su espada.");
        }
    }

    public void evolucionar() {
        if (nivelVida < 16) {
            this.nivelVida += 2;
            this.nivelAtaque += 1;
            this.cantidadJabalinas += 1;  // Aumenta la cantidad de jabalinas
            System.out.println(this.getNombre() + " ha evolucionado a un nuevo nivel.");
        }
    }

    public void usarHabilidadEspecial() {
        if (modoTortuga) {
            System.out.println(this.getNombre() + " ya está en modo tortuga.");
        } else {
            modoTortuga = true;
            System.out.println(this.getNombre() + " activa el modo tortuga.");
        }
    }

    @Override
    public String toString() {
        return super.toString() + " | Jabalinas restantes: " + cantidadJabalinas + " | Modo Tortuga: " + (modoTortuga ? "Activado" : "Desactivado");
    }
}
