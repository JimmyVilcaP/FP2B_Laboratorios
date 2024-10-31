package lab7;
import java.util.*;

public class VideoJuego4 {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String salir="s";
		while(true) {
			int cant1=(int)(Math.random()*10+1);
			int cant2=(int)(Math.random()*10+1);
			Soldado [][] misSoldados = new Soldado[10][10];
			Soldado [] ejercito1 = new Soldado[cant1];
			Soldado [] ejercito2 = new Soldado[cant2];
			
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
	
	public static void crearEjercito(Soldado [][] dat, Soldado [] e1, Soldado [] e2) {
		for(int i=0; i<e1.length; i++) {
			int fil=(int)(Math.random()*10);
			int col=(int)(Math.random()*10);
			int vida=(int)(Math.random()*5+1);
			int res;
			
			if(dat[fil][col] == null) res=0;
			else res=-1;
			
			if(res==0) {
				dat[fil][col]=new Soldado();
				dat[fil][col].setNombre("E1"+"Soldado"+i);
				dat[fil][col].setVida(vida);
			
			
				e1[i] = new Soldado();
				e1[i].setNombre("E1"+"Soldado"+i);
				e1[i].setVida(vida);
			}
			else i--;
		}
		for(int j=0; j<e2.length; j++) {
			int fil=(int)(Math.random()*10);
			int col=(int)(Math.random()*10);
			int vida=(int)(Math.random()*5+1);
			int res;
			
			if(dat[fil][col] == null) res=0;
			else res=-1;
			
			if(res==0) {
				dat[fil][col]=new Soldado();
				dat[fil][col].setNombre("E2"+"Soldado"+j);
				dat[fil][col].setVida(vida);
				
				e2[j] = new Soldado();
				e2[j].setNombre("E2"+"Soldado"+j);
				e2[j].setVida(vida);
			}
			else j--;
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
	public static void soldadosVida(Soldado [] e1, Soldado [] e2) {
		int may1=0, may2=0; 
		int pos1=0, pos2=0;
		int sum1=0, sum2=0;
		
		for(int i=0; i<e1.length; i++) {
			sum1+=e1[i].getVida();
			if(e1[i].getVida() > may1) {
				may1=e1[i].getVida();
				pos1=i;
			}
			else continue;
		}
		for(int j=0; j<e2.length; j++) {
			sum2+=e2[j].getVida();
			if(e2[j].getVida() > may2) {
				may2=e2[j].getVida();
				pos2=j;			
			}
			else continue;
		}
		//Mayor puntos de vida por Ej�rcito
		System.out.println("Soldado con mayor vida\nEjercito 1 -> "
							+e1[pos1].getNombre()+" - Vida "+e1[pos1].getVida());
		System.out.println("Soldado con mayor vida\nEjercito 2 -> "
							+e2[pos2].getNombre()+" - Vida "+e2[pos2].getVida());
		System.out.println();
		//Promedio puntos de vida por Ej�rcito
		System.out.println("Promedio puntos de vida\nEj�rcito 1 -> "
								+Math.round((double)(sum1)/e1.length));
		System.out.println("Promedio puntos de vida\nEj�rcito 2 -> "
								+Math.round((double)(sum2)/e2.length));
		System.out.println();
	}
	public static void datosSoldados(Soldado [] e1, Soldado [] e2) {
		int v1=0, v2=0;
		
		System.out.println("EJ�RCITO 1 // SOLDADOS");
		for(int i=0; i<e1.length; i++) {
			v1+=e1[i].getVida();
			System.out.println(e1[i].getNombre()+", "+e1[i].getVida()+" puntos de vida.");
		}
		System.out.println();
		System.out.println("EJ�RCITO 2 // SOLDADOS");
		for(int j=0; j<e2.length; j++) {
			v2+=e2[j].getVida();
			System.out.println(e2[j].getNombre()+", "+e2[j].getVida()+" puntos de vida.");
		}
		System.out.println();
		
		//Ranking de poder por Ej�rcito
		ejercito1Burbuja(e1);
		ejercito2Seleccion(e2);
		System.out.println();
		
		//Ej�rcito ganador // M�trica -> Mayor puntos de vida total
		if(v1 > v2)
			System.out.println("�El ej�rcito 1 es el ganador!");
		else if(v2 > v1)
			System.out.println("�El ej�rcito 2 es el ganador!");
		else if(v1 == v2)
			System.out.println("�No hay ganador!");
	}
	
	public static void ejercito1Burbuja(Soldado [] e1) {
		int cant=e1.length-1;
		String nombre;
		int vida;
		for(int i=0; i<e1.length-1; i++) {			
			for(int j=0; j<cant; j++) {
				if(e1[j].getVida() > e1[j+1].getVida()) {
					nombre=e1[j].getNombre();
					vida=e1[j].getVida();
					e1[j].setNombre(e1[j+1].getNombre());
					e1[j].setVida(e1[j+1].getVida());
					e1[j+1].setNombre(nombre);
					e1[j+1].setVida(vida);
				}
				else continue;
			}
		}
		System.out.println("RANKING DE PODER EJ�RCITO 1");
		for(int z=e1.length-1; z>=0; z--) {
			System.out.println(e1[z].getNombre()+", "+e1[z].getVida()+" puntos de vida");
		}
	}
	public static void ejercito2Seleccion(Soldado [] e2) {
		String nombre;
		int vida;
		for(int i=0; i<e2.length-1; i++) {
			int men=i;
			for(int j=i+1; j<e2.length; j++) {
				if(e2[men].getVida() > e2[j].getVida()) {
					men=j;
				}
				else continue;
			}
			nombre=e2[i].getNombre();
			vida=e2[i].getVida();
			e2[i].setNombre(e2[men].getNombre());
			e2[i].setVida(e2[men].getVida());
			e2[men].setNombre(nombre);
			e2[men].setVida(vida);
		}
		System.out.println("\nRANKING DE PODER EJ�RCITO 2");
		for(int z=e2.length-1; z>=0; z--) {
			System.out.println(e2[z].getNombre()+", "+e2[z].getVida()+" puntos de vida");
		}
	}
}
