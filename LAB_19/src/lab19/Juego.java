package lab19;
import java.util.*;

public class Juego {
    private Mapa mapa;
    private Ejercito ejercito1, ejercito2;
    private Scanner scanner;
    private Random random = new Random();

    public Juego(String ejercito1, String ejercito2) {
        this.mapa = new Mapa(10);
        
        this.ejercito1 = new Ejercito(ejercito1, 1);
        this.ejercito2 = new Ejercito(ejercito2, 2);
        this.scanner = new Scanner(System.in);

        iniciarJuego();
    }

    public void iniciarJuego() {
        mapa.colocarSoldados(ejercito1);
        mapa.colocarSoldados(ejercito2);
        
        // Mostrar el tablero
        mapa.mostrarTablero();
        
        boolean juegoActivo = true;

        while (juegoActivo) {
        	if (!verificarGanador()) break;
            System.out.println("Turno de " + ejercito1.getNombre() + " " + ejercito1.getID());            
            moverSoldado(ejercito1);
            
            if (!verificarGanador()) break;
            System.out.println("Turno de " + ejercito2.getNombre() + " " + ejercito2.getID());
            moverSoldado(ejercito2);
        }
    }

    private int moverSoldado(Ejercito ejercito) {
        System.out.println("Ejércitos disponibles para mover en " + ejercito.getNombre() + ":");
        ejercito.mostrarDatosSoldados();
        
        
        System.out.print("Seleccione un soldado para mover (número): ");
        int solPos = scanner.nextInt() - 1;
        Soldado soldado = ejercito.getMisSoldados().get(solPos);
        int fila, columna;
        String columnaStr;
        
        while(true) {
	        System.out.print("Ingrese columna (A-J): ");
	        columnaStr = scanner.next().toUpperCase();
	        columna = columnaStr.charAt(0) - 'A';
	
	        // Validar que la columna esté dentro del rango
	        if (columna < 0 || columna >= mapa.getSize()) {
	            System.out.println("Columna fuera de los límites. Intente de nuevo.");
	            continue;
	        }
	        
	        System.out.print("Ingrese fila (1-10): ");
	        fila = scanner.nextInt() - 1;
	       
	        // Validar que la fila esté dentro del rango
	        if (fila < 0 || fila >= mapa.getSize()) {
	            System.out.println("Fila fuera de los límites. Intente de nuevo.");
	            continue;
	        }
	        break;
        }
        
        Soldado soldadoPosicion = mapa.getSoldadoEnPosicion(fila, columna);
        if (soldadoPosicion == null) {
            mapa.colocarSoldadoEnPosicion(soldado, fila, columna);
            System.out.println("Ejército movido a (" + (fila + 1) + ", " + columnaStr + ")");
            mapa.mostrarTablero();
            return -1;
        } else if (soldadoPosicion.getEjercitoId() != soldado.getEjercitoId()) {
            System.out.println("Posición ocupada por un soldado enemigo. Iniciando batalla.");
            boolean pos = iniciarBatalla(soldado, soldadoPosicion);
            if(pos) { 
            	mapa.colocarSoldadoEnPosicion(soldado, fila, columna);
                System.out.println();
                mapa.mostrarTablero();
                return soldado.getEjercitoId();
            } else {
                System.out.println();
                mapa.mostrarTablero();
                return soldadoPosicion.getEjercitoId();
            }
        } else {
            System.out.println("Posición ocupada por otro ejército aliado.");
            return -1;
        }
    }
    
    private boolean iniciarBatalla(Soldado soldado, Soldado soldadoPosicion) {        
        int vidaS1 = soldado.getVidaActual();        		
        int vidaS2 = soldadoPosicion.getVidaActual();
        int ejercito1id = soldado.getEjercitoId();
        int ejercito2id = soldadoPosicion.getEjercitoId();
        
        double vidTotal = (vidaS1 + vidaS2);
        double prob1 = (vidaS1*100)/vidTotal;
        prob1 = Math.round(prob1 * 100.0) / 100.0;
        double prob2=100-prob1;
        
        double ganador = Math.random() * 100;
        System.out.println("¡Batalla entre " + soldado.mostrarREF() + ", prob. " + prob1 + ", vida total " + vidaS1 
        					+ " y " + soldadoPosicion.mostrarREF() + ", prob. " + prob2 + ", vida total " + vidaS2 + "!");
        System.out.println("Valor generado: " + Math.round(ganador * 100.0) / 100.0);
        if(ganador > prob1) {
        	System.out.println("El soldado " + soldadoPosicion.mostrarREF() + " ha ganado la batalla.");
        	if(ejercito1id == 1) mapa.eliminarSoldadoDelTablero(soldado, ejercito1);
        	else mapa.eliminarSoldadoDelTablero(soldado, ejercito2);
        	return false;
        } else {
        	System.out.println("El ejercito " + soldado.mostrarREF() + " ha ganado la batalla.");
        	if (ejercito2id == 1) mapa.eliminarSoldadoDelTablero(soldadoPosicion, ejercito1);
        	else mapa.eliminarSoldadoDelTablero(soldadoPosicion, ejercito2);
        	return true;
        }
    }
    
    private boolean verificarGanador() {
    	boolean ejercitos1 = true;
    	boolean ejercitos2 = true;
    	if(ejercito1.getMisSoldados().size()==0) ejercitos1 = false;
        if(ejercito2.getMisSoldados().size()==0) ejercitos2 = false;
    	
        if (!ejercitos1) {
            System.out.println("¡ El " + ejercito2.getNombre() + " ha ganado la guerra!");
            return false;	
        } else if (!ejercitos2) {
            System.out.println("¡ El " + ejercito1.getNombre() + " ha ganado la guerra!");
            return false;
        }

        return true;
    }
}