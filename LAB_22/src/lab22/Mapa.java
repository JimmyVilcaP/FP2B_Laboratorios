package lab22;
import java.util.Random;

public class Mapa {
    private Soldado[][] mapa;
    private int size;

    public Mapa(int size, String terreno) {
        this.size = size;
        this.mapa = new Soldado[size][size];
    }

    // Método para colocar los soldados de un ejército en el mapa
    public void colocarSoldados(Reino reino) {
        Random random = new Random();
        Ejercito ejercito = reino.getEjercito();

        for (Soldado soldado : ejercito.getMisSoldados()) {
            int x, y;
            do {
                x = random.nextInt(size);
                y = random.nextInt(size);
            } while (mapa[x][y] != null);

            mapa[x][y] = soldado;
            soldado.setFila(x);
            soldado.setColumna(y);
        }
    }

    // Método para mostrar el tablero
    public void mostrarTablero() {
        System.out.print("   ");
        for (int col = 0; col < size; col++) {
            System.out.print("   " + (char) ('A' + col) + "  "); 
        }
        System.out.println();

        // Mostrar filas del tablero
        for (int i = 0; i < mapa.length; i++) {
            System.out.print((i + 1) + (i < 9 ? "  " : " "));
            for (int j = 0; j < mapa[i].length; j++) {
                if (mapa[i][j] == null) {
                    System.out.print("|____|");
                } else {
                    System.out.print("|" + mapa[i][j].mostrarREF() + "|");
                }
            }
            System.out.println();
        }
    }
    
    public void colocarSoldadoEnPosicion(Soldado soldado, int x, int y) {
    	int oldX = -1, oldY = -1;
        // Buscar la posición actual
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (mapa[i][j] == soldado) {
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
                mapa[x][y] = soldado;
            } else {
                System.out.println("Posición ocupada. No se puede colocar el soldado en (" + (x + 1) + ", " + (char) ('A' + y) + ")");
            }
        } else {
            System.out.println("Posición fuera de los límites del tablero.");
        }
    }
    
    public Soldado getSoldadoEnPosicion(int x, int y) {
        if (x >= 0 && x < size && y >= 0 && y < size) {
            return mapa[x][y];
        }
        return null;
    }

    public int getSize() {
        return size;
    }

    // Método para eliminar un soldado del tablero
    public void eliminarSoldadoDelTablero(Soldado soldado, Reino reino) {
        Ejercito ejercito = reino.getEjercito();
    	for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[i].length; j++) {
                if (mapa[i][j] == soldado) {
                    mapa[i][j] = null;
                    System.out.println("Soldado eliminado del tablero.");
                    
                    ejercito.getMisSoldados().remove(soldado); // Eliminar el ejército del ArrayList del reino
                    System.out.println("Soldado " + soldado.mostrarREF() + " eliminado de la lista de soldados de " + reino.getNombre());
                    return;
                }
            }
        }
        

    }
}
