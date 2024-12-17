package lab22;

public class EspadachinReal extends Soldado {
    private int cantidadCuchillos; // Habilidad especial: Cuchillos

    public EspadachinReal(int id, int ejercitoID) {
        super("EspadachínReal" + id, 10, 8, 12, id, true, ejercitoID);
        this.cantidadCuchillos = 5;  // Cantidad inicial de cuchillos
    }

    @Override
    public void atacar(Soldado enemigo) {
        if (cantidadCuchillos > 0) {
            System.out.println(this.getNombre() + " lanza un cuchillo a " + enemigo.getNombre());
            cantidadCuchillos--;
            int danio = 3 + (int)(Math.random() * 3);
            enemigo.serAtacado(danio);
        } else {
            int danio = this.nivelAtaque - enemigo.nivelDefensa;
            enemigo.serAtacado(danio);
        }
    }

    @Override
    public void defender() {
        // Lógica de defensa especial
        System.out.println(this.getNombre() + " se defiende con una guardia especial.");
    }

    public void evolucionar() {
        // Lógica de evolución (puede aumentar las estadísticas)
        if (nivelVida < 18) {
            this.nivelVida += 2;
            this.nivelAtaque += 1;
            this.cantidadCuchillos += 1;  // Aumenta la cantidad de cuchillos
            System.out.println(this.getNombre() + " ha evolucionado a un nuevo nivel.");
        }
    }

    public void usarHabilidadEspecial() {
        if (cantidadCuchillos > 0) {
            System.out.println(this.getNombre() + " lanza un cuchillo.");
            cantidadCuchillos--;
        } else {
            System.out.println(this.getNombre() + " no tiene cuchillos disponibles.");
        }
    }

    @Override
    public String toString() {
        return super.toString() + " | Cuchillos restantes: " + cantidadCuchillos;
    }
}
