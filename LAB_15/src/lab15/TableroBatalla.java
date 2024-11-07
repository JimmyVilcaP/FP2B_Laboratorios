package lab15;
import java.util.ArrayList;
import java.util.Random;

public class TableroBatalla {
    private Soldado[][] tablero;
    private int size;
    private ArrayList<Soldado> soldados1;
    private ArrayList<Soldado> soldados2;
    private Random random = new Random();

    public TableroBatalla(ArrayList<Soldado> soldados1, ArrayList<Soldado> soldados2) {
        this.size = 10;
        this.soldados1 = soldados1;
        this.soldados2 = soldados2;
        this.tablero = new Soldado[size][size];
        colocarSoldados(soldados1);
        colocarSoldados(soldados2);
    }

    // Coloca los soldados en posiciones aleatorias
    private void colocarSoldados(ArrayList<Soldado> soldados) {
        for (Soldado soldado : soldados) {
            int x, y;
            do {
                x = random.nextInt(size);
                y = random.nextInt(size);
            } while (tablero[x][y] != null); // Repetir si la posición ya está ocupada

            tablero[x][y] = soldado;
            System.out.println("Soldado " + soldado.getNombre() + " colocado en (" + (x + 1) + ", " + (char) ('A' + y) + ")");
        }
    }

    // Mostrar el tablero de batalla
    public void mostrarTablero() {
        System.out.print("   ");
        for (int col = 0; col < 10; col++) {
            System.out.print("   " + (char)('A' + col) + "  ");
        }
        System.out.println();

        for (int i = 0; i < tablero.length; i++) {
            System.out.print((i + 1) + (i < 9 ? "  " : " "));
            for (int j = 0; j < tablero[i].length; j++) {
                if (tablero[i][j] == null) {
                    System.out.print("|____|");
                } else {
                    String representacion = tablero[i][j].toString();
                    System.out.print("|" + representacion + "|");
                }
            }
            System.out.println();
        }
    }

    // Método para mover un soldado y manejar la batalla
    public int moverSoldado(Soldado soldado, int x, int y) {
        int oldX = -1, oldY = -1;
        // Buscar la posición actual del soldado
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (tablero[i][j] == soldado) {
                    oldX = i;
                    oldY = j;
                    break;
                }
            }
        }

        // Si la nueva posición está ocupada por un enemigo, iniciar la batalla
        if (tablero[x][y] != null && tablero[x][y].getReinoId() != soldado.getReinoId()) {
			int gan = iniciarBatalla(soldado, tablero[x][y]);
            if(gan == 1) {
            	tablero[x][y]=null;
            	tablero[x][y]=soldado;
            	tablero[oldX][oldY] = null;
            } else {
            	tablero[oldX][oldY] = null;
            }
            return 1;
        } else if(tablero[x][y] == null) {
        	tablero[oldX][oldY] = null;
            tablero[x][y] = soldado;
            return 0;
        } 
        System.out.println("Posición ocupada por Soldado aliado.");
        return -1;
    }

    // Método de batalla entre dos soldados
    private int iniciarBatalla(Soldado soldado1, Soldado soldado2) {
        System.out.println("¡Batalla entre " + soldado1.getNombre() + " y " + soldado2.getNombre() + "!");
        int vidaTotal1 = soldado1.getVidaActual();
        int vidaTotal2 = soldado2.getVidaActual();

        double probabilidad1 = (double) vidaTotal1 / (vidaTotal1 + vidaTotal2);
        if (random.nextDouble() < probabilidad1) {
            System.out.println(soldado1.getNombre() + " gana la batalla contra " + soldado2.getNombre());
            if(soldado2.getReinoId()==1) eliminarSoldados(soldado2, soldados1);
            else eliminarSoldados(soldado2, soldados2);
            return 1;
        } else {
            System.out.println(soldado2.getNombre() + " gana la batalla contra " + soldado1.getNombre());
            if(soldado1.getReinoId()==1) eliminarSoldados(soldado1, soldados1);
            else eliminarSoldados(soldado1, soldados2);
            return 2;
        }
    }
    private void eliminarSoldados(Soldado s, ArrayList<Soldado> sol) {
		for(int i=0; i<sol.size(); i++) {
			if(s.getId() == sol.get(i).getId()) {
				sol.remove(i);
			}
		}
	}
}
