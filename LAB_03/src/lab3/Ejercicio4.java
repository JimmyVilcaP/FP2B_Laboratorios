package lab3;
import java.util.*;

public class Ejercicio4 {
	public static void main(String[] args) {
		Soldado [] misSoldados = new Soldado[5];
		
		crearSoldados(misSoldados);
		mostrarSoldados(misSoldados);		
	}
	static void crearSoldados(Soldado [] s) {
		Scanner sc=new Scanner(System.in);
		String name;
		int health;
		
		for(int i=0; i<s.length; i++) {
			System.out.print("Nombre del soldado -> ");
			name=sc.next();
			System.out.print("Nivel de vida -> ");
			health=sc.nextInt();
			s[i]=new Soldado();
			s[i].setNombre(name);
			s[i].setVida(health);
		}
	}
	static void mostrarSoldados(Soldado [] ss) {
		for(int i=0; i<ss.length; i++) {
			System.out.print((i+1)+". Soldado: "+ss[i].getNombre());
			System.out.println(", "+ss[i].getVida()+" puntos de vida.");
		}
	}
}
