package lab01;
import java.util.*;

public class Ejercicio4 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String [] soldados = new String[5];
		int [] nVida = new int[5];
		
		for(int i=0; i<soldados.length; i++) {
			System.out.print("Soldado " + (i+1) + " -> ");
			soldados[i]=sc.next();
			System.out.print("Nivel de vida -> ");
			nVida[i]=sc.nextInt();
		}
		
		for(int j=0; j<soldados.length; j++) {
			System.out.print("Soldado " + (j+1) + ": " + soldados[j]);
			System.out.println(", nivel de vida " + nVida[j]);
		}
	}
}
