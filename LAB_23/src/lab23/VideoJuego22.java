package lab23;

import javax.swing.*;

public class VideoJuego22 {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Lanzar la ventana del juego y hacerla visible
                JuegoGUI juego = new JuegoGUI();
                juego.setVisible(true);
            }
        });
    }
}