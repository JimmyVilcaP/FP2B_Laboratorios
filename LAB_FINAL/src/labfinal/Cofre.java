package labfinal;

public class Cofre {
    private String tipo;
    private int valor;
    private int posX;
    private int posY;

    public Cofre() {
        if (Math.random() > 0.75) {
            this.tipo = "bonus";
            this.valor = (int) (Math.random() * 50) + 10;
        } else {
            this.tipo = "trampa"; 
            this.valor = (int) (Math.random() * 10) + 5;
        }
    }

    public String getTipo() {
        return tipo;
    }

    public int getValor() {
        return valor;
    }

    public void aplicarEfecto(Heroe heroe) {
        if (this.tipo.equals("bonus")) {
            heroe.setSalud(heroe.getSalud() + this.valor);
        } else if (this.tipo.equals("trampa")) {
            heroe.setSalud(heroe.getSalud() - this.valor);
        }
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
