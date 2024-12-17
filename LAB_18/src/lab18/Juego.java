package lab18;
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
    public void iniciarJuego() {
        mapa.colocarEjercito(reino1);
        mapa.colocarEjercito(reino2);
        
        // Mostrar el tablero
        mapa.mostrarTablero();
    	
        // Mostrar todos los datos de los soldados por cada ejército
        for (Ejercito ejercito : reino1.getEjercitos()) {
            System.out.println("Datos del ejército de " + ejercito.getNombre() + " del reino " + reino1.getNombre() + ":");
            ejercito.mostrarDatosSoldados();
        }
        System.out.println();
        for (Ejercito ejercito : reino2.getEjercitos()) {
            System.out.println("Datos del ejército de " + ejercito.getNombre() + " del reino " + reino2.getNombre() + ":");
            ejercito.mostrarDatosSoldados();
        }
        System.out.println();
        // Mostrar el soldado con mayor vida por ejército
        for (Ejercito ejercito : reino1.getEjercitos()) {
            Soldado soldadoMayorVida = ejercito.obtenerSoldadoConMayorVida();
            System.out.println("Soldado con mayor vida del " + ejercito.getNombre() + ": " + soldadoMayorVida);
        }
        System.out.println();
        for (Ejercito ejercito : reino2.getEjercitos()) {
            Soldado soldadoMayorVida = ejercito.obtenerSoldadoConMayorVida();
            System.out.println("Soldado con mayor vida del " + ejercito.getNombre() + ": " + soldadoMayorVida);
        }
        System.out.println();
        // Mostrar el promedio de vida por ejército
        for (Ejercito ejercito : reino1.getEjercitos()) {
            System.out.println("Promedio de vida del ejército " + ejercito.getNombre() + " del reino " + reino1.getNombre() + ": " + ejercito.obtenerPromedioVida());
        }
        System.out.println();
        for (Ejercito ejercito : reino2.getEjercitos()) {
            System.out.println("Promedio de vida del ejército " + ejercito.getNombre() + " del reino " + reino2.getNombre() + ": " + ejercito.obtenerPromedioVida());
        }
        System.out.println();
        // Mostrar el ranking de poder de cada ejército
        for (Ejercito ejercito : reino1.getEjercitos()) {
            System.out.println("Ranking de poder del ejército " + ejercito.getNombre() + " del reino " + reino1.getNombre() + ":");
            ejercito.mostrarRanking();
        }
        System.out.println();
        for (Ejercito ejercito : reino2.getEjercitos()) {
            System.out.println("Ranking de poder del ejército " + ejercito.getNombre() + " del reino " + reino2.getNombre() + ":");
            ejercito.mostrarRanking();
        }
        System.out.println();
        determinarGanador();
    }

    public void determinarGanador() {
        double sumaPromedioReino1 = 0;
        double sumaPromedioReino2 = 0;
        int totalEjercitosReino1 = 0;
        int totalEjercitosReino2 = 0;

        for (Ejercito ejercito : reino1.getEjercitos()) {
            sumaPromedioReino1 += ejercito.obtenerPromedioVida();
            totalEjercitosReino1++;
        }

        for (Ejercito ejercito : reino2.getEjercitos()) {
            sumaPromedioReino2 += ejercito.obtenerPromedioVida();
            totalEjercitosReino2++;
        }

        double promedioTotalReino1 = sumaPromedioReino1 / totalEjercitosReino1;
        double promedioTotalReino2 = sumaPromedioReino2 / totalEjercitosReino2;

        System.out.println("Promedio de vida total del reino " + reino1.getNombre() + ": " + promedioTotalReino1);
        System.out.println("Promedio de vida total del reino " + reino2.getNombre() + ": " + promedioTotalReino2);

        if (promedioTotalReino1 > promedioTotalReino2) {
            System.out.println("El reino " + reino1.getNombre() + " ha ganado la batalla.");
        } else if (promedioTotalReino1 < promedioTotalReino2) {
            System.out.println("El reino " + reino2.getNombre() + " ha ganado la batalla.");
        } else {
            System.out.println("La batalla ha terminado en empate.");
        }
    }
}