package lab14;

public class Tablero {
    protected Object[][] tablero;

    public Tablero(int filas, int columnas) {
        tablero = new Object[filas][columnas];
    }
    public void mostrarTablero() {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                if (tablero[i][j] == null) {
                    System.out.print("|____|");
                } else {
                    System.out.print("|" + tablero[i][j].toString() + "|");
                }
            }
            System.out.println();
        }
        System.out.println("\nReinos:");
        for (Reino r : rei) {
            System.out.print("R" + r.getId() + " | REINO " + r.getNombre() + "\n");
        }
    }
}
