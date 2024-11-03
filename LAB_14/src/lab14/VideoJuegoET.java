package lab14;
import java.util.*;

public class VideoJuegoET {
	static final Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		String [] reinos =  {"Inglaterra", "Francia", "Scaro-Imperio", "Castilla-Aragón", "Moros"};
		Reino reinoJ1, reinoJ2;
		int r1, r2;
		r1=(int)(Math.random()*reinos.length);
		do {
			r2=(int)(Math.random()*reinos.length);
		} while (r1==r2);
		
		reinoJ1 = new Reino(1, reinos[r1]);
		reinoJ2 = new Reino(2, reinos[r2]);
		
		TableroEjercitos tablero = new TableroEjercitos(10, 10);

		for (Ejercito ejercito : reinoJ1.getEjercitos()) {
            tablero.posicionarEjercito(ejercito);
        }
		for (Ejercito ejercito : reinoJ2.getEjercitos()) {
            tablero.posicionarEjercito(ejercito);
        }
		Reino[] rei = {reinoJ1, reinoJ2};
		System.out.println("\t\t    TABLERO DE EJERCITOS");
		tablero.mostrarTablero(); 
		
		
	}
}
