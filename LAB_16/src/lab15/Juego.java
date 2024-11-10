package lab15;
import java.util.Random;
import java.util.Scanner;

public class Juego {
    private Mapa mapa;
    private Reino reino1, reino2;
    private Scanner scanner;
    private Random random = new Random();

    public Juego(String reinoNombre1, String reinoNombre2) {
    	String[] terrenos_posibles = {"bosque", "campo abierto", "montaña", "desierto", "playa"};
    	int ind = (int)(Math.random()*5);
    	String terreno= terrenos_posibles[ind];

        this.mapa = new Mapa(10, terreno); // Tamaño del tablero
        this.reino1 = new Reino(reinoNombre1, 1);
        this.reino2 = new Reino(reinoNombre2, 2);
        this.scanner = new Scanner(System.in);
        iniciarJuego();
    }

    private void iniciarJuego() {
        System.out.println("Seleccione el modo de juego:\n"
        				  +"1. Modo Automático (basado en métrica)\n"
        				  +"2. Modo Manual (Humano contra Humano)");
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

    // Modo Automático: Se enfrentan en función de una métrica (total de soldados)
    private void iniciarModoAutomatico() {
        // Calculamos las métricas para cada reino
        int totalSoldadosReino1 = contarSoldados(reino1);
        int totalSoldadosReino2 = contarSoldados(reino2);

        int vidaTotalReino1 = calcularVidaTotal(reino1);
        int vidaTotalReino2 = calcularVidaTotal(reino2);

        int totalEjercitosReino1 = reino1.getEjercitos().size();
        int totalEjercitosReino2 = reino2.getEjercitos().size();

        // Mostramos las métricas
        System.out.println("Modo Automático:");
        System.out.println(reino1.getNombre() + " tiene " + totalSoldadosReino1 + " soldados.");
        System.out.println(reino2.getNombre() + " tiene " + totalSoldadosReino2 + " soldados.\n");
        System.out.println(reino1.getNombre() + " tiene " + vidaTotalReino1 + " vida total.");
        System.out.println(reino2.getNombre() + " tiene " + vidaTotalReino2 + " vida total.\n");
        System.out.println(reino1.getNombre() + " tiene " + totalEjercitosReino1 + " ejércitos.");
        System.out.println(reino2.getNombre() + " tiene " + totalEjercitosReino2 + " ejércitos.");

        // Determinamos al ganador de cada métrica
        String ganadorSoldados = totalSoldadosReino1 > totalSoldadosReino2 ? reino1.getNombre() : totalSoldadosReino1 < totalSoldadosReino2 ? reino2.getNombre() : "Empate";
        String ganadorVida = vidaTotalReino1 > vidaTotalReino2 ? reino1.getNombre() : vidaTotalReino1 < vidaTotalReino2 ? reino2.getNombre() : "Empate";
        String ganadorEjercitos = totalEjercitosReino1 > totalEjercitosReino2 ? reino1.getNombre() : totalEjercitosReino1 < totalEjercitosReino2 ? reino2.getNombre() : "Empate";

        // Imprimir los resultados de cada métrica
        System.out.println("\nMétrica de Total de Soldados: ");
        System.out.println("Ganador: " + ganadorSoldados);

        System.out.println("\nMétrica de Vida Total: ");
        System.out.println("Ganador: " + ganadorVida);

        System.out.println("\nMétrica de Total de Ejércitos: ");
        System.out.println("Ganador: " + ganadorEjercitos);

        // Determinamos al ganador final
        int puntosReino1 = 0;
        int puntosReino2 = 0;

        if (!ganadorSoldados.equals("Empate")) {
            puntosReino1 = ganadorSoldados.equals(reino1.getNombre()) ? 1 : 0;
            puntosReino2 = ganadorSoldados.equals(reino2.getNombre()) ? 1 : 0;
        }
        
        if (!ganadorVida.equals("Empate")) {
            puntosReino1 += ganadorVida.equals(reino1.getNombre()) ? 1 : 0;
            puntosReino2 += ganadorVida.equals(reino2.getNombre()) ? 1 : 0;
        }
        
        if (!ganadorEjercitos.equals("Empate")) {
            puntosReino1 += ganadorEjercitos.equals(reino1.getNombre()) ? 1 : 0;
            puntosReino2 += ganadorEjercitos.equals(reino2.getNombre()) ? 1 : 0;
        }

        // El reino con más puntos es el ganador
        if (puntosReino1 > puntosReino2) {
            System.out.println("\n¡" + reino1.getNombre() + " gana la guerra!");
        } else if (puntosReino2 > puntosReino1) {
            System.out.println("\n¡" + reino2.getNombre() + " gana la guerra!");
        } else {
            System.out.println("\nEs un empate en la guerra.");
        }
    }

    // Método para contar el total de soldados de un reino
    private int contarSoldados(Reino reino) {
        int totalSoldados = 0;
        for (Ejercito ejercito : reino.getEjercitos()) {
            totalSoldados += ejercito.getMisSoldados().size();
        }
        return totalSoldados;
    }

    // Método para calcular la vida total de los soldados de un reino
    private int calcularVidaTotal(Reino reino) {
        int vidaTotal = 0;
        for (Ejercito ejercito : reino.getEjercitos()) {
            for (Soldado soldado : ejercito.getMisSoldados()) {
                vidaTotal += soldado.getVidaActual();
            }
        }
        return vidaTotal;
    }


    // Modo Manual: Humano contra Humano
    private void iniciarModoManual() {
        // Colocar los ejércitos de ambos reinos en el tablero
        mapa.colocarEjercito(reino1);
        mapa.colocarEjercito(reino2);

        System.out.println("Modo Manual iniciado:");
        mapa.mostrarTablero();

        boolean juegoActivo = true;
        while (juegoActivo) {
            // Movimiento y batalla, alternando entre reinos
            System.out.println("Turno de " + reino1.getNombre());
            moverEjercitoHumano(reino1);

            System.out.println("Turno de " + reino2.getNombre());
            moverEjercitoHumano(reino2);

            mapa.mostrarTablero();
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
        int fila, columna;
        String columnaStr;
        
        while(true) {
	        // Leer columna como letra
	        System.out.print("Ingrese columna (A-J): ");
	        columnaStr = scanner.next().toUpperCase();
	        columna = convertirColumnaALetra(columnaStr);
	
	        // Validar que la columna esté dentro del rango
	        if (columna < 0 || columna >= mapa.getSize()) {
	            System.out.println("Columna fuera de los límites. Intente de nuevo.");
	            continue;
	        }
	        
	        // Leer fila como número
	        System.out.print("Ingrese fila (1-10): ");
	        fila = scanner.nextInt() - 1;
	       
	        // Validar que la fila esté dentro del rango
	        if (fila < 0 || fila >= mapa.getSize()) {
	            System.out.println("Fila fuera de los límites. Intente de nuevo.");
	            continue;
	        }
	        break;
        }
        
        // Verificar si la posición está ocupada por un ejército enemigo
        Ejercito ejercitoEnPosicion = mapa.getEjercitoEnPosicion(fila, columna);
        if (ejercitoEnPosicion == null) {
            mapa.colocarEjercitoEnPosicion(ejercito, fila, columna);
            System.out.println("Ejército movido a (" + (fila + 1) + ", " + columnaStr + ")");
        } else if (ejercitoEnPosicion.getReinoID() != ejercito.getReinoID()) {
            System.out.println("Posición ocupada por un ejército enemigo. Iniciando batalla.");
            iniciarBatalla(ejercito, ejercitoEnPosicion); // Iniciar batalla entre los dos ejércitos en la misma posición
        } else {
            System.out.println("Posición ocupada por otro ejército aliado.");
        }
        
        mapa.mostrarTablero();
    }

    private int convertirColumnaALetra(String columna) {
        return columna.charAt(0) - 'A';
    }

    private void iniciarBatalla(Ejercito ejercito1, Ejercito ejercito2) {
        System.out.println("¡Batalla entre " + ejercito1 + " y " + ejercito2 + "!");
        TableroBatalla tableroBatalla = new TableroBatalla(ejercito1.getMisSoldados(), ejercito2.getMisSoldados(), ejercito1.getReinoID());
        tableroBatalla.mostrarTablero();

        while (!ejercito1.getMisSoldados().isEmpty() && !ejercito2.getMisSoldados().isEmpty()) {
            System.out.println("Turno para " + ejercito1.getReinoNombre() + " " + ejercito1.getNombre());
            Soldado soldado1 = seleccionarSoldado(ejercito1);
            moverSoldadoEnTableroBatalla(tableroBatalla, soldado1);

            if (ejercito2.getMisSoldados().isEmpty()) break;

            System.out.println("Turno para " + ejercito2.getReinoNombre() + " " + ejercito2.getNombre());
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
            mapa.eliminarEjercitoDelTablero(ejercito1);
        } else if (ejercito2.getMisSoldados().isEmpty()) {
            System.out.println("¡" + ejercito1.getNombre() + " ha ganado la batalla!");
            mapa.eliminarEjercitoDelTablero(ejercito2);
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
