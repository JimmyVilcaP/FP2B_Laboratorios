package lab21;

public class Lancero extends Soldado {
    private int longitudDeLanza;

    public Lancero(int id, int ejercitoID) {
        super("Lancero" + id, 5, 10, 5 + (int)(Math.random() * 4), id, true, ejercitoID);
        this.longitudDeLanza = 5; 
    }

    public void schiltrom() {
        setDefensa(getDefensa() + 1);
        System.out.println(this.getNombre() + " ha realizado el schiltrom, aumentando su defensa en 1.");
    }

    public int getLongitudDeLanza() {
        return longitudDeLanza;
    }

    public void setLongitudDeLanza(int longitudDeLanza) {
        this.longitudDeLanza = longitudDeLanza;
    }

    @Override
    public String toString() {
        return super.toString() + " | Longitud de Lanza: " + longitudDeLanza;
    }
}
