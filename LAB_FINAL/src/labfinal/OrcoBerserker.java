package labfinal;

public class OrcoBerserker extends Orco {
    private int ira;  // Atributo especial que aumenta el ataque

    public OrcoBerserker(int salud, int ataque, int ira) {
        super(salud, ataque);  // Llamada al constructor de la clase base Orco
        this.ira = ira;
    }

    @Override
    public int getAtaque() {
        // El ataque aumenta cuando el OrcoBerserker está en estado de ira
        return super.getAtaque() + ira;
    }
}
