package lab24;

import java.io.Serializable;

public class CaballeroFranco extends Soldado implements Serializable  {
	private static final long serialVersionUID = 7L;
    private int cantidadLanzas;  // Habilidad especial: Lanzamiento de lanzas

    public CaballeroFranco(int id, int ejercitoID) {
        super("CaballeroFranco" + id, 12, 6, 15, id, true, ejercitoID);  // Vida inicial 15
        this.cantidadLanzas = 4;  // Cantidad inicial de lanzas
    }

    @Override
    public void atacar(Soldado enemigo) {
        if (cantidadLanzas > 0) {
            System.out.println(this.getNombre() + " lanza una lanza a " + enemigo.getNombre());
            cantidadLanzas--;
            // Daño de lanza
            int danio = 5 + (int)(Math.random() * 4);  // Daño de lanza
            enemigo.serAtacado(danio);
        } else {
            int danio = this.nivelAtaque - enemigo.nivelDefensa;
            enemigo.serAtacado(danio);
        }
    }

    @Override
    public void defender() {
        System.out.println(this.getNombre() + " se defiende con un escudo.");
    }

    public void evolucionar() {
        if (nivelVida < 20) {
            this.nivelVida += 3;
            this.nivelAtaque += 2;
            this.cantidadLanzas += 2;  // Aumenta la cantidad de lanzas
            System.out.println(this.getNombre() + " ha evolucionado a un nuevo nivel.");
        }
    }

    public void usarHabilidadEspecial() {
        if (cantidadLanzas > 0) {
            System.out.println(this.getNombre() + " lanza una lanza.");
            cantidadLanzas--;
        } else {
            System.out.println(this.getNombre() + " no tiene lanzas disponibles.");
        }
    }

    @Override
    public String toString() {
        return super.toString() + " | Lanzas restantes: " + cantidadLanzas;
    }
}
