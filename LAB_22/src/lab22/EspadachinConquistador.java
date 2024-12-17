package lab22;

public class EspadachinConquistador extends Soldado {
    private int cantidadHachas;  // Habilidad especial: Hachas

    public EspadachinConquistador(int id, int ejercitoID) {
        super("EspadachínConquistador" + id, 11, 6, 14, id, true, ejercitoID);  // Vida inicial 14
        this.cantidadHachas = 3;  // Inicialmente tiene 3 hachas
    }

    @Override
    public void atacar(Soldado enemigo) {
        if (cantidadHachas > 0) {
            System.out.println(this.getNombre() + " lanza un hacha a " + enemigo.getNombre());
            cantidadHachas--;
            // Daño de hacha
            int danio = 5 + (int)(Math.random() * 4);  // Daño de hacha
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
        if (nivelVida < 18) {
            this.nivelVida += 3;
            this.nivelAtaque += 1;
            this.cantidadHachas += 1;  // Aumenta la cantidad de hachas
            System.out.println(this.getNombre() + " ha evolucionado a un nuevo nivel.");
        }
    }

    public void usarHabilidadEspecial() {
        if (cantidadHachas > 0) {
            System.out.println(this.getNombre() + " lanza un hacha.");
            cantidadHachas--;
        } else {
            System.out.println(this.getNombre() + " no tiene hachas disponibles.");
        }
    }

    @Override
    public String toString() {
        return super.toString() + " | Hachas restantes: " + cantidadHachas;
    }
}
