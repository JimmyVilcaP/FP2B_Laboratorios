package lab20;

public class Caballero extends Soldado {
    private boolean montado;
    private String arma; // "Espada" o "Lanza"

    public Caballero(int id, int ejercitoID) {
        super("Caballero" + id, 3 + (int)(Math.random() * 3), 3 + (int)(Math.random() * 2), 3 + (int)(Math.random() * 2), id, true, ejercitoID);
        this.montado = false;
        this.arma = "Espada"; // Al principio está con espada
    }

    public void alternarArma() {
        if (montado) {
            arma = "Lanza";
            System.out.println(this.getNombre() + " ha cambiado a la lanza.");
        } else {
            arma = "Espada";
            System.out.println(this.getNombre() + " ha cambiado a la espada.");
        }
    }

    public void montar() {
        if (!montado) {
            montado = true;
            alternarArma(); // Cambia a lanza
            System.out.println(this.getNombre() + " se monta en su caballo.");
        } else {
            System.out.println(this.getNombre() + " ya está montado.");
        }
    }

    public void desmontar() {
        if (montado) {
            montado = false;
            alternarArma(); // Cambia a espada
            System.out.println(this.getNombre() + " se desmonta de su caballo.");
        } else {
            System.out.println(this.getNombre() + " ya está desmontado.");
        }
    }

    public void envestir(Soldado enemigo) {
        int veces = montado ? 3 : 2;
        for (int i = 0; i < veces; i++) {
            atacar(enemigo); // Atacar varias veces
        }
        System.out.println(this.getNombre() + " ha envestido a " + enemigo.getNombre());
    }

    @Override
    public String toString() {
        return super.toString() + " (Caballero, " + (montado ? "Montado" : "Desmontado") + ", Arma: " + arma + ")";
    }
}


