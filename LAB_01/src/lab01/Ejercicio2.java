package lab01;

//Laboratorio Nro 1
//Autor: Jimmy Vilca Peralta
import java.util.*;

public class Ejercicio2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Random rand=new Random();
		String s1, s2, s3, s4, s5;
		int v1, v2, v3, v4, v5;
		
		System.out.print("Soldado número 1 -> ");
		s1=sc.nextLine();
		v1=rand.nextInt(5)+1;
		System.out.print("Soldado número 2 -> ");
		s2=sc.nextLine();
		v2=rand.nextInt(5)+1;
		System.out.print("Soldado número 3 -> ");
		s3=sc.nextLine();
		v3=rand.nextInt(5)+1;
		System.out.print("Soldado número 4 -> ");
		s4=sc.nextLine();
		v4=rand.nextInt(5)+1;
		System.out.print("Soldado número 5 -> ");
		s5=sc.nextLine();
		v5=rand.nextInt(5)+1;

		System.out.println("Soldado 1: " + s1 + ", vida " + v1);
		System.out.println("Soldado 2: " + s2 + ", vida " + v2);
		System.out.println("Soldado 3: " + s3 + ", vida " + v3);
		System.out.println("Soldado 4: " + s4 + ", vida " + v4);
		System.out.println("Soldado 5: " + s5 + ", vida " + v5);
	}
}
