package labfinal;

import javax.swing.SwingUtilities;

public class VideoJuego {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JuegoGUI juego = new JuegoGUI();
                juego.setVisible(true);
            }
        });
    }
}
