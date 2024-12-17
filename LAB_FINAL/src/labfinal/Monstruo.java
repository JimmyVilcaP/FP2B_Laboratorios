package labfinal;

public class Monstruo {
    protected int salud;
    protected int ataque;
    protected int defensa;
    private int posX;
    private int posY;

    public Monstruo(int salud, int ataque, int defensa) {
        this.salud = salud;
        this.ataque = ataque;
        this.defensa = defensa;
        this.posX = 0;
        this.posY = 0;
    }

    public void recibirDaño(int daño) {
        int dañoFinal = Math.max(0, daño - this.defensa);
        this.salud -= dañoFinal;
        
        if (this.salud < 0) {
            this.salud = 0;
        }
    }

    public void atacar(Heroe heroe) {
        int daño = ataque;
        heroe.recibirDano(daño);
        System.out.println(getClass().getSimpleName() + " atacó a " + heroe.getNombre() + 
                           " e infligió " + daño + " puntos de daño.");
    }

    public int getSalud() {
        return salud;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getDefensa() {
        return defensa;
    }

    public void setSalud(int salud) {
        this.salud = salud;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    public int getX() {
        return posX;
    }

    public void setX(int posX) {
        this.posX = posX;
    }

    public int getY() {
        return posY;
    }

    public void setY(int posY) {
        this.posY = posY;
    }
}
