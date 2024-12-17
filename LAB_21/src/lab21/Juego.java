package lab21;
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

        mostrarDatos(ejercito1, ejercito2);
        
        determinarGanador(ejercito1, ejercito2);
        mapa.mostrarTablero();
    }

    private void determinarGanador(Ejercito e1, Ejercito e2) {
    	System.out.print("VIDA TOTAL EJERCITO 1: ");
        System.out.println(e1.vidaTotal());      
        System.out.print("VIDA TOTAL EJERCITO 2: ");
        System.out.println(e2.vidaTotal());    
        
        double vidTotal = (e1.vidaTotal() + e2.vidaTotal());
        double prob1 = (e1.vidaTotal()*100)/vidTotal;
        prob1 = Math.round(prob1 * 100.0) / 100.0;
        double ganador = Math.random() * 100;
        double resultado = Math.round(ganador * 100.0) / 100.0;
        System.out.println(reino1.getNombre()+"-> 0% a "+prob1+"%, "+reino2.getNombre()+"-> "+(prob1+0.01)+"% a "+100+"%");
        System.out.println("PROBABLIDAD GANADORA: "+resultado+"%");
    	if(ganador > prob1) System.out.println("EL REINO DE "+reino2.getNombre()+" ES EL GANADOR");
    	else System.out.println("EL REINO DE "+reino1.getNombre()+" ES EL GANADOR");
	}

	private void mostrarDatos(Ejercito ejercito1, Ejercito ejercito2) {
        System.out.println("||| DATOS DEL REINO DE "+reino1.getNombre()+" |||");
		System.out.println("Ejército 1");
        ejercito1.mostrarDatosSoldados();
        System.out.println("\nSoldado con mayor vida");
        System.out.println(ejercito1.obtenerSoldadoConMayorVida());
        System.out.println("\nPromedio de vida soldados");
        System.out.println(ejercito1.obtenerPromedioVida());        
        System.out.println("\nRanking de poder VIDA");
        ejercito1.mostrarRanking();
        
        System.out.println();
        System.out.println("||| DATOS DEL REINO DE "+reino2.getNombre()+" |||");
        System.out.println("Ejército 2");
        ejercito2.mostrarDatosSoldados();
        System.out.println("\nSoldado con mayor vida");
        System.out.println(ejercito2.obtenerSoldadoConMayorVida());
        System.out.println("\nPromedio de vida soldados");
        System.out.println(ejercito2.obtenerPromedioVida());        
        System.out.println("\nRanking de poder VIDA");
        ejercito2.mostrarRanking();
        System.out.println();
	}
}