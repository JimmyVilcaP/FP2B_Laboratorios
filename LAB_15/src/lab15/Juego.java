package lab15;
import java.util.Random;
import java.util.Scanner;

public class Juego {
    private Tablero tablero;
    private Reino reino1, reino2;
    private Scanner scanner;
    private Random random;

    public Juego(String reinoNombre1, String reinoNombre2) {
        this.tablero = new Tablero(10); // Tamaño del tablero
        this.reino1 = new Reino(reinoNombre1, 1);
        this.reino2 = new Reino(reinoNombre2, 2);
        this.scanner = new Scanner(System.in);
        this.random = new Random();
        iniciarJuego();
    }

    private void iniciarJuego() {
        System.out.println("Seleccione el modo de juego:");
        System.out.println("1. Modo Automático (basado en métrica)");
        System.out.println("2. Modo Manual (Humano contra Humano)");
        System.out.print("Opción: ");
        int modo = scanner.nextInt();

        if (modo == 1) {
            iniciarModoAutomatico();
        } else if (modo == 2) {
            iniciarModoManual();
        } else {
            System.out.println("Opción no válida.");
        }
    }

    // Modo Automático: Se enfrenta en función de una métrica (total de soldados)
    private void iniciarModoAutomatico() {
        int totalSoldadosReino1 = contarSoldados(reino1);
        int totalSoldadosReino2 = contarSoldados(reino2);

        System.out.println("Modo Automático:");
        System.out.println(reino1.getNombre() + " tiene " + totalSoldadosReino1 + " soldados.");
        System.out.println(reino2.getNombre() + " tiene " + totalSoldadosReino2 + " soldados.");

        if (totalSoldadosReino1 > totalSoldadosReino2) {
            System.out.println("¡" + reino1.getNombre() + " gana la batalla!");
        } else if (totalSoldadosReino2 > totalSoldadosReino1) {
            System.out.println("¡" + reino2.getNombre() + " gana la batalla!");
        } else {
            System.out.println("Es un empate.");
        }
    }

    private int contarSoldados(Reino reino) {
        return reino.getEjercitos().stream()
                .mapToInt(ejercito -> ejercito.getMisSoldados().size())
                .sum();
    }

    // Modo Manual: Humano contra Humano
    private void iniciarModoManual() {
        // Colocar los ejércitos de ambos reinos en el tablero
        tablero.colocarEjercito(reino1);
        tablero.colocarEjercito(reino2);

        System.out.println("Modo Manual iniciado:");
        tablero.mostrarTablero();

        boolean juegoActivo = true;
        while (juegoActivo) {
            // Movimiento y batalla, alternando entre reinos
            System.out.println("Turno de " + reino1.getNombre());
            moverEjercitoHumano(reino1);

            System.out.println("Turno de " + reino2.getNombre());
            moverEjercitoHumano(reino2);

            tablero.mostrarTablero();
            juegoActivo = verificarGanador();
        }
    }

    private void moverEjercitoHumano(Reino reino) {
        System.out.println("Ejércitos disponibles para mover en " + reino.getNombre() + ":");
        for (int i = 0; i < reino.getEjercitos().size(); i++) {
            System.out.println((i + 1) + ". " + reino.getEjercitos().get(i).toString()+ " | " + reino.getEjercitos().get(i).getNombre());
        }

        System.out.print("Seleccione un ejército para mover (número): ");
        int ejercitoIndex = scanner.nextInt() - 1;
        Ejercito ejercito = reino.getEjercitos().get(ejercitoIndex);

        // Leer columna como letra
        System.out.print("Ingrese columna (A-J): ");
        String columnaStr = scanner.next().toUpperCase();
        int columna = convertirColumnaALetra(columnaStr);

        // Validar que la columna esté dentro del rango
        if (columna < 0 || columna >= tablero.getSize()) {
            System.out.println("Columna fuera de los límites. Intente de nuevo.");
            return;
        }
        
        // Leer fila como número
        System.out.print("Ingrese fila (1-10): ");
        int fila = scanner.nextInt() - 1;

        // Validar que la fila esté dentro del rango
        if (fila < 0 || fila >= tablero.getSize()) {
            System.out.println("Fila fuera de los límites. Intente de nuevo.");
            return;
        }
        
        // Verificar si la posición está ocupada por un ejército enemigo
        Ejercito ejercitoEnPosicion = tablero.getEjercitoEnPosicion(fila, columna);
        if (ejercitoEnPosicion == null) {
            tablero.colocarEjercitoEnPosicion(ejercito, fila, columna);
            System.out.println("Ejército movido a (" + (fila + 1) + ", " + columnaStr + ")");
        } else if (ejercitoEnPosicion.getReinoID() != ejercito.getReinoID()) {
            System.out.println("Posición ocupada por un ejército enemigo. Iniciando batalla.");
            iniciarBatalla(ejercito, ejercitoEnPosicion); // Iniciar batalla entre los dos ejércitos en la misma posición
        } else {
            System.out.println("Posición ocupada por otro ejército aliado.");
        }
        
        tablero.mostrarTablero();
    }

    private int convertirColumnaALetra(String columna) {
        return columna.charAt(0) - 'A';
    }

    private void iniciarBatalla(Ejercito ejercito1, Ejercito ejercito2) {
        System.out.println("¡Batalla entre " + ejercito1 + " y " + ejercito2 + "!");

        TableroBatalla tableroBatalla = new TableroBatalla(ejercito1.getMisSoldados(), ejercito2.getMisSoldados());
        tableroBatalla.mostrarTablero();

        while (!ejercito1.getMisSoldados().isEmpty() && !ejercito2.getMisSoldados().isEmpty()) {
            System.out.println("Turno para " + ejercito1.getNombre());
            Soldado soldado1 = seleccionarSoldado(ejercito1);
            moverSoldadoEnTableroBatalla(tableroBatalla, soldado1);

            if (ejercito2.getMisSoldados().isEmpty()) break;

            System.out.println("Turno para " + ejercito2.getNombre());
            Soldado soldado2 = seleccionarSoldado(ejercito2);
            moverSoldadoEnTableroBatalla(tableroBatalla, soldado2);

            // Verificar si alguno de los ejércitos se queda sin soldados
            if (ejercito1.getMisSoldados().isEmpty() || ejercito2.getMisSoldados().isEmpty()) {
                break; 
            }
        }

        // Eliminar el ejército derrotado y mostrar el resultado
        if (ejercito1.getMisSoldados().isEmpty()) {
            System.out.println("¡" + ejercito2.getNombre() + " ha ganado la batalla!");
            tablero.eliminarEjercitoDelTablero(ejercito1);
        } else if (ejercito2.getMisSoldados().isEmpty()) {
            System.out.println("¡" + ejercito1.getNombre() + " ha ganado la batalla!");
            tablero.eliminarEjercitoDelTablero(ejercito2);
        }
    }

    private Soldado seleccionarSoldado(Ejercito ejercito) {
        System.out.println("Seleccione un soldado para mover:");
        for (int i = 0; i < ejercito.getMisSoldados().size(); i++) {
            System.out.println((i + 1) + ". " + ejercito.getMisSoldados().get(i).toString() + " | " + ejercito.getMisSoldados().get(i).getNombre());
        }
        int indice = scanner.nextInt() - 1;
        return ejercito.getMisSoldados().get(indice);
    }
    private void moverSoldadoEnTableroBatalla(TableroBatalla tableroBatalla, Soldado soldado) {
	    while(true) { 
    		System.out.print("Ingrese columna (A-J): ");
	        String columnaStr = scanner.next().toUpperCase();
	        int columna = convertirColumnaALetra(columnaStr);
	
	        System.out.print("Ingrese fila (1-10): ");
	        int fila = scanner.nextInt() - 1;
	        
	        int comp = tableroBatalla.moverSoldado(soldado, fila, columna);
	        if(comp == 1 || comp==0) break;
	        else if(comp == -1) continue;
	    }    
        tableroBatalla.mostrarTablero();
    }

    private boolean verificarGanador() {
        boolean reino1TieneEjercitos = reino1.getEjercitos().stream()
                .anyMatch(ejercito -> !ejercito.getMisSoldados().isEmpty());
        boolean reino2TieneEjercitos = reino2.getEjercitos().stream()
                .anyMatch(ejercito -> !ejercito.getMisSoldados().isEmpty());

        if (!reino1TieneEjercitos) {
            System.out.println("¡" + reino2.getNombre() + " ha ganado la guerra!");
            return false;
        } else if (!reino2TieneEjercitos) {
            System.out.println("¡" + reino1.getNombre() + " ha ganado la guerra!");
            return false;
        }

        return true;
    }
}
