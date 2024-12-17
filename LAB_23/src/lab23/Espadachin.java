package lab23;

public class Espadachin extends Soldado {
    private int longitudEspada;

    public Espadachin(int id, int ejercitoID) {
        super("Espadachin" + id, 10, 8, 8 + (int)(Math.random() * 3), id, true, ejercitoID);
        this.longitudEspada = 1 + (int)(Math.random() * 3); // Longitud aleatoria de espada
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

	@Override
	public void atacar(Soldado enemigo) {
		// TODO Esbozo de método generado automáticamente
		
	}
    @Override
    public void defender() {
        System.out.println(this.getNombre() + " crea un muro de escudos.");
    }
}