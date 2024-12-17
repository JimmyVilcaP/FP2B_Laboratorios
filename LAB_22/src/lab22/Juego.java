package lab22;
import java.util.*;

public class Juego {
    private Mapa mapa;
    private Reino reino1, reino2;
    private Scanner scanner;
    private Random random = new Random();

    public Juego(String reino1, String reino2) {
    	String[] territorio = {"bosque", "campo abierto", "montaña", "desierto", "playa"};
    	int ind = (int)(Math.random()*5);
    	String terreno= territorio[ind];
    	
        this.mapa = new Mapa(10, terreno);
        System.out.println("TERRENO DEL MAPA: "+terreno+"\n");
        this.reino1 = new Reino(reino1, 1);
        this.reino2 = new Reino(reino2, 2);
        this.scanner = new Scanner(System.in);
        if(terreno.equalsIgnoreCase("bosque")) {
        	if(reino1.equalsIgnoreCase("Inglaterra") || reino1.equalsIgnoreCase("Sacro Imperio")) darBono(this.reino1);
        	if (reino2.equalsIgnoreCase("Inglaterra") || reino1.equalsIgnoreCase("Sacro Imperio")) darBono(this.reino2);
        } else if(terreno.equalsIgnoreCase("campo abierto")) {
        	if(reino1.equalsIgnoreCase("Francia") || reino1.equalsIgnoreCase("Sacro Imperio")) darBono(this.reino1);
        	if(reino2.equalsIgnoreCase("Francia") || reino2.equalsIgnoreCase("Sacro Imperio")) darBono(this.reino2);
        } else if(terreno.equalsIgnoreCase("montaña")) {
        	if(reino1.equalsIgnoreCase("Castilla Aragón")) darBono(this.reino1);
        	if(reino2.equalsIgnoreCase("Castilla Aragón")) darBono(this.reino2);
        } else if(terreno.equalsIgnoreCase("desierto")) {
        	if(reino1.equalsIgnoreCase("Moros")) darBono(this.reino1);
        	if(reino2.equalsIgnoreCase("Moros")) darBono(this.reino2);
        } else if(terreno.equalsIgnoreCase("playa")) {
        	if(reino1.equalsIgnoreCase("Sacro Imperio")) darBono(this.reino1);
        	if(reino2.equalsIgnoreCase("Sacro Imperio")) darBono(this.reino2);
        }
        
        iniciarJuego();
    }
    
    private void darBono(Reino reino) {
        Ejercito ejercito = reino.getEjercito();

        for (Soldado soldado : ejercito.getMisSoldados()) {
            soldado.setVidaActual(soldado.getVidaActual() + 1);
        }
        
    	System.out.println("Bono dado al reino "+reino.getNombre());
    	System.out.println();
    }
    
    public void iniciarJuego() {
        mapa.colocarSoldados(reino1);
        mapa.colocarSoldados(reino2);
        Ejercito ejercito1 = reino1.getEjercito();
        Ejercito ejercito2 = reino2.getEjercito();
        
        boolean juegoActivo = true;
        mapa.mostrarTablero();

        while (juegoActivo) {
        	if (!verificarGanador(ejercito1, ejercito2)) break;
            System.out.println("Turno de " + reino1.getNombre() + " " + ejercito1.getID());            
            moverSoldado(ejercito1);
            
            if (!verificarGanador(ejercito2, ejercito2)) break;
            System.out.println("Turno de " + reino2.getNombre() + " " + ejercito2.getID());
            moverSoldado(ejercito2);
        }
    }

	private int moverSoldado(Ejercito ejercito) {
        System.out.println("Ejércitos disponibles para mover en " + ejercito.getNombre() + ":");
        ejercito.mostrarDatosSoldados();
        
        
        System.out.print("Seleccione un soldado para mover (número): ");
        int solPos = scanner.nextInt();
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
	    soldado.afectarVidaPorReglas(soldadoPosicion);
	    soldadoPosicion.afectarVidaPorReglas(soldado);

	    int vidaS1 = soldado.getVidaActual();        
	    int vidaS2 = soldadoPosicion.getVidaActual();

	    int ejercito1id = soldado.getEjercitoId();
	    int ejercito2id = soldadoPosicion.getEjercitoId();
	    
	    double vidTotal = (vidaS1 + vidaS2);
	    
	    double prob1 = (vidaS1 * 100) / vidTotal;
	    prob1 = Math.round(prob1 * 100.0) / 100.0;
	    double prob2 = 100 - prob1;
	    
	    double ganador = Math.random() * 100;
	    System.out.println("¡Batalla entre " + soldado.mostrarREF() + ", prob. " + prob1 + ", vida total " + vidaS1 
	            + " y " + soldadoPosicion.mostrarREF() + ", prob. " + prob2 + ", vida total " + vidaS2 + "!");
	    System.out.println("Valor generado: " + Math.round(ganador * 100.0) / 100.0);

	    // Determinar el ganador y eliminar al perdedor del mapa
	    if (ganador > prob1) {
	        System.out.println("El soldado " + soldadoPosicion.mostrarREF() + " ha ganado la batalla.");
	        if (ejercito1id == 1) mapa.eliminarSoldadoDelTablero(soldado, reino1);
	        else mapa.eliminarSoldadoDelTablero(soldado, reino2);
	        return false; // El soldado 1 ha perdido
	    } else {
	        System.out.println("El ejercito " + soldado.mostrarREF() + " ha ganado la batalla.");
	        if (ejercito2id == 1) mapa.eliminarSoldadoDelTablero(soldadoPosicion, reino1);
	        else mapa.eliminarSoldadoDelTablero(soldadoPosicion, reino2);
	        return true; // El soldado 2 ha perdido
	    }
	}
    
    private boolean verificarGanador(Ejercito ejercito1, Ejercito ejercito2) {
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