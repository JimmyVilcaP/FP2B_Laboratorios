package lab15;
import java.util.ArrayList;
import java.util.Random;

public class TableroBatalla {
    private Soldado[][] tablero;
    private int size;
    private ArrayList<Soldado> soldados1;
    private ArrayList<Soldado> soldados2;
    private Random random = new Random();

    public TableroBatalla(ArrayList<Soldado> soldados1, ArrayList<Soldado> soldados2, int reinoID) {
        this.size = 10;
        if(reinoID == 1) {
	        this.soldados1 = soldados1;
	        this.soldados2 = soldados2;
        } else {
        	this.soldados1 = soldados2;
	        this.soldados2 = soldados1;
        }
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
        }
    }

    // Mostrar el tablero de batalla
    public void mostrarTablero() {
        System.out.print("    ");
        for (int col = 0; col < 10; col++) {
            System.out.print("  " + (char)('A' + col) + "    ");
        }
        System.out.println();

        for (int i = 0; i < tablero.length; i++) {
            System.out.print((i + 1) + (i < 9 ? "  " : " "));
            for (int j = 0; j < tablero[i].length; j++) {
                if (tablero[i][j] == null) {
                    System.out.print("|_____|");
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
            if (gan == 1) {
                tablero[x][y] = soldado;
                tablero[oldX][oldY] = null;
            } else {
                tablero[oldX][oldY] = null;
            }
            return 1;
        } else if (tablero[x][y] == null) {
            // Mover a una posición vacía
            tablero[oldX][oldY] = null;
            tablero[x][y] = soldado;
            return 0;
        }
        System.out.println("Posición ocupada por Soldado aliado.");
        return -1;
    }

    // Método de batalla entre dos soldados
    private int iniciarBatalla(Soldado soldado1, Soldado soldado2) {
        System.out.println("¡Batalla entre " + soldado1.toString() + " | " + soldado1.getReinoId() + "|" + soldado1.getNombre() + " y " 
        									 + soldado2.toString() + " | " + soldado2.getReinoId() + "|"+ soldado2.getNombre() + "!");
        int vidaTotal1 = soldado1.getVidaActual();
        int vidaTotal2 = soldado2.getVidaActual();
        
        double probabilidad = (double) vidaTotal1 / (vidaTotal1 + vidaTotal2);
        if (random.nextDouble() < probabilidad) {
            System.out.println(soldado1.getNombre() + " Vida: " + vidaTotal1 + " gana la batalla contra " 
            				 + soldado2.getNombre() + " Vida: " + vidaTotal2 + "|| Prob: " + probabilidad+100);
            if (soldado1.getReinoId() == 1) eliminarSoldadoDeLista(soldado2, soldados2);
            else if (soldado1.getReinoId() == 2) eliminarSoldadoDeLista(soldado2, soldados1);
            return 1;
        } else {
        	System.out.println(soldado2.getNombre() + " Vida: " + vidaTotal2 + " gana la batalla contra " 
   				 			 + soldado1.getNombre() + " Vida: " + vidaTotal1 + "|| Prob: " + probabilidad*100);
        	if (soldado2.getReinoId() == 2) eliminarSoldadoDeLista(soldado1, soldados1);
            else if (soldado2.getReinoId() == 1) eliminarSoldadoDeLista(soldado1, soldados2);
        	return 2;
        }
    }
    private void eliminarSoldadoDeLista(Soldado soldado, ArrayList<Soldado> s) {
        for(int i=0; i<s.size(); i++) {
        	if(s.get(i).getId() == soldado.getId()) {
        		System.out.println(s.get(i).getId()+ " Se eliminó soldado "+s.get(i).getNombre()+", "+s.get(i).getReinoId());
        		s.remove(i);
        		return;
        	}
        }
    }
}
/*
 * SOLDADOS1 == 2
 * 
 * 
 */