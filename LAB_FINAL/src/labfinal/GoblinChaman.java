package labfinal;

public class GoblinChaman extends Goblin {
    public GoblinChaman(int salud, int ataque) {
        super(salud, ataque);
        this.defensa = 10;  // Goblin Mago tiene más defensa
    }
}
