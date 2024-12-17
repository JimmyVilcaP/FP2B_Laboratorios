package lab23;

import java.util.Random;
import javax.swing.JOptionPane;

public class Mapa {
    private Soldado[][] mapa;
    private int size;
    private Reino reino1;
    private Reino reino2;

    public Mapa(int size, String terreno, Reino reino1, Reino reino2) {
        this.size = size;
        this.mapa = new Soldado[size][size];
        this.reino1 = reino1;
        this.reino2 = reino2;
    }

    public void colocarSoldados(Reino reino) {
        Random random = new Random();

        for (Soldado soldado : reino.getEjercito().getMisSoldados()) {
            int x, y;
            do {
                x = random.nextInt(size);
                y = random.nextInt(size);
            } while (mapa[x][y] != null);

            mapa[x][y] = soldado;
            soldado.setFila(x);
            soldado.setColumna(y);

            JuegoGUI.mostrarSoldado(soldado, x, y);
        }
    }

    public void colocarSoldadoEnPosicion(Soldado soldado, int x, int y) {
        int oldX = -1, oldY = -1;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (mapa[i][j] == soldado) {
                    oldX = i;
                    oldY = j;
                    break;
                }
            }
        }

        if (x >= 0 && x < size && y >= 0 && y < size) {
            if (mapa[x][y] == null) {
                mapa[oldX][oldY] = null;
                mapa[x][y] = soldado;
                soldado.setFila(x);
                soldado.setColumna(y);

                JuegoGUI.mostrarSoldado(mapa[oldX][oldY], oldX, oldY);
                JuegoGUI.mostrarSoldado(mapa[x][y], x, y);
            } else {
                JuegoGUI.mostrarError("Posición ocupada por otro soldado.");
            }
        } else {
            JuegoGUI.mostrarError("Posición fuera de los límites del tablero.");
        }
    }

    public Soldado[][] getMapa() {
        return mapa;
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

    public boolean manejarBatalla(Soldado soldadoSel, Soldado soldadoEne) {
        double vidTotal = soldadoSel.getVidaActual() + soldadoEne.getVidaActual();
        double prob1 = (soldadoSel.getVidaActual() * 100) / vidTotal;
        prob1 = Math.round(prob1 * 100.0) / 100.0;
        double prob2 = 100 - prob1;

        double ganador = Math.random() * 100;

        mostrarResultadoBatalla(soldadoSel, soldadoEne, prob1, prob2, ganador);

        if (ganador < prob1) {
            eliminarSoldadoDelTablero(soldadoEne, soldadoEne.getEjercitoId());
            return true;
        } else {
            eliminarSoldadoDelTablero(soldadoSel, soldadoSel.getEjercitoId());
            return false;
        }
    }

    private void mostrarResultadoBatalla(Soldado soldadoSel, Soldado soldadoEne, double prob1, double prob2, double ganador) {
        String mensajeBatalla = String.format(
            "Probabilidades de victoria:\n\n" +
            "%s: %.2f%%  VS  %s: %.2f%%\n\n" +
            "La probabilidad generada fue: %.2f%%\n\n", 
            soldadoSel.getNombre(), prob1, soldadoEne.getNombre(), prob2, ganador
        );

        String resultadoFinal;
        if (ganador < prob1) {
            resultadoFinal = "El ganador es: " + soldadoSel.getNombre();
            JuegoGUI.mostrarMensaje("El " + soldadoSel.getNombre() + " ha ganado la batalla.");
        } else {
            resultadoFinal = "El ganador es: " + soldadoEne.getNombre();
            JuegoGUI.mostrarMensaje("El " + soldadoEne.getNombre() + " ha ganado la batalla.");
        }

        mensajeBatalla += resultadoFinal;

        JOptionPane.showMessageDialog(null, mensajeBatalla, "Resultado de la Batalla", JOptionPane.INFORMATION_MESSAGE);
    }

    public void verificarGanador() {
        if (reino1.getEjercito().getMisSoldados().isEmpty()) {
            mostrarMensajeDeFinJuego(reino2.getNombre());
        } else if (reino2.getEjercito().getMisSoldados().isEmpty()) {
            mostrarMensajeDeFinJuego(reino1.getNombre());
        }
    }

    private void mostrarMensajeDeFinJuego(String ganador) {
        String mensaje = "¡El " + ganador + " ha ganado la guerra!";
        JOptionPane.showMessageDialog(null, mensaje, "Fin del Juego", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    void eliminarSoldadoDelTablero(Soldado soldado, int ejercitoId) {
        if (ejercitoId == 1) {
            eliminarSoldadoDelEjercito(soldado, reino1);
        } else if (ejercitoId == 2) {
            eliminarSoldadoDelEjercito(soldado, reino2);
        }
        verificarGanador();
    }

    void eliminarSoldadoDelEjercito(Soldado soldado, Reino reino) {
        Ejercito ejercito = reino.getEjercito();
        ejercito.getMisSoldados().remove(soldado);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (mapa[i][j] == soldado) {
                    mapa[i][j] = null;
                    JuegoGUI.mostrarSoldado(null, i, j);
                    return;
                }
            }
        }
    }
}
