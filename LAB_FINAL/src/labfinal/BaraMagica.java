package labfinal;

public class BaraMagica extends Arma {
    private int poderMagico;

    public BaraMagica(int durabilidad, int poderMagico) {
        super("Bara Mágica", durabilidad);
        this.poderMagico = poderMagico;
    }

    @Override
    public int calcularDaño(int ataque) {
        return (int) (poderMagico * 0.5 + ataque);
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
