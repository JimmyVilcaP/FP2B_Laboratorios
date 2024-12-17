package lab23;

public class Arquero extends Soldado {
    private int flechas;

    public Arquero(int id, int ejercitoID) {
        super("Arquero" + id, 7, 3, 3 + (int)(Math.random() * 3), id, true, ejercitoID);
        this.flechas = 5 + (int)(Math.random() * 5);
    }

    public void dispararFlecha(Soldado enemigo) {
        if (flechas > 0) {
            flechas--;
            System.out.println(this.getNombre() + " disparó una flecha a " + enemigo.getNombre());
            int danio = 2 + (int)(Math.random() * 3); // Daño aleatorio de la flecha
            enemigo.serAtacado(danio);
        } else {
            System.out.println(this.getNombre() + " no tiene flechas disponibles.");
        }
    }

    @Override
    public String toString() {
        return super.toString() + " (Arquero, Flechas restantes: " + flechas + ")";
    }

	@Override
	public void atacar(Soldado enemigo) {
		// TODO Esbozo de método generado automáticamente
		
	}

	@Override
	public void defender() {
		// TODO Esbozo de método generado automáticamente
		
	}
}
