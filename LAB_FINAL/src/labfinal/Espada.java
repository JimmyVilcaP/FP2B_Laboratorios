package labfinal;

public class Espada extends Arma {
    public Espada(int durabilidad) {
        super("Espada", durabilidad);
    }

    @Override
    public int calcularDaño(int ataque) {
        return ataque + 10;
    }
}
