package lab22;

import java.util.*;

public class VideoJuego22 {
    public static void main(String[] args) {
    	Scanner sc=new Scanner(System.in);
    	while(true) {
	    	List<String> nombresReinos = Arrays.asList("Inglaterra", "Francia", "Sacro Imperio", "Castilla Aragón", "Moros");
	        Random random = new Random();
	
	        String reinoNombre1 = nombresReinos.get(random.nextInt(nombresReinos.size()));
	        String reinoNombre2;
	        
	        do {
	            reinoNombre2 = nombresReinos.get(random.nextInt(nombresReinos.size()));
	        } while (reinoNombre1.equals(reinoNombre2));
	              
	        Juego juego = new Juego(reinoNombre1, reinoNombre2);
	        
	        System.out.println("¡Juego finalizado entre " + reinoNombre1 + " y " + reinoNombre2 + "!\n");
	        
	        while(true) {
	        	System.out.println("Desea volver a jugar? S/N");
	        	String etd=sc.next();
	        	
	        	if(etd.equalsIgnoreCase("S")) break;
	        	else if(etd.equalsIgnoreCase("N")) System.exit(0);
	        	else System.out.println("Opción no válida");
	        }
        }
    }
}

