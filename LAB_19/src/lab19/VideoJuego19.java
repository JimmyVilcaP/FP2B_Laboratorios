package lab19;
import java.util.*;

public class VideoJuego19 {
    public static void main(String[] args) {
    	Scanner sc=new Scanner(System.in);
    	while(true) {
    		String ejercito1 = "EJERCITO1";
    		String ejercito2 = "EJERCITO2";
    		
	        Juego juego = new Juego(ejercito1, ejercito2);
	        
	        System.out.println("¡Juego finalizado	!\n");
	        
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
