package lab18;
import java.util.Random;

public class Mapa {
    private Ejercito[][] mapa;
    private int size;
    private String terreno;

    public Mapa(int size, String terreno2) {
        this.size = size;
        this.mapa = new Ejercito[size][size];
    }

    // Método para colocar todos los ejércitos en el mapa
    public void colocarEjercito(Reino reino) {
        Random random = new Random();

        for (Ejercito ejercito : reino.getEjercitos()) {
            int x, y;
            do {
                x = random.nextInt(size);
                y = random.nextInt(size);
            } while (mapa[x][y] != null);  // Evitar colocar más de un ejército en la misma celda

            mapa[x][y] = ejercito;  // Coloca el ejército en el tablero
            colocarSoldados(ejercito, x, y);
        }
    }

    // Método para colocar los soldados en la posición del ejército
    private void colocarSoldados(Ejercito ejercito, int x, int y) {
        for (Soldado soldado : ejercito.getMisSoldados()) {
            soldado.setFila(x);
            soldado.setColumna(y);
        }
    }

    // Método para mostrar el tablero
    public void mostrarTablero() {
        System.out.print("   ");
        for (int col = 0; col < size; col++) {
            System.out.print("   " + (char) ('A' + col) + "  ");  // Mostrar encabezado de columnas
        }
        System.out.println();

        // Mostrar filas del tablero
        for (int i = 0; i < mapa.length; i++) {
            System.out.print((i + 1) + (i < 9 ? "  " : " "));  // Mostrar número de fila
            for (int j = 0; j < mapa[i].length; j++) {
                if (mapa[i][j] == null) {
                    System.out.print("|____|");  // Si la casilla está vacía
                } else {
                    // Mostrar información del ejército y el tipo de soldado
                    String representacion = obtenerRepresentacionSoldado(mapa[i][j]);
                    System.out.print("|" + representacion + "|");
                }
            }
            System.out.println();
        }
    }

    // Método para obtener una representación del soldado en una casilla
    private String obtenerRepresentacionSoldado(Ejercito ejercito) {
        Soldado soldado = ejercito.getMisSoldados().get(0);
        if (soldado instanceof Espadachin) {
            return "Esp"+ejercito.getID();  // Abreviación de "Espadachín"
        } else if (soldado instanceof Caballero) {
            return "Cab"+ejercito.getID();  // Abreviación de "Caballero"
        } else if (soldado instanceof Arquero) {
            return "Arq"+ejercito.getID();  // Abreviación de "Arquero"
        }
        return "Des";
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

    // Método para eliminar un ejército del tablero
    public void eliminarEjercitoDelTablero(Ejercito ejercito) {
        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[i].length; j++) {
                if (mapa[i][j] == ejercito) {
                    mapa[i][j] = null;
                    System.out.println("Ejército " + ejercito.getNombre() + " eliminado del tablero.");
                    break;
                }
            }
        }
    }
}