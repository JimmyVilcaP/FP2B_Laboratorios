package lab15;
import java.util.Random;

public class Mapa {
    private Ejercito[][] mapa;
    private int size;
    private String terreno;
    
    public Mapa(int size, String terreno) {
    	this.terreno = terreno;
        this.size = size;
        this.mapa = new Ejercito[size][size];
    }

    // Método para colocar todos los ejércitos
    public void colocarEjercito(Reino reino) {
        Random random = new Random();

        for (Ejercito ejercito : reino.getEjercitos()) {
            int x, y;
            do {
                x = random.nextInt(size);
                y = random.nextInt(size);
            } while (mapa[x][y] != null);	

            mapa[x][y] = ejercito;
        }
    }

    public void colocarEjercitoEnPosicion(Ejercito ejercito, int x, int y) {
    	int oldX = -1, oldY = -1;
        // Buscar la posición actual
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (mapa[i][j] == ejercito) {
                    oldX = i;
                    oldY = j;
                    break;
                }
            }
        }
    	
    	// Verificar que las coordenadas están dentro de los límites del tablero
        if (x >= 0 && x < size && y >= 0 && y < size) {
            if (mapa[x][y] == null) {
            	mapa[oldX][oldY] = null;
                mapa[x][y] = ejercito;
            } else {
                System.out.println("Posición ocupada. No se puede colocar el ejército en (" + (x + 1) + ", " + (char) ('A' + y) + ")");
            }
        } else {
            System.out.println("Posición fuera de los límites del tablero.");
        }
    }

    public void mostrarTablero() {
    	System.out.print("   ");
	    for (int col = 0; col < 10; col++) {
	        System.out.print("   " + (char)('A' + col) + "  ");
	    }
        System.out.println();

        for (int i = 0; i < mapa.length; i++) {
            System.out.print((i + 1) + (i < 9 ? "  " : " "));
            for (int j = 0; j < mapa[i].length; j++) {
                if (mapa[i][j] == null) {
                    System.out.print("|____|");
                } else {
                    String representacion = mapa[i][j].toString();
                    System.out.print("|" + representacion + "|");
                }
            }
            System.out.println();
        }
    }

    // Método auxiliar para obtener el ejército en una posición específica (x, y)
    public Ejercito getEjercitoEnPosicion(int x, int y) {
        if (x >= 0 && x < size && y >= 0 && y < size) {
            return mapa[x][y];
        }
        return null;
    }

    public int getSize() {
        return size;
    }
    public void eliminarEjercitoDelTablero(Ejercito ejercito) {
        // Eliminar el ejército del tablero visual
        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[i].length; j++) {
                if (mapa[i][j] == ejercito) {
                    mapa[i][j] = null; // Eliminar el ejército derrotado del tablero
                    System.out.println("Ejército " + ejercito + " eliminado del tablero.");
                    break;
                }
            }
        }

        // Eliminar el ejército del ArrayList de su reino
        Reino reino = ejercito.getReino(); // Obtener el reino desde el ejército
        reino.getEjercitos().remove(ejercito); // Eliminar el ejército del ArrayList del reino
        System.out.println("Ejército " + ejercito + " eliminado de la lista de ejércitos de " + reino.getNombre());
    }
}
