package lab9;

import java.util.*;

public class VideoJuego6 {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		while(true) {
			int cant1=(int)(Math.random()*10+1);
			int cant2=(int)(Math.random()*10+1);
			Soldado [][] misSoldados = new Soldado[10][10];
			Soldado [] ejercito1 = new Soldado[cant1];
			Soldado [] ejercito2 = new Soldado[cant2];
			
			generarEjercitos(misSoldados, ejercito1, 1);
			generarEjercitos(misSoldados, ejercito2, 2);
			mostrarTablero(misSoldados);
			vidaSoldados(ejercito1, ejercito2);
			datosSoldados(ejercito1, ejercito2);
			System.out.println("\nDesea simular otra batalla? Si/No");
			String salir=sc.next();
			if(salir.equalsIgnoreCase("si")) continue;
			else break;
		}
	}
	public static void generarEjercitos(Soldado[][] dat, Soldado[] e, int num) {
		for(int i=0; i<e.length; i++) {
			String n="E"+num+"Soldado";
			int fil=(int)(Math.random()*10);
			int col=(int)(Math.random()*10);
			int ataque=(int)(Math.random()*5+1);
			int defensa=(int)(Math.random()*5+1);
			int vida=(int)(Math.random()*5+1);
			int res;
			
			if(dat[fil][col] == null) res=0;
			else res=-1;
			
			if(res==0) {
				e[i] = new Soldado((n+i), ataque, defensa, vida, true);
				dat[fil][col]=e[i];
			}
			else i--;
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
	public static void vidaSoldados(Soldado [] e1, Soldado [] e2) {
		int may1=0, may2=0; 
		int pos1=0, pos2=0;
		int sum1=0, sum2=0;
		
		for(int i=0; i<e1.length; i++) {
			sum1+=e1[i].getVidaActual();
			if(e1[i].getVidaActual() > may1) {
				may1=e1[i].getVidaActual();
				pos1=i;
			}
			else continue;
		}
		for(int j=0; j<e2.length; j++) {
			sum2+=e2[j].getVidaActual();
			if(e2[j].getVidaActual() > may2) {
				may2=e2[j].getVidaActual();
				pos2=j;			
			}
			else continue;
		}
		//Mayor puntos de vida por Ej�rcito
		System.out.println("Soldado con mayor vida");
		System.out.println("Ejercito 1 -> "
							+e1[pos1].getNombre()+" - Vida "+e1[pos1].getVidaActual());
		System.out.println("Ejercito 2 -> "
							+e2[pos2].getNombre()+" - Vida "+e2[pos2].getVidaActual());
		System.out.println();
		//Promedio puntos de vida por Ej�rcito
		System.out.println("Promedio puntos de vida");
		System.out.println("Ejército 1 -> "
							+Math.round((double)(sum1)/e1.length));
		System.out.println("Ejército 2 -> "
							+Math.round((double)(sum2)/e2.length));
		System.out.println();
	}
	public static void datosSoldados(Soldado [] e1, Soldado [] e2) {
		int a1=0, a2=0;
		
		System.out.println("EJÉRCITO 1 // SOLDADOS");
		for(int i=0; i<e1.length; i++) {
			a1+=e1[i].getAtaque();
			e1[i].mostrarDatos();
		}
		System.out.println();
		System.out.println("EJÉRCITO 2 // SOLDADOS");
		for(int j=0; j<e2.length; j++) {
			a2+=e2[j].getAtaque();
			e2[j].mostrarDatos();
		}
		System.out.println();
		
		//Ranking de poder por Ej�rcito
		Soldado prov= new Soldado();
		ejercito1Burbuja(e1, prov);
		ejercito2Seleccion(e2, prov);
		System.out.println();
		
		//Mayor puntos de ataque total
		System.out.println("Métrica usada MAYOR PUNTOS DE ATAQUE");
		if(a1 > a2)
			System.out.println("El ejército 1 es el ganador!");
		else if(a2 > a1)
			System.out.println("El ejército 2 es el ganador!");
		else if(a1 == a2)
			System.out.println("No hay ganador!");
	}
	public static void ejercito1Burbuja(Soldado [] e, Soldado p) {
		int cant=e.length-1;
		
		System.out.println("\n|-------RANKING DE PODER BURBUJA-------|");
		for(int i=0; i<e.length-1; i++) {
			for(int j=0; j<cant; j++) {
				if(e[j].getVidaActual()<e[j+1].getVidaActual()) {
					p=e[j];
					e[j]=e[j+1];
					e[j+1]=p;
				}
				else continue;
			}
			cant--;
		}
		for(int j=0; j<e.length; j++) {
			System.out.println(e[j].getNombre()+", "+e[j].getVidaActual()+" puntos de vida");
		}
	}
	public static void ejercito2Seleccion(Soldado [] e, Soldado p) {
		System.out.println("\n|------RANKING DE PODER SELECCION------|");
		for(int i=0; i<e.length-1; i++) {
			int men=i;
			for(int j=i+1; j<e.length; j++) {
				if(e[men].getVidaActual() < e[j].getVidaActual()) {
					men=j;
				}
			}
			p=e[i];
			e[i]=e[men];
			e[men]=p;
		}
		for(int j=0; j<e.length; j++) {
			System.out.println( e[j].getNombre()+", "+e[j].getVidaActual()+" puntos de vida");
		}
	}
}
