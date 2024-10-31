package lab5;

import java.util.*;

public class VideoJuego2 {

	public static void main(String[] args) {
		Soldado [][] tablero = new Soldado[10][10];
		int cant=(int)(Math.random()*10+1);
		Soldado [] ejercito = new Soldado[cant];
		iniciarSoldados(tablero, ejercito);
		mostrarSoldados(tablero);
		datosSoldados(ejercito);
	}
	static void iniciarSoldados(Soldado[][] m, Soldado[] e) {
		for(int i=0; i<e.length; i++) {
			int fil=(int)(Math.random()*10);
			int col=(int)(Math.random()*10);
			int vida=(int)(Math.random()*5+1);
			int res;

			if(m[fil][col] == null) res=0;
			else res=-1;
			if(res==0) {
				e[i] = new Soldado();
				e[i].setNombre("Soldado"+i);
				e[i].setVida(vida);
				e[i].setFila(fil);
				e[i].setColumna(col);
				
				m[fil][col] = e[i];
			}
			else i--;
		}
	}
	static void mostrarSoldados(Soldado [][] m) {
		for(int i=0; i<m.length; i++) {
			for(int j=0; j<m[i].length; j++) {
				if(m[i][j] == null) System.out.print("|____|");
				else System.out.print("|"+m[i][j].getNombre().charAt(0)
										 +m[i][j].getNombre().charAt(7)
										 +"N"+m[i][j].getVida()
										 +"|");
			}
			System.out.println();
		}
	}
	static void datosSoldados(Soldado[] e) {
		//Soldado mayor vida
		int pos=0, may=e[0].getVida();
		int suma=e[0].getVida();
		for(int i=1; i<e.length; i++) {
			suma+=e[i].getVida();
			if(may < e[i].getVida()) {
				may=e[i].getVida();
				pos=i;
			}
		}
		System.out.println("\n|-------SOLDADO CON MAYOR VIDA-------|");
		System.out.println(e[pos].getNombre()+", "+e[pos].getVida()+" de vida. ");
		
		//Promedio puntos de vida total
		double prom=Math.round((double)(suma)/e.length);
		System.out.println("\n|-------PROMEDIO DE VIDA DE LOS SOLDADOS-------|");
		System.out.println("Promedio: "+(int)prom+" puntos de vida.");
		
		//Nivel de vida todos
		System.out.println("\n|-------NIVEL DE VIDA TOTAL-------|");
		System.out.println("Puntos de vida soldados: "+suma);
		
		//Datos en orden
		System.out.println("\n|-------SOLDADOS CREADOS-------|");
		for(int j=0; j<e.length; j++) {
			System.out.println(e[j].getNombre()+", "+e[j].getVida()+" puntos de vida");
		}
		
		int z=(int)(Math.random()*2);
		Soldado prov= new Soldado();
		if(z==0) ordenamientoBurbuja(e, prov);
		else ordenamientoSeleccion(e, prov);
	}
	static void ordenamientoBurbuja(Soldado [] e, Soldado p) {
		int cant=e.length-1;
		
		System.out.println("\n|-------RANKING DE PODER BURBUJA-------|");
		for(int i=0; i<e.length-1; i++) {
			for(int j=0; j<cant; j++) {
				if(e[j].getVida()<e[j+1].getVida()) {
					p=e[j];
					e[j]=e[j+1];
					e[j+1]=p;
				}
				else continue;
			}
			cant--;
		}
		for(int j=0; j<e.length; j++) {
			System.out.println(e[j].getNombre()+", "+e[j].getVida()+" puntos de vida");
		}
	}
	static void ordenamientoSeleccion(Soldado [] e, Soldado p) {
		System.out.println("\n|-------RANKING DE PODER SELECCION-------|");
		for(int i=0; i<e.length-1; i++) {
			int men=i;
			for(int j=i+1; j<e.length; j++) {
				if(e[men].getVida() < e[j].getVida()) {
					men=j;
				}
			}
			p=e[i];
			e[i]=e[men];
			e[men]=p;
		}
		for(int j=0; j<e.length; j++) {
			System.out.println(e[j].getNombre()+", "+e[j].getVida()+" puntos de vida");
		}
	}
}
