package lab01;
import java.util.*;

public class Ejercicio3 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String [] soldados = new String[5];
		
		for(int i=0; i<soldados.length; i++) {
			System.out.print("Soldado " + (i+1) + " -> ");
			soldados[i]=sc.next();
		}
		
		for(int j=0; j<soldados.length; j++) {
			System.out.println("Soldado " + (j+1) + ": " + soldados[j]);
		}
	}
}
