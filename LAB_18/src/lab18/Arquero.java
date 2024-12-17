package lab18;

public class Arquero extends Soldado {
    private int flechas;

    public Arquero(String reino, int id, int ejercitoID, int reinoID) {
        super(reino, "Arquero" + id, 2 + (int)(Math.random() * 2), 1 + (int)(Math.random() * 2), 1 + (int)(Math.random() * 2), id, true, ejercitoID, reinoID);
        this.flechas = 5 + (int)(Math.random() * 5); // Número aleatorio de flechas (de 5 a 9)
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
}
