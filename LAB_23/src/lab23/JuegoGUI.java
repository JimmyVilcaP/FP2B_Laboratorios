package lab23;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JuegoGUI extends JFrame {

    private static final int SIZE = 10;
    private static JPanel panelTablero;
    private static JTextArea areaDeMensajes;
    private static JButton[][] botonesTablero;
    private JComboBox<String> comboBoxReinosJugador1;
    private JComboBox<String> comboBoxReinosJugador2;
    private JCheckBox checkBoxBonus;
    private Reino reino1, reino2;
    private static Mapa mapaJuego;
    private boolean turnoReino1;

    private Soldado soldadoSeleccionado = null;

    public JuegoGUI() {
        setTitle("Juego de Estrategia");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());

        JPanel panelSeleccionReinos = new JPanel();
        panelSeleccionReinos.setLayout(new GridLayout(3, 2));

        String[] reinos = {"Inglaterra", "Francia", "Sacro Imperio", "Castilla Aragón", "Moros"};
        comboBoxReinosJugador1 = new JComboBox<>(reinos);
        comboBoxReinosJugador2 = new JComboBox<>(reinos);

        panelSeleccionReinos.add(new JLabel("Jugador 1 (AZUL):"));
        panelSeleccionReinos.add(comboBoxReinosJugador1);
        panelSeleccionReinos.add(new JLabel("Jugador 2 (ROJO):"));
        panelSeleccionReinos.add(comboBoxReinosJugador2);

        checkBoxBonus = new JCheckBox("Activar bono");
        panelSeleccionReinos.add(checkBoxBonus);

        JButton iniciarJuegoBtn = new JButton("Iniciar Juego");
        iniciarJuegoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarJuego();
            }
        });
        panelSeleccionReinos.add(iniciarJuegoBtn);

        panelPrincipal.add(panelSeleccionReinos, BorderLayout.CENTER);
        add(panelPrincipal);
    }

    private void iniciarJuego() {
        String reinoSeleccionadoJugador1 = (String) comboBoxReinosJugador1.getSelectedItem();
        String reinoSeleccionadoJugador2 = (String) comboBoxReinosJugador2.getSelectedItem();

        mostrarMensaje("Juego iniciado entre: AZUL - " + reinoSeleccionadoJugador1 + " y ROJO - " + reinoSeleccionadoJugador2);

        if (reino1 == null || reino2 == null) {
            reino1 = new Reino(reinoSeleccionadoJugador1, 1);
            reino2 = new Reino(reinoSeleccionadoJugador2, 2);
        }

        mapaJuego = new Mapa(SIZE, "bosque", reino1, reino2);

        mapaJuego.colocarSoldados(reino1);
        mapaJuego.colocarSoldados(reino2);

        panelTablero = new JPanel(new GridLayout(SIZE, SIZE));
        botonesTablero = new JButton[SIZE][SIZE];

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                final int fila = i;
                final int columna = j;
                JButton btn = new JButton();
                btn.setPreferredSize(new Dimension(50, 50));
                btn.setBackground(Color.LIGHT_GRAY);
                btn.setEnabled(true);
                btn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        manejarClickBoton(fila, columna);
                        mostrarSoldadosEnTablero();
                    }
                });
                botonesTablero[i][j] = btn;
                panelTablero.add(btn);
            }
        }

        getContentPane().removeAll();
        add(panelTablero, BorderLayout.CENTER);

        mostrarSoldadosEnTablero();

        turnoReino1 = true;
        mostrarTurno();
    }

    private void mostrarSoldadosEnTablero() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                Soldado soldado = mapaJuego.getMapa()[i][j];
                if (soldado != null) {
                    mostrarSoldado(soldado, i, j);
                }
            }
        }
    }

    private void mostrarTurno() {
        if (turnoReino1) {
            mostrarMensaje("Es el turno de " + reino1.getNombre());
        } else {
            mostrarMensaje("Es el turno de " + reino2.getNombre());
        }
    }

    public static void mostrarSoldado(Soldado soldado, int fila, int columna) {
        if (botonesTablero == null || botonesTablero[fila][columna] == null) {
            return;
        }

        JButton btn = botonesTablero[fila][columna];

        if (soldado != null) {
            btn.setText(soldado.getNombre());

            if (soldado.getEjercitoId() == 1) {
                btn.setBackground(Color.BLUE);
            } else if (soldado.getEjercitoId() == 2) {
                btn.setBackground(Color.RED);
            } else {
                btn.setBackground(Color.GREEN);
            }
        } else {
            btn.setText("");
            btn.setBackground(Color.LIGHT_GRAY);
        }
    }

    private void manejarClickBoton(int fila, int columna) {
        if (soldadoSeleccionado == null) {
            Soldado soldado = mapaJuego.getMapa()[fila][columna];

            if (soldado != null && soldado.getEjercitoId() == (turnoReino1 ? 1 : 2)) {
                soldadoSeleccionado = soldado;
                mostrarMensaje("Soldado seleccionado: " + soldado.getNombre());
                return;
            }
        }

        if (soldadoSeleccionado != null) {
            Soldado soldadoEnemigo = mapaJuego.getMapa()[fila][columna];

            if (soldadoEnemigo != null && soldadoEnemigo.getEjercitoId() != soldadoSeleccionado.getEjercitoId()) {
                // Si hay un soldado enemigo, inicia la batalla
                boolean soldadoGano = mapaJuego.manejarBatalla(soldadoSeleccionado, soldadoEnemigo);

                if (soldadoGano) {
                    // Si el soldado ganó, se mueve a la posición del enemigo
                    mapaJuego.colocarSoldadoEnPosicion(soldadoSeleccionado, fila, columna);
                    botonesTablero[soldadoSeleccionado.getFila()][soldadoSeleccionado.getColumna()].setBackground(Color.LIGHT_GRAY);
                    mostrarSoldadosEnTablero();
                } else {
                    mostrarSoldadosEnTablero();
                }
                
                soldadoSeleccionado = null;
                turnoReino1 = !turnoReino1;
                mostrarTurno();
            } else if (mapaJuego.getMapa()[fila][columna] == null) {
                // Si la casilla está vacía, mover el soldado
                mapaJuego.colocarSoldadoEnPosicion(soldadoSeleccionado, fila, columna);
                botonesTablero[soldadoSeleccionado.getFila()][soldadoSeleccionado.getColumna()].setBackground(Color.LIGHT_GRAY);
                soldadoSeleccionado = null;
                turnoReino1 = !turnoReino1;
                mostrarTurno();
            } else {
                mostrarError("No puedes mover al soldado a esa casilla.");
            }
        }
    }



    public static void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new JuegoGUI().setVisible(true);
            }
        });
    }
}
