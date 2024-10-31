package lab6;

import java.util.*;

public class VideoJuego3 {

	public static void main(String[] args) {
		ArrayList <ArrayList<Soldado>> tablero = new ArrayList<>();
		ArrayList<Soldado> ejercito1 = new ArrayList<Soldado>();
		ArrayList<Soldado> ejercito2 = new ArrayList<Soldado>();
		
		iniciarTabla(tablero);
		iniciarSoldados(tablero, ejercito1, 1);
		iniciarSoldados(tablero, ejercito2, 2);
		mostrarTablero(tablero);
		datosSoldados(ejercito1);
		Soldado prov= new Soldado();
		ordenamientoBurbuja(ejercito1, prov);
		System.out.println("\n<|||||||||||||||||||||||||||||||||||||||||||||||||||||||||>");
		datosSoldados(ejercito2);
		ordenamientoSeleccion(ejercito2, prov);
		
	}
	static void iniciarTabla(ArrayList<ArrayList<Soldado>> m) {
		for(int h=0; h<10; h++) {
			m.add(new ArrayList<Soldado>());
			for(int k=0; k<10;k++)
				m.get(h).add(null);
		}	
	}
	static void iniciarSoldados(ArrayList<ArrayList<Soldado>> m, ArrayList<Soldado> e, int num) {
		int cant = (int)(Math.random()*10+1);
		for(int i=0; i<cant; i++) {
			Soldado soldado= new Soldado();
			int fil=(int)(Math.random()*10);
			int col=(int)(Math.random()*10);
			int vida=(int)(Math.random()*5+1);
			int res;
			
			if(m.get(fil).get(col) == null) res=0;
			else res=-1;
			if(res==0) {
				soldado.setNombre("Soldado"+i+"X"+num);
				soldado.setVida(vida);
				soldado.setFila(fil);
				soldado.setColumna(col);
				soldado.setEjercito(num);
				
				e.add(soldado);
				m.get(fil).set(col, soldado);
			}
			else i--;
		}
	}
	static void mostrarTablero(ArrayList<ArrayList<Soldado>> m) {
		int [] f= {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		String [] c = {"  ", "  A  ", "   B  ", "    C  ", "  D  ", "   E  ", "   F ", "    G ", "    H  ", "   I  ", "   J "};
		for(int h=0; h<c.length; h ++) {
			System.out.print(c[h]);
		}
		System.out.println();
		for(int i=0; i<m.size(); i++) {
			if(i>=9) System.out.print(f[i]+" ");
			else System.out.print(" "+f[i]+" ");
			for(int j=0; j<m.get(i).size(); j++) {
				if(m.get(i).get(j) == null) {
					
					System.out.print("|____|");
				}
				else System.out.print("|"+m.get(i).get(j).getNombre().charAt(0)
										 +m.get(i).get(j).getNombre().charAt(7)
										 +m.get(i).get(j).getNombre().charAt(8)
										 +m.get(i).get(j).getNombre().charAt(9)+"|");
			}
			System.out.println();
		}
		System.out.println("\t     X1 = Ejercito 1 // X2 = Ejercito 2");
	}
	static void datosSoldados(ArrayList<Soldado> e) {
		//Soldado mayor vida
		int pos=0, may=e.get(0).getVida();
		int suma=e.get(0).getVida();
		for(int i=1; i<e.size(); i++) {
			suma+=e.get(i).getVida();
			if(may < e.get(i).getVida()) {
				may=e.get(i).getVida();
				pos=i;
			}
		}
		System.out.println("\n|-------SOLDADO CON MAYOR VIDA EJERCITO "+e.get(pos).getEjercito()+"-------|");
		System.out.println(e.get(pos).getNombre()+", "+e.get(pos).getVida()+" de vida. ");
		
		//Promedio puntos de vida total
		double prom=Math.round((double)(suma)/e.size());
		System.out.println("\n|-------PROMEDIO DE VIDA DE LOS SOLDADOS EJERCITO "+e.get(pos).getEjercito()+"-------|");
		System.out.println("Promedio: "+(int)prom+" puntos de vida.");
		
		//Nivel de vida todos
		System.out.println("\n|-------NIVEL DE VIDA TOTAL EJERCITO "+e.get(pos).getEjercito()+"-------|");
		System.out.println("Puntos de vida soldados: "+suma);
		
		//Datos en orden
		System.out.println("\n|-------SOLDADOS CREADOS EJERCITO "+e.get(pos).getEjercito()+"-------|");
		for(int j=0; j<e.size(); j++) {
			System.out.println(e.get(j).getNombre()+", "+e.get(j).getVida()+" puntos de vida.");
		}
	}
	static void ordenamientoBurbuja(ArrayList<Soldado> e, Soldado p) {
		int cant=e.size()-1;
		
		System.out.println("\n|-------RANKING DE PODER BURBUJA EJERCITO "+e.get(cant).getEjercito()+"-------|");
		for(int i=0; i<e.size()-1; i++) {
			for(int j=0; j<cant; j++) {
				if(e.get(j).getVida()<e.get(j+1).getVida()) {
					p=e.get(j);
					e.set(j, e.get(j+1));
					e.set(j+1, p);
				}
				else continue;
			}
			cant--;
		}
		for(int j=0; j<e.size(); j++) {
			System.out.println(e.get(j).getNombre()+", "+e.get(j).getVida()+" puntos de vida");
		}
	}
	static void ordenamientoSeleccion(ArrayList<Soldado> e, Soldado p) {
		System.out.println("\n|-------RANKING DE PODER SELECCION EJERCITO "+e.get(0).getEjercito()+"-------|");
		for(int i=0; i<e.size()-1; i++) {
			int men=i;
			for(int j=i+1; j<e.size(); j++) {
				if(e.get(men).getVida() < e.get(j).getVida()) {
					men=j;
				}
			}
			p=e.get(i);
			e.set(i, e.get(men));
			e.set(men, p);
		}
		for(int j=0; j<e.size(); j++) {
			System.out.println(e.get(j).getNombre()+", "+e.get(j).getVida()+" puntos de vida");
		}
	}
}
