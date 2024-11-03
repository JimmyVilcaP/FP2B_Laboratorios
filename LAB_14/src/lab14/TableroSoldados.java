package lab14;
import java.util.Random;

public class TableroSoldados extends Tablero{	
	private Random random = new Random();
	
	public TableroSoldados(int fila, int columna) {
        super(fila, columna);
    }
	
	public void posicionarSoldado(Soldado soldado) {
        int x, y;

        do {
            x = random.nextInt(tablero.length);
            y = random.nextInt(tablero[0].length);
        } while (tablero[x][y] != null);

        tablero[x][y] = soldado;
    }
}
