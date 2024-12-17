package labfinal;
import java.util.ArrayList;
import java.util.List;

public class Tablero {
    private int tamaño;
    private List<Monstruo> monstruos;
    private Cofre cofre;
    private Trampa trampa;
    private Heroe heroe;
    private Monstruo jefe;  // El monstruo jefe del nivel

    public Tablero(int tamaño) {
        this.tamaño = tamaño;
        this.monstruos = new ArrayList<>();
        this.cofre = new Cofre();
        this.trampa = new Trampa(15); // Ejemplo: trampa de daño 15
        generarMonstruos();
        colocarJefe();
    }

    private void generarMonstruos() {
        for (int i = 0; i < tamaño / 2; i++) {
            // Coloca monstruos aleatorios en el tablero
            Monstruo monstruo = new Goblin((int)(Math.random() * tamaño), (int)(Math.random() * tamaño));
            monstruos.add(monstruo);
        }
    }

    private void colocarJefe() {
        // Coloca un jefe al azar (en un lugar aleatorio)
        this.jefe = new Minotauro((int)(Math.random() * tamaño), (int)(Math.random() * tamaño));
    }

    // Métodos para interacción, mover al héroe, y otras mecánicas del juego
}
