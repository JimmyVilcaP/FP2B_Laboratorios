package lab19;

public class Espadachin extends Soldado {
    private int longitudEspada;

    public Espadachin(int id, int ejercitoID) {
        super("Espadachin" + id, 3 + (int)(Math.random() * 2), 3 + (int)(Math.random() * 2), 3 + (int)(Math.random() * 2), id, true, ejercitoID);
        this.longitudEspada = 1 + (int)(Math.random() * 3); // Longitud aleatoria de espada
    }

    @Override
    public void defender() {
        super.defender();
        System.out.println(this.getNombre() + " crea un muro de escudos.");
    }

    public int getLongitudEspada() {
        return longitudEspada;
    }

    public void setLongitudEspada(int longitudEspada) {
        this.longitudEspada = longitudEspada;
    }

    @Override
    public String toString() {
        return super.toString() + " (Espadachín, Espada de longitud: " + longitudEspada + ")";
    }
}