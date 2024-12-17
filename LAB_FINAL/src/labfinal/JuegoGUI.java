package labfinal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JuegoGUI extends JFrame {
    private static final int TAM_TABLERO = 5; // Tamaño del tablero 5x5
    private Heroe heroe;
    private List<Monstruo> monstruos;
    private List<Arma> armas;
    private List<Cofre> cofres;
    private List<JButton> botonesTablero;
    private int nivel;
    private JPanel panelTablero;
    private JPanel panelMenu;
    private JTextField nombreJugadorField;
    private Random random;

    public JuegoGUI() {
        setTitle("Juego de Mazmorras");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        nivel = 1;

        cofres = new ArrayList<>();
        armas = new ArrayList<>();
        armas.add(new BaraMagica(5, 20));
        armas.add(new Lanza(10));

        random = new Random();

        mostrarMenuInicio();
    }

    private void mostrarMenuInicio() {
        panelMenu = new JPanel();
        panelMenu.setLayout(new BoxLayout(panelMenu, BoxLayout.Y_AXIS));

        JLabel bienvenidaLabel = new JLabel("¡Bienvenido al Juego de Mazmorras!");
        bienvenidaLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelMenu.add(bienvenidaLabel);

        JLabel nombreLabel = new JLabel("Ingresa tu nombre:");
        nombreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelMenu.add(nombreLabel);

        nombreJugadorField = new JTextField();
        nombreJugadorField.setMaximumSize(new Dimension(200, 30));
        panelMenu.add(nombreJugadorField);

        JButton iniciarButton = new JButton("Iniciar Juego");
        iniciarButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        iniciarButton.addActionListener(e -> {
            String nombre = nombreJugadorField.getText();
            heroe = new Heroe(nombre, 100, 30, 10, new BaraMagica(5, 20), 2, 2);
            generarMonstruos();
            generarCofres();
            mostrarTablero();
        });
        panelMenu.add(iniciarButton);
        
        getContentPane().add(panelMenu);
    }

    private void mostrarTablero() {
        panelMenu.setVisible(false);
        panelTablero = new JPanel();
        panelTablero.setLayout(new GridLayout(TAM_TABLERO, TAM_TABLERO));

        botonesTablero = new ArrayList<>();

        for (int i = 0; i < TAM_TABLERO * TAM_TABLERO; i++) {
            JButton botonCasilla = new JButton();
            botonCasilla.setBackground(Color.GRAY);
            botonCasilla.addActionListener(e -> manejarClick(botonCasilla));
            botonesTablero.add(botonCasilla);
            panelTablero.add(botonCasilla);
        }

        asignarElementos();

        getContentPane().add(panelTablero);
        revalidate();
        repaint();
    }

    private void manejarClick(JButton boton) {
        int index = botonesTablero.indexOf(boton);
        int fila = index / TAM_TABLERO;
        int columna = index % TAM_TABLERO;
        guardarEstadoJuego(heroe);

        if (fila == heroe.getX() && columna == heroe.getY()) {
            JOptionPane.showMessageDialog(this, "¡Estás en tu posición actual!");
            return;
        }

        if (boton.getBackground() == Color.ORANGE || boton.getBackground() == Color.RED || boton.getBackground() == Color.GREEN || boton.getBackground() == Color.BLUE || boton.getBackground() == Color.MAGENTA) {
            Monstruo monstruo = obtenerMonstruoPorPosicion(fila, columna);
            if (monstruo != null) {
                realizarCombate(monstruo, boton);
            }
        } else if (boton.getBackground() == Color.PINK) {
        	Cofre cofre = obtenerCofre(fila, columna);
            abrirCofre(cofre, boton);
            moverHeroe(boton, fila, columna);
        } else {
            moverHeroe(boton, fila, columna);
        }
    }

    private void realizarCombate(Monstruo monstruo, JButton boton) {
        while (heroe.getSalud() > 0 && monstruo.getSalud() > 0) {
            heroe.atacar(monstruo);
            if (monstruo.getSalud() > 0) {
                monstruo.atacar(heroe);
            }
        }

        if (heroe.getSalud() <= 0) {
            JOptionPane.showMessageDialog(this, "¡Has sido derrotado! Juego terminado.");
            System.exit(0);
        } else {
            JOptionPane.showMessageDialog(this, "¡Has derrotado a un " + monstruo.getClass().getSimpleName() + "!");
            boton.setBackground(Color.GRAY);
            boton.setText("");
            monstruos.remove(monstruo);
            if (monstruos.isEmpty()) {
                avanzarNivel();
            }
        }
    }

    private void abrirCofre(Cofre cofre, JButton boton) {
        cofre.aplicarEfecto(heroe);
        if(cofre.getTipo().equalsIgnoreCase("bonus")) JOptionPane.showMessageDialog(this, "¡Has encontrado un cofre bonus!"+" Tu vida aumenta en "+cofre.getValor());
        else JOptionPane.showMessageDialog(this, "¡Encontraste un cofre trampa!"+" Tu vida baja en "+cofre.getValor());
        
        boton.setBackground(Color.GRAY);
        boton.setText("");
    }

    private void moverHeroe(JButton boton, int fila, int columna) {
        botonesTablero.get(heroe.getX() * TAM_TABLERO + heroe.getY()).setBackground(Color.GRAY);
        botonesTablero.get(heroe.getX() * TAM_TABLERO + heroe.getY()).setText("");
        heroe.setPosX(fila);
        heroe.setPosY(columna);
        boton.setBackground(Color.CYAN);
        boton.setText("HÉROE: "+heroe.getNombre());
    }

    private void avanzarNivel() {
        nivel++;
        if (nivel > 5) {
            JOptionPane.showMessageDialog(this, "¡Felicidades! Has completado todos los niveles.");
            System.exit(0);
        } else {
            JOptionPane.showMessageDialog(this, "¡Nivel " + nivel + " completado! Avanzando al siguiente nivel.");
            generarMonstruos();
            generarCofres();
            asignarElementos();
        }
    }

    private Monstruo obtenerMonstruoPorPosicion(int fila, int columna) {
        for (Monstruo monstruo : monstruos) {
            if (monstruo.getX() == fila && monstruo.getY() == columna) {
                return monstruo;
            }
        }
        return null;
    }
    private Cofre obtenerCofre(int fila, int columna) {
        for (Cofre cofre : cofres) {
            if (cofre.getX() == fila && cofre.getY() == columna) {
                return cofre;
            }
        }
        return null;
    }

    private void generarMonstruos() {
        monstruos = new ArrayList<>();
        switch (nivel) {
            case 1:
                for (int i = 0; i < 10; i++) {
                    monstruos.add(new Slime(random.nextInt(30) + 10, random.nextInt(10) + 5));
                }
                monstruos.add(new SlimeSupremo(50, 20));
                break;
            case 2:
                for (int i = 0; i < 8; i++) {
                    monstruos.add(new Goblin(random.nextInt(40) + 20, random.nextInt(10) + 5));
                }
                monstruos.add(new GoblinChaman(70, 25));
                break;
            case 3:
                for (int i = 0; i < 6; i++) {
                    monstruos.add(new Orco(random.nextInt(50) + 30, random.nextInt(15) + 10));
                }
                monstruos.add(new OrcoBerserker(90, 30, 5));
                break;
            case 4:
                for (int i = 0; i < 4; i++) {
                    monstruos.add(new Minotauro(random.nextInt(60) + 40, random.nextInt(20) + 15));
                }
                monstruos.add(new MinotauroArcano(100, 40, 5));
                break;
            case 5:
                monstruos.add(new Slime(random.nextInt(30) + 10, random.nextInt(10) + 5));
                monstruos.add(new Goblin(random.nextInt(40) + 20, random.nextInt(10) + 5));
                monstruos.add(new Orco(random.nextInt(50) + 30, random.nextInt(15) + 10));
                monstruos.add(new Minotauro(random.nextInt(60) + 40, random.nextInt(20) + 15));
                monstruos.add(new JefeFinal(150, 50));
                break;
        }
    }

    private void generarCofres() {
        cofres = new ArrayList<>();
        int cantidadCofres = Math.min(3, TAM_TABLERO * TAM_TABLERO - monstruos.size() - 1);
        for (int i = 0; i < cantidadCofres; i++) {
            cofres.add(new Cofre());
        }
    }

    private void asignarElementos() {
        List<Integer> casillasDisponibles = new ArrayList<>();
        for (int i = 0; i < TAM_TABLERO * TAM_TABLERO; i++) {
            casillasDisponibles.add(i);
        }

        // Asignar posición al héroe
        int casillaHeroe = casillasDisponibles.remove(random.nextInt(casillasDisponibles.size()));
        JButton botonHeroe = botonesTablero.get(casillaHeroe);
        botonHeroe.setBackground(Color.CYAN);
        botonHeroe.setText("HÉROE: "+heroe.getNombre());
        heroe.setPosX(casillaHeroe / TAM_TABLERO);
        heroe.setPosY(casillaHeroe % TAM_TABLERO);

        // Asignar posiciones a los monstruos comunes
        for (Monstruo monstruo : monstruos) {
            if (!casillasDisponibles.isEmpty()) {
                int casillaMonstruo = casillasDisponibles.remove(random.nextInt(casillasDisponibles.size()));
                JButton botonMonstruo = botonesTablero.get(casillaMonstruo);

                // Determinar tipo de monstruo según el nivel
                String tipoMonstruo;
                if (monstruo instanceof SlimeSupremo) {
                    tipoMonstruo = "SLIME SUPREMO";
                    botonMonstruo.setBackground(Color.RED);
                } else if (monstruo instanceof GoblinChaman) {
                    tipoMonstruo = "CHAMAN GOBLIN";
                    botonMonstruo.setBackground(Color.RED);
                } else if (monstruo instanceof OrcoBerserker) {
                    tipoMonstruo = "ORCO BERSERKER";
                    botonMonstruo.setBackground(Color.RED);
                } else if (monstruo instanceof MinotauroArcano) {
                    tipoMonstruo = "TAURO ARCANO";
                    botonMonstruo.setBackground(Color.RED);
                } else if (monstruo instanceof JefeFinal) {
                    tipoMonstruo = "GUARDIÁN DEL INFINITO";
                    botonMonstruo.setBackground(Color.RED);
                } else if (monstruo instanceof Slime) {
                    tipoMonstruo = "Slime";
                    botonMonstruo.setBackground(Color.ORANGE);
                } else if (monstruo instanceof Goblin) {
                    tipoMonstruo = "Goblin";
                    botonMonstruo.setBackground(Color.GREEN);
                } else if (monstruo instanceof Orco) {
                    tipoMonstruo = "Orco";
                    botonMonstruo.setBackground(Color.BLUE);
                } else if (monstruo instanceof Minotauro) {
                    tipoMonstruo = "Minotauro";
                    botonMonstruo.setBackground(Color.MAGENTA);
                } else {
                    tipoMonstruo = "asdas";
                    botonMonstruo.setBackground(Color.GRAY);
                }

                botonMonstruo.setText(tipoMonstruo);
                monstruo.setX(casillaMonstruo / TAM_TABLERO);
                monstruo.setY(casillaMonstruo % TAM_TABLERO);
            }
        }

        for (Cofre cofre : cofres) {
            if (!casillasDisponibles.isEmpty()) {
                int casillaCofre = casillasDisponibles.remove(random.nextInt(casillasDisponibles.size()));
                JButton botonCofre = botonesTablero.get(casillaCofre);
                botonCofre.setBackground(Color.PINK);
                botonCofre.setText("COFRE");
                
                cofre.setX(casillaCofre / TAM_TABLERO);
                cofre.setY(casillaCofre % TAM_TABLERO);
            }
        }
    }

    private void guardarEstadoJuego(Heroe heroe) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("estado_juego.txt"))) {
            writer.write("Jugador: " + heroe.getNombre() + "\n");
            writer.write("Tamaño del TABLERO: " + TAM_TABLERO + "\n");
            writer.write("\nMounstruos del Nivel "+ nivel +":\n");

            for (Monstruo monstruo : monstruos) {
            	if (monstruo instanceof SlimeSupremo) {
            		writer.write("SLIME SUPREMO" + " - Salud: " + monstruo.getSalud() + " - Ataque: " + monstruo.getAtaque() + "\n");
            	} else if (monstruo instanceof GoblinChaman) {
            		writer.write("GOBLIN CHAMN" + " - Salud: " + monstruo.getSalud() + " - Ataque: " + monstruo.getAtaque() + "\n");
            	} else if (monstruo instanceof OrcoBerserker) {
            		writer.write("ORCO BERSERKER" + " - Salud: " + monstruo.getSalud() + " - Ataque: " + monstruo.getAtaque() + "\n");
                } else if (monstruo instanceof MinotauroArcano) {
                	writer.write("TAURO ARCANO" + " - Salud: " + monstruo.getSalud() + " - Ataque: " + monstruo.getAtaque() + "\n");
                } else if (monstruo instanceof JefeFinal) {
                	writer.write("GUARDIAN DEL INFINITO" + " - Salud: " + monstruo.getSalud() + " - Ataque: " + monstruo.getAtaque() + "\n");
                } else if (monstruo instanceof Slime) {
                	writer.write("SLIME" + " - Salud: " + monstruo.getSalud() + " - Ataque: " + monstruo.getAtaque() + "\n");
                } else if (monstruo instanceof Goblin) {
                	writer.write("GOBLIN" + " - Salud: " + monstruo.getSalud() + " - Ataque: " + monstruo.getAtaque() + "\n");
                } else if (monstruo instanceof Orco) {
                	writer.write("ORCO" + " - Salud: " + monstruo.getSalud() + " - Ataque: " + monstruo.getAtaque() + "\n");
                } else if (monstruo instanceof Minotauro) {
                	writer.write("MINOTAURO" + " - Salud: " + monstruo.getSalud() + " - Ataque: " + monstruo.getAtaque() + "\n");
                }
            }

            System.out.println("Estado del juego guardado en 'estado_juego.txt'");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al guardar el estado del juego.");
        }
    }
}
