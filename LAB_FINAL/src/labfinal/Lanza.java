package labfinal;

public class Lanza extends Arma {
    private int durabilidadMaxima;

    public Lanza(int durabilidadMaxima) {
        super("Lanza", durabilidadMaxima);
        this.durabilidad = durabilidadMaxima;
    }

    @Override
    public int calcularDaño(int ataque) {
        return ataque + (durabilidad > 0 ? 5 : 1);
    }

    public void usar() {
        if (durabilidad > 0) {
            durabilidad--;
        }
    }

    public boolean estaRota() {
        return durabilidad <= 0;
    }
}
