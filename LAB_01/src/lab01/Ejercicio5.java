package lab01;
import java.util.*;

public class Ejercicio5 {
	public static void main(String[] args) {
		Random rand=new Random();
		String [] ejercito1 = new String[rand.nextInt(5)+1];
		String [] ejercito2 = new String[rand.nextInt(5)+1];
		
		inicializarEjercito(ejercito1, ejercito2);
		mostrarEjercito(ejercito1, ejercito2);
		mostrarEGanador(ejercito1, ejercito2);
	}

	public static void inicializarEjercito (String[] e1, String[] e2) {
		for(int i=0; i<e1.length; i++) {
			e1[i]=("Soldado"+i);
		}
		for(int j=0; j<e2.length; j++) {
			e2[j]=("Soldado"+j);
		}
	}
	public static void mostrarEjercito (String[] e1, String[] e2) {
		System.out.println("EJÉRCITO 1");
		for( int i=0; i<e1.length; i++) {
			System.out.println(e1[i]);
		}
		System.out.println("EJÉRCITO 2");
		for( int j=0; j<e2.length; j++) {
			System.out.println(e2[j]);
		}
	}
	public static void mostrarEGanador (String[] e1, String[] e2) {
		if(e1.length > e2.length) {
			System.out.println("EL GANADOR ES EL EJÉRCITO 1");
		}
		else if (e1.length < e2.length) {
			System.out.println("EL GANADOR ES EL EJÉRCITO 2");
		}
		else if (e1.length == e2.length) {
			System.out.println("NO HAY GANADOR, EMPATE");
		}
	}
}
