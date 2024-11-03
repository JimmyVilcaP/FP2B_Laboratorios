package lab14;
import java.util.*;

public class TableroEjercitos extends Tablero {
	private Random random = new Random();

	public TableroEjercitos(int fila, int columna) {
		super(fila, columna);
	}
	
	public void posicionarEjercito(Ejercito ejercito) {
        int x, y;

        do {
            x = random.nextInt(tablero.length);
            y = random.nextInt(tablero[0].length);
        } while (tablero[x][y] != null);

        tablero[x][y] = ejercito;
    }
}
