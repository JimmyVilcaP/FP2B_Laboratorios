package lab17;
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
    	for (Ejercito ejercito : reino.getEjercitos()) {
            for (Soldado soldado : ejercito.getMisSoldados()) {
                soldado.setVidaActual(soldado.getVidaActual()+1);;
            }
        }
    	System.out.println("Bono dado al reino "+reino.getNombre());
    	System.out.println();
    }
    private void iniciarJuego() {
    	iniciarModoManual();

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
    private int calcularVidaTotal(Ejercito ejercito) {
        int vidaTotal = 0;
        for (Soldado soldado : ejercito.getMisSoldados()) {
            vidaTotal += soldado.getVidaActual();
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
        int bono;
        while (juegoActivo) {
            System.out.println("Turno de " + reino1.getNombre() + " " + reino1.getID());
            bono = moverEjercitoHumano(reino1);
            if(bono == 1) darBono(reino1);
            if(bono == 2) darBono(reino2);
            
            if (!verificarGanador()) {
                break;
            }

            System.out.println("Turno de " + reino2.getNombre() + " " + reino2.getID());
            bono = moverEjercitoHumano(reino2);
            if(bono == 1) darBono(reino1);
            if(bono == 2) darBono(reino2);
            
            if (!verificarGanador()) {
                break;
            }
        }

    }
    private int moverEjercitoHumano(Reino reino) {
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
        
        Ejercito ejercitoEnPosicion = mapa.getEjercitoEnPosicion(fila, columna);
        if (ejercitoEnPosicion == null) {
            mapa.colocarEjercitoEnPosicion(ejercito, fila, columna);
            System.out.println("Ejército movido a (" + (fila + 1) + ", " + columnaStr + ")");
            mapa.mostrarTablero();
            return -1;
        } else if (ejercitoEnPosicion.getReinoID() != ejercito.getReinoID()) {
            System.out.println("Posición ocupada por un ejército enemigo. Iniciando batalla.");
            boolean pos = iniciarBatalla(ejercito, ejercitoEnPosicion);
            if(pos) { 
            	mapa.colocarEjercitoEnPosicion(ejercito, fila, columna);
                System.out.println();
                mapa.mostrarTablero();
                return ejercito.getReinoID();
            } else {
                System.out.println();
                mapa.mostrarTablero();
                return ejercitoEnPosicion.getReinoID();
            }
        } else {
            System.out.println("Posición ocupada por otro ejército aliado.");
            return -1;
        }
    }
    private int convertirColumnaALetra(String columna) {
        return columna.charAt(0) - 'A';
    }
    private boolean iniciarBatalla(Ejercito ejercito1, Ejercito ejercito2) {        
        int vidaE1 = calcularVidaTotal(ejercito1);        		
        int vidaE2 = calcularVidaTotal(ejercito2);
        Reino reino1 = ejercito1.getReino();
        Reino reino2 = ejercito2.getReino();
        
        double vidTotal = (vidaE1 + vidaE2);
        double prob1 = (vidaE1*100)/vidTotal;
        prob1 = Math.round(prob1 * 100.0) / 100.0;
        double prob2=100-prob1;
        
        double ganador = Math.random() * 100;
        System.out.println("¡Batalla entre " + ejercito1 + ", prob. " + prob1 + ", vida total " + vidaE1 
        					+ " y " + ejercito2 + ", prob. " + prob2 + ", vida total " + vidaE2 + "!");
        System.out.println("Valor generado: " + ganador);
        if(ganador > prob1) {
        	System.out.println("El ejercito " + ejercito2 + " ha ganado la batalla.");
        	mapa.eliminarEjercitoDelTablero(ejercito1);
        	return false;
        } else {
        	System.out.println("El ejercito " + ejercito1 + " ha ganado la batalla.");
        	mapa.eliminarEjercitoDelTablero(ejercito2);
        	return true;
        }
    }
    private boolean verificarGanador() {
    	boolean reino1Ejercitos = true;
    	boolean reino2Ejercitos = true;
    	if(reino1.getEjercitos().size()==0) reino1Ejercitos = false;
        if(reino2.getEjercitos().size()==0) reino2Ejercitos = false;
    	
        if (!reino1Ejercitos) {
            System.out.println("¡" + reino2.getNombre() + " ha ganado la guerra!");
            return false;
        } else if (!reino2Ejercitos) {
            System.out.println("¡" + reino1.getNombre() + " ha ganado la guerra!");
            return false;
        }

        return true;
    }
}