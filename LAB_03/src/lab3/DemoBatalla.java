package lab3;
import java.util.*;

public class DemoBatalla {
	public static void main(String [] args){
		Nave [] misNaves = new Nave[3];
		Scanner sc = new Scanner(System.in);
		String nomb, col;
		int fil, punt;
		boolean est;
		for (int i = 0; i < misNaves.length; i++) {
			System.out.println("Nave " + (i+1));
			System.out.print("Nombre: ");
			nomb = sc.next();
			System.out.println("Fila ");
			fil = sc.nextInt();
			System.out.print("Columna: ");
			col = sc.next();
			System.out.print("Estado: ");
			est = sc.nextBoolean();
			System.out.print("Puntos: ");
			punt = sc.nextInt();
			System.out.println();
			misNaves[i] = new Nave(); //Se crea un objeto Nave y se asigna su referencia a misNaves
			misNaves[i].setNombre(nomb);
			misNaves[i].setFila(fil);
			misNaves[i].setColumna(col);
			misNaves[i].setEstado(est);
			misNaves[i].setPuntos(punt);
		}
		System.out.println("MIS NAVES");
		mostrarNaves(misNaves);
		mostrarPorNombre(misNaves);
		System.out.println();
		mostrarPorPuntos(misNaves);
		System.out.println();
		System.out.println("\nNave con mayor número de puntos: " + mostrarMayorPuntos(misNaves)+"\n");
		System.out.println("MIS NAVES ALEATORIO");
		mostrarNaves(navesRandom(misNaves));
		}
		//Método para mostrar todas las naves
		public static void mostrarNaves(Nave [] flota) {
			for(int i=0; i<flota.length; i++) {
				System.out.println((i+1)
					   +": "+flota[i].getNombre()+", PUNTOS "
					   +flota[i].getPuntos()+", ESTADO "
					   +flota[i].getEstado());
			}
			System.out.println();
		}
		//Método para mostrar todas las naves de un nombre que se pide por teclado
		public static void mostrarPorNombre(Nave [] flota) {
			Scanner sc=new Scanner(System.in);
			ArrayList <Integer> pos=new ArrayList<>();
			System.out.print("Ingrese el nombre de la nave a buscar: ");
			String n=sc.nextLine();
			for(int i=0; i<flota.length; i++) {
				if(n.equalsIgnoreCase(flota[i].getNombre())) {
					pos.add(i);
				}
			}
			if(pos.size()!=0) {
				System.out.println("NAVES ENCONTRADAS");
				for(int j=0; j<pos.size(); j++) {
					System.out.println((j+1)
							   +": "+flota[pos.get(j)].getNombre()+", PUNTOS "
							   +flota[pos.get(j)].getPuntos()+", ESTADO "
							   +flota[pos.get(j)].getEstado());
				}
			} else 
				System.out.println("No se encontraron naves.");
		}
		//Método para mostrar todas las naves con un número de puntos inferior o igual
		//al número de puntos que se pide por teclado
		public static void mostrarPorPuntos(Nave [] flota){
			Scanner sc=new Scanner(System.in);
			System.out.print("Ingrese el número de puntos: ");
			int num=sc.nextInt();
			int cont=0;
			for(int i=0; i<flota.length; i++) {
				if(num >= flota[i].getPuntos()) {
					System.out.println((i+1)
							   +": "+flota[i].getNombre()+", PUNTOS "
							   +flota[i].getPuntos()+", ESTADO "
							   +flota[i].getEstado());
					cont++;
				}
				if(i==flota.length-1 && cont==0) {
					System.out.print("No se encontraron naves con el nivel de puntos ingresado.");
				}
			}
		}
		//Método que devuelve la Nave con mayor número de Puntos
		public static Nave mostrarMayorPuntos(Nave [] flota){
			int may=0;
			for(int i=1; i<flota.length; i++) {
				if(flota[may].getPuntos()<flota[i].getPuntos())
					may=i;
			}
			return flota[may];
		}
		//Crear un método que devuelva un nuevo arreglo de objetos con todos los objetos previamente ingresados 
		//pero aleatoriamente desordenados
		public static Nave[] navesRandom(Nave [] flota) {
			Nave [] newNaves = new Nave[flota.length];
			ArrayList <Integer> val=new ArrayList<>();
			for(int j=0; j<flota.length; j++) {
				val.add(j);
			}
			Collections.shuffle(val);
			for(int i=0; i<newNaves.length; i++) {
				newNaves[i] = new Nave();
				newNaves[i].setNombre(flota[val.get(i)].getNombre());
				newNaves[i].setFila(flota[val.get(i)].getFila());
				newNaves[i].setColumna(flota[val.get(i)].getColumna());
				newNaves[i].setEstado(flota[val.get(i)].getEstado());
				newNaves[i].setPuntos(flota[val.get(i)].getPuntos());
			}
			return newNaves;
		}
}
