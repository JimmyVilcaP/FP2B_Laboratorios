package labfinal;

public class MinotauroArcano extends Minotauro {
    private int magia;  // Atributo especial que representa poder mágico

    public MinotauroArcano(int salud, int ataque, int magia) {
        super(salud, ataque);  // Llamada al constructor de la clase base Minotauro
        this.magia = magia;
    }

    public int getMagia() {
        return magia;
    }

    @Override
    public int getAtaque() {
        // El MinotauroArcano tiene un ataque mágico adicional
        return super.getAtaque() + magia;
    }
}
