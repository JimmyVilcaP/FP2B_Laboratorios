package lab01;

//Laboratorio Nro 1
//Autor: Jimmy Vilca Peralta
import java.util.*;

public class Ejercicio1 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String s1, s2, s3, s4, s5;
		
		System.out.print("Soldado número 1 -> ");
		s1=sc.nextLine();
		System.out.print("Soldado número 2 -> ");
		s2=sc.nextLine();
		System.out.print("Soldado número 3 -> ");
		s3=sc.nextLine();
		System.out.print("Soldado número 4 -> ");
		s4=sc.nextLine();
		System.out.print("Soldado número 5 -> ");
		s5=sc.nextLine();
		
		System.out.println("Soldado 1: " + s1);
		System.out.println("Soldado 2: " + s2);
		System.out.println("Soldado 3: " + s3);
		System.out.println("Soldado 4: " + s4);
		System.out.println("Soldado 5: " + s5);
	}
}
