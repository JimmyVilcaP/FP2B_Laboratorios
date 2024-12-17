package labfinal;

public class Heroe {
    private String nombre;
    private int salud;
    private int ataque;
    private int defensa;
    private Arma arma;
    private int x, y;

    public Heroe(String nombre, int salud, int ataque, int defensa, Arma arma, int x, int y) {
        this.nombre = nombre;
        this.salud = salud;
        this.ataque = ataque;
        this.defensa = defensa;
        this.arma = arma;
        this.x = x;
        this.y = y;
    }

    public void mover(int nuevoX, int nuevoY) {
        if (nuevoX >= 0 && nuevoX < 5 && nuevoY >= 0 && nuevoY < 5) {
            this.x = nuevoX;
            this.y = nuevoY;
        } else {
            System.out.println("Movimiento inválido. Fuera del tablero.");
        }
    }

    public void atacar(Monstruo monstruo) {
        if (monstruo != null) {
            int daño = this.arma.calcularDaño(this.ataque);
            monstruo.recibirDaño(daño);
            System.out.println(this.nombre + " ataca a " + monstruo.getClass().getSimpleName() + " causando " + daño + " de daño.");
        }
    }

    public void recibirDano(int daño) {
        int dañoFinal = Math.max(0, daño - this.defensa);
        this.salud -= dañoFinal;

        if (this.salud < 0) {
            this.salud = 0;
        }

        System.out.println(this.nombre + " recibió " + dañoFinal + " puntos de daño. Salud restante: " + this.salud);
        if (this.salud == 0) {
            System.out.println(this.nombre + " ha muerto.");
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSalud() {
        return salud;
    }

    public void setSalud(int salud) {
        this.salud = salud;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String toString() {
        return "Héroe: " + nombre + " | Salud: " + salud + " | Ataque: " + ataque + " | Defensa: " + defensa;
    }

	public void setPosX(int i) {
		this.x = i;
	}

	public void setPosY(int i) {
		this.y = i;
	}
}
