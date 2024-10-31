package lab4;
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
			 System.out.print("Fila: ");
			 fil = sc.nextInt();
			 System.out.print("Columna: ");
			 col = sc.next();
			 System.out.print("Estado: ");
			 est = sc.nextBoolean();
			 System.out.print("Puntos: ");
			 punt = sc.nextInt();
	
			 misNaves[i] = new Nave(); //Se crea un objeto Nave y se asigna su referencia a misNaves
	
			 misNaves[i].setNombre(nomb);
			 misNaves[i].setFila(fil);
			 misNaves[i].setColumna(col);
			 misNaves[i].setEstado(est);
			 misNaves[i].setPuntos(punt);
		}

		System.out.println("\nNaves creadas:");
		mostrarNaves(misNaves);
		mostrarPorNombre(misNaves);
		mostrarPorPuntos(misNaves);
		System.out.println("\nNave con mayor número de puntos: " + mostrarMayorPuntos(misNaves));
		
		//leer un nombre
		//mostrar los datos de la nave con dicho nombre, mensaje de “no encontrado” en caso contrario
		System.out.print("\nNombre de la nave a buscar -> ");
		String nombre=sc.next();
		int pos=busquedaLinealNombre(misNaves,nombre);
		if(pos != -1) {
			System.out.println("\nNAVE ENCONTRADA EN LA POSICIÓN "+(pos+1));
			System.out.println((pos+1) + ": "+misNaves[pos]);
		} else {
			System.out.println("\nNAVE NO ENCONTRADA");
		}
		ordenarPorPuntosBurbuja(misNaves);
		mostrarNaves(misNaves);
		ordenarPorNombreBurbuja(misNaves);
		mostrarNaves(misNaves);
		
		//mostrar los datos de la nave con dicho nombre, mensaje de “no encontrado” en caso contrario
		System.out.print("\nNombre de la nave a buscar -> ");
		nombre=sc.next();
		pos=busquedaBinariaNombre(misNaves,nombre);
		if(pos != -1) {
			System.out.println("\nNAVE ENCONTRADA EN LA POSICIÓN "+(pos+1));
			System.out.println((pos+1) + ": "+misNaves[pos]);
		} else {
			System.out.println("\nNAVE NO ENCONTRADA");
		}
		ordenarPorPuntosSeleccion(misNaves);
		mostrarNaves(misNaves);
		ordenarPorNombreSeleccion(misNaves);
		mostrarNaves(misNaves);
		ordenarPorPuntosInsercion(misNaves);
		mostrarNaves(misNaves);
		ordenarPorNombreInsercion(misNaves);
		mostrarNaves(misNaves);
		}
	//Método para mostrar todas las naves
	static void mostrarNaves(Nave[] flota){
		for(int i=0; i<flota.length; i++) {
			System.out.println((i+1)+": "+flota[i]);
		}
		System.out.println();
	 }

	//Método para mostrar todas las naves de un nombre que se pide por teclado
	static void mostrarPorNombre(Nave[] flota){
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
				System.out.println((j+1)+": "+flota[pos.get(j)]);
			}
		} else 
			System.out.println("No se encontraron naves.");
	}

	//Método para mostrar todas las naves con un número de puntos inferior o igual
	//al número de puntos que se pide por teclado
	static void mostrarPorPuntos(Nave[] flota){
		Scanner sc=new Scanner(System.in);
		System.out.print("Ingrese el número de puntos: ");
		int num=sc.nextInt();
		int cont=0;
		for(int i=0; i<flota.length; i++) {
			if(num >= flota[i].getPuntos()) {
				System.out.println((i+1)+": "+flota[i]);
				cont++;
			}
			if(i==flota.length-1 && cont==0) {
				System.out.print("No se encontraron naves con el nivel de puntos ingresado.");
			}
		}
	}

	//Método que devuelve la Nave con mayor número de Puntos
	static Nave mostrarMayorPuntos(Nave[] flota){
		int may=0;
		for(int i=1; i<flota.length; i++) {
			if(flota[may].getPuntos()<flota[i].getPuntos())
				may=i;
		}
		return flota[may];
	}
	//Método para buscar la primera nave con un nombre que se pidió por teclado
	static int busquedaLinealNombre(Nave[] flota, String s){
		System.out.print("-------Búsqueda Lineal Nombre-------");
		for(int i=0; i<flota.length; i++) {
			if(flota[i].getNombre().equalsIgnoreCase(s))
				return i;
		}
		return -1;
	}
	//Método que ordena por número de puntos de menor a mayor
	static void ordenarPorPuntosBurbuja(Nave[] flota){
		int cant=flota.length-1;
		
		System.out.println("\n-------Ordenamiento Burbuja Puntos-------");
		for(int i=0; i<flota.length-1; i++) {
			for(int j=0; j<cant; j++) {
				if(flota[j].getPuntos()>flota[j+1].getPuntos()) {
					intercambio(flota, j, j+1);
				}
				else continue;
			}
			cant--;
		}
	}
	//Método que ordena por nombre de A a Z
	static void ordenarPorNombreBurbuja(Nave[] flota){
		int cant=flota.length-1;
		
		System.out.println("\n-------Ordenamiento Burbuja Nombres-------");
		for(int i=0; i<flota.length-1; i++) {
			for(int j=0; j<cant; j++) {
				if(flota[j].getNombre().compareToIgnoreCase(flota[j+1].getNombre())>0) {
					intercambio(flota, j, j+1);
				}
				else continue;
			}
			cant--;
		}
	}
	//Método para buscar la primera nave con un nombre que se pidió por teclado
	static int busquedaBinariaNombre(Nave[] flota, String s){
		int min=0;
		int may=flota.length-1;
		int med;
		System.out.print("-------Búsqueda Binaria Nombre-------");
		while(min<=may) {
			med=(may+min)/2;
			if(flota[med].getNombre().compareToIgnoreCase(s)==0)
				return med;
			else if(flota[med].getNombre().compareToIgnoreCase(s)>0)
				may=med-1;
			else
				min=med+1;
		}
		return -1;
	}
	//Método que ordena por número de puntos de menor a mayor
	static void ordenarPorPuntosSeleccion(Nave[] flota){
		System.out.println("\n-------Ordenamiento Por Puntos Selección-------");
		for(int i=0; i<flota.length-1; i++) {
			int men=i;
			for(int j=i+1; j<flota.length; j++) {
				if(flota[men].getPuntos() > flota[j].getPuntos()) {
					men=j;
				}
			}
			intercambio(flota, i, men);
		}
	}
	//Método que ordena por nombre de A a Z
	static void ordenarPorNombreSeleccion(Nave[] flota){
		System.out.println("\n-------Ordenamiento Por Nombre Selección A-Z-------");
		for (int i = 0; i<flota.length; i++) {
            int men = i;
            for (int j = i + 1; j<flota.length; j++) {
                if (flota[j].getNombre().compareToIgnoreCase(flota[men].getNombre()) < 0) {
                    men = j;
                }
            }
            intercambio(flota, i, men);
		}
	}
	//Método que muestra las naves ordenadas por número de puntos de mayor a menor
	static void ordenarPorPuntosInsercion(Nave[] flota) {
	    System.out.println("\n-------Ordenamiento Por Puntos Inserción MAY-MEN-------");
	    
	    for (int i = 1; i < flota.length; i++) {
	        Nave temp = flota[i];
	        int j = i - 1;
	        
	        while (j >= 0 && flota[j].getPuntos() < temp.getPuntos()) {
	            flota[j + 1] = flota[j];
	            j--;
	        }
	        
	        flota[j + 1] = temp;
	    }
	}

	//Método que muestra las naves ordenadas por nombre de Z a A
	static void ordenarPorNombreInsercion(Nave[] flota) {
	    System.out.println("\n-------Ordenamiento Por Nombre Inserción (Z-A)-------");
	    
	    for (int i = 1; i < flota.length; i++) {
	        Nave temp = flota[i];
	        int j = i - 1;

	        while (j >= 0 && flota[j].getNombre().compareToIgnoreCase(temp.getNombre()) < 0) {
	            flota[j + 1] = flota[j];
	            j--;
	        }

	        flota[j + 1] = temp;
	    }
	}

	static void intercambio(Nave [] flota, int pos1, int pos2) {
		String n, c;
		int f, p;
		boolean e;
		
		n=flota[pos1].getNombre();
		c=flota[pos1].getColumna();
		f=flota[pos1].getFila();
		p=flota[pos1].getPuntos();
		e=flota[pos1].getEstado();
		
		flota[pos1].setNombre(flota[pos2].getNombre());
		flota[pos1].setColumna(flota[pos2].getColumna());
		flota[pos1].setFila(flota[pos2].getFila());
		flota[pos1].setPuntos(flota[pos2].getPuntos());
		flota[pos1].setEstado(flota[pos2].getEstado());
		
		flota[pos2].setNombre(n);
		flota[pos2].setColumna(c);
		flota[pos2].setFila(f);
		flota[pos2].setPuntos(p);
		flota[pos2].setEstado(e);
	}
}
