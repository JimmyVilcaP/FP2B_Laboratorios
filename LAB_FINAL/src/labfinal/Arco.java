package labfinal;

public class Arco extends Arma {
    public Arco(int durabilidad) {
        super("Arco", durabilidad);
    }

    @Override
    public int calcularDaño(int ataqueBase) {
        return ataqueBase + 5;  // El arco da un bonus de +5 al ataque
    }
}
