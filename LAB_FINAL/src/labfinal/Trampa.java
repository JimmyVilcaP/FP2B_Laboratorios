package labfinal;

public class Trampa {
    private int daño;

    public Trampa(int daño) {
        this.daño = daño;
    }

    public void activar(Heroe heroe) {
        heroe.recibirDaño(this.daño);
    }
}
