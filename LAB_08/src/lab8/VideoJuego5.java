package lab8;

import java.util.*;
public class VideoJuego5 {
	public static void main(String [] args) {
		Scanner sc=new Scanner(System.in);
		String salir="s";
		while(true) {
			Soldado [][] misSoldados = new Soldado[10][10];
			HashMap <Integer, Soldado> ejercito1 = new HashMap <Integer, Soldado>();
			HashMap <Integer, Soldado> ejercito2 = new HashMap <Integer, Soldado>();
			
			crearEjercito(misSoldados, ejercito1, ejercito2);
			mostrarTablero(misSoldados);
			soldadosVida(ejercito1, ejercito2);
			datosSoldados(ejercito1, ejercito2);
			System.out.println("\n�Desea simular otra batalla? Si/No");
			salir=sc.next();
			if(salir.equalsIgnoreCase("si")) continue;
			else break;
		}
	}
	public static void crearEjercito(Soldado [][] dat, HashMap<Integer, Soldado> e1, HashMap<Integer, Soldado> e2) {
		for(int i=0; i<2; i++) {
			int cant=(int)(Math.random()*10+1);
			for(int j=0; j<cant; j++) {
				int fil=(int)(Math.random()*10);
				int col=(int)(Math.random()*10);
				int vida=(int)(Math.random()*5+1);
				int res;
				
				if(dat[fil][col] == null) res=0;
				else res=-1;
				
				if(res==0) {
					dat[fil][col]=new Soldado();
					dat[fil][col].setNombre(("E"+(i+1))+"Soldado"+j);
					dat[fil][col].setVida(vida);
					
					if(i==0) {
						e1.put(j, dat[fil][col]);
					}
					else if(i==1) {
						e2.put(j, dat[fil][col]);
					}
					
				}
				else j--;
			}
		}
	}
	public static void mostrarTablero(Soldado [][] dat) {
		for(int i=0; i<dat.length; i++) {
			for(int j=0; j<dat[i].length; j++) {
				if(dat[i][j] == null) System.out.print("|____|");
				else System.out.print("|"+dat[i][j].getNombre().charAt(0)
										 +dat[i][j].getNombre().charAt(1)
										 +dat[i][j].getNombre().charAt(2)
										 +dat[i][j].getNombre().charAt(9)+"|");
			}
			System.out.println();
		}
		System.out.println("\t     E1 = Ejercito 1 // E2 = Ejercito 2");
	}
	public static void soldadosVida(HashMap<Integer, Soldado> e1, HashMap<Integer, Soldado> e2) {
		int may1=0, may2=0; 
		int pos1=0, pos2=0;
		int sum1=0, sum2=0;
		
		for(Integer key1:e1.keySet()) {
			sum1+=e1.get(key1).getVida();
			if(e1.get(key1).getVida() > may1) {
				may1=e1.get(key1).getVida();
				pos1=key1;
			}
			else continue;
		}
		for(Integer key2:e2.keySet()) {
			sum2+=e2.get(key2).getVida();
			if(e2.get(key2).getVida() > may2) {
				may2=e2.get(key2).getVida();
				pos2=key2;			
			}
			else continue;
		}
		//Mayor puntos de vida por Ej�rcito
		System.out.println("Soldado con mayor vida\nEjercito 1 -> "
							+e1.get(pos1).getNombre()+" - Vida "+e1.get(pos1).getVida());
		System.out.println("Soldado con mayor vida\nEjercito 2 -> "
							+e2.get(pos2).getNombre()+" - Vida "+e2.get(pos2).getVida());
		System.out.println();
		//Promedio puntos de vida por Ej�rcito
		System.out.println("Promedio puntos de vida\nEj�rcito 1 -> "
								+Math.round((double)(sum1)/e1.size()));
		System.out.println("Promedio puntos de vida\nEj�rcito 2 -> "
								+Math.round((double)(sum2)/e2.size()));
		System.out.println();
	}
	public static void datosSoldados(HashMap<Integer, Soldado> e1, HashMap<Integer, Soldado> e2) {
		Soldado [] orden1 = new Soldado[e1.size()];
		Soldado [] orden2 = new Soldado[e2.size()];
		int v1=0, v2=0;
		
		System.out.println("EJ�RCITO 1 // SOLDADOS");
		for(Integer key1:e1.keySet()) {
			orden1[key1] = new Soldado();
			orden1[key1].setNombre(e1.get(key1).getNombre());
			orden1[key1].setVida(e1.get(key1).getVida());
			v1+=e1.get(key1).getVida();
			System.out.println(e1.get(key1).getNombre()+", "+e1.get(key1).getVida()+" puntos de vida.");
		}
		System.out.println();
		System.out.println("EJ�RCITO 2 // SOLDADOS");
		for(Integer key2:e2.keySet()) {
			orden2[key2] = new Soldado();
			orden2[key2].setNombre(e2.get(key2).getNombre());
			orden2[key2].setVida(e2.get(key2).getVida());
			v2+=e2.get(key2).getVida();
			System.out.println(e2.get(key2).getNombre()+", "+e2.get(key2).getVida()+" puntos de vida.");
		}
		System.out.println();
		
		//Ranking de poder por Ej�rcito
		ejercito1Burbuja(orden1);
		ejercito2Seleccion(orden2);
		System.out.println();
		/*
		CONCLUSIONES: Un HashMap al ser accedido mediante una clave(KEY) no puede ser ordenado
		debido a que no se encuentra en una posici�n espec�fica o en orden como un Array o ArrayList
		por esa raz�n cre� m�s conveniente copiar los datos de un Hashmap en un Array, permitiendome
		ordenar los datos de forma m�s sencilla. 
		*/
		
		//Ej�rcito ganador // M�trica -> Mayor puntos de vida total
		if(v1 > v2)
			System.out.println("�El ej�rcito 1 es el ganador!");
		else if(v2 > v1)
			System.out.println("�El ej�rcito 2 es el ganador!");
		else if(v1 == v2)
			System.out.println("�No hay ganador!");
	}
	public static void ejercito1Burbuja(Soldado [] o1) {
		int cant=o1.length-1;
		String nombre;
		int vida;
		for(int i=0; i<o1.length-1; i++) {			
			for(int j=0; j<cant; j++) {
				if(o1[j].getVida() > o1[j+1].getVida()) {
					nombre=o1[j].getNombre();
					vida=o1[j].getVida();
					o1[j].setNombre(o1[j+1].getNombre());
					o1[j].setVida(o1[j+1].getVida());
					o1[j+1].setNombre(nombre);
					o1[j+1].setVida(vida);
				}
				else continue;
			}
		}
		System.out.println("RANKING DE PODER EJ�RCITO 1");
		for(int z=o1.length-1; z>=0; z--) {
			System.out.println(o1[z].getNombre()+", "+o1[z].getVida()+" puntos de vida");
		}
	}
	public static void ejercito2Seleccion(Soldado [] o2) {
		String nombre;
		int vida;
		for(int i=0; i<o2.length-1; i++) {
			int men=i;
			for(int j=i+1; j<o2.length; j++) {
				if(o2[men].getVida() > o2[j].getVida()) {
					men=j;
				}
				else continue;
			}
			nombre=o2[i].getNombre();
			vida=o2[i].getVida();
			o2[i].setNombre(o2[men].getNombre());
			o2[i].setVida(o2[men].getVida());
			o2[men].setNombre(nombre);
			o2[men].setVida(vida);
		}
		System.out.println("\nRANKING DE PODER EJ�RCITO 2");
		for(int z=o2.length-1; z>=0; z--) {
			System.out.println(o2[z].getNombre()+", "+o2[z].getVida()+" puntos de vida");
		}
	}
}
