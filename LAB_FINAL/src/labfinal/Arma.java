package labfinal;

public abstract class Arma {
    protected String nombre;
    protected int durabilidad;

    public Arma(String nombre, int durabilidad) {
        this.nombre = nombre;
        this.durabilidad = durabilidad;
    }

    public abstract int calcularDaño(int ataqueBase);

    public void desgastar() {
        if (this.durabilidad > 0) {
            this.durabilidad--;
        }
    }

    public boolean estaRota() {
        return this.durabilidad <= 0;
    }
}
