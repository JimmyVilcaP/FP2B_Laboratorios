package lab2;

//Laboratorio Nro 2
//Autor: Jimmy Vilca Peralta
import java.util.*;

public class Ejercicio1 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String ahor1 = "   +---+ \n"+
						"   |   | \n" +
						"       | \n" +
						"       | \n" +
						"       | \n" +
						"       | \n" +
						"========= ";
		String ahor2 = "   +---+ \n"+
						"   |   | \n"+
						"   O   | \n"+
						"       | \n"+
						"       | \n"+
						"       | \n"+
						"=========";
		String ahor3 = "   +---+ \n"+
						"   |   | \n"+
						"   O   | \n"+
						"   |   | \n"+
						"       | \n"+
						"       | \n"+
						"=========";
		String ahor4 = "   +---+ \n"+
						"   |   | \n"+
						"   O   | \n"+
						"  /|   | \n"+
						"       | \n"+
						"       | \n"+
						"=========";
		String ahor5 = "   +---+ \n"+
						"   |   | \n"+
						"   O   | \n"+
						"  /|\\  | \n"+
						"       | \n"+
						"       | \n"+
						"=========";
		String ahor6 = "   +---+ \n"+
						"   |   | \n"+
						"   O   | \n"+
						"  /|\\  | \n"+
						"  /    | \n"+
						"       | \n"+
						"=========";
		String ahor7 = "   +---+ \n"+
						"   |   | \n"+
						"   O   | \n"+
						"  /|\\  | \n"+
						"  / \\  | \n"+
						"       | \n"+
						"=========";
		
		String [] figuras = {ahor1, ahor2, ahor3, ahor4, ahor5, ahor6, ahor7};
		int contador = 0;
		int c = 0;
		int fin = 0;
		String letra;
		String [] palabras = {"programacion", "java", "identacion", "clases", 
										"objetos", "desarrollador", "pruebas"};
		String palSecreta = getPalabraSecreta(palabras);
		String [] mostrar = new String[palSecreta.length()];
		ArrayList <String> letUsada = new ArrayList<String>();
		
		System.out.print(figuras[0]);
		mostrarBlancos(palSecreta);
		System.out.print("\n");
		
		while(contador<6) {
			letra = ingreseLetra(letUsada);
			if(letUsada.size()!=1) {
				c=comprobarLetra(letra, letUsada);
			}
			if (letraEnPalabraSecreta(letra, palSecreta) == true) {
				System.out.println("================================");
				System.out.print(figuras[contador]);
				mostrarBlancosActualizados(letra, palSecreta, mostrar);
			}
			else {
				System.out.println("================================");
				System.out.println("Fallaste...");
				
				if(c==0) {
					System.out.print(figuras[contador+1]);
					contador++;
				}
				else {
					System.out.print(figuras[contador]);
				}
				mostrarBlancosActualizados(letra, palSecreta, mostrar);
			}	
			fin = comprobarPalabra(palSecreta, mostrar);
			if(fin==palSecreta.length()) break;
			else continue;
		}
		mostrarResultados(letUsada, palSecreta, fin);
	}
	
	public static String getPalabraSecreta(String [] p) {
		int indiceMayor = p.length;
		int i = (int) (Math.random()*indiceMayor);
		
		return p[i];
	}
	public static void mostrarBlancos(String pal) {
		for(int i=0; i<pal.length(); i++) {
			System.out.print("_ ");
		}
	}
	public static String ingreseLetra(ArrayList<String> l) {
		Scanner sc = new Scanner(System.in);
		String laLetra;
		int rango=0;
		while(true) {
			System.out.println();
			System.out.print("Ingrese letra -> ");
			laLetra = sc.next();
			if(laLetra.length() != 1) {
				System.out.println("INGRESE UNA SOLA LETRA");
				continue;
			}
			else {
				char valor=laLetra.charAt(0);
				rango=(int) (valor);
				if (rango > 96 && rango < 123) {
					l.add(laLetra);
					break;
				}
				else {
					System.out.println("INGRESE UNA CARACTER VÁLIDO (a-z)");
					continue;
				}
			}
		}
		return laLetra;
	}
	public static int comprobarLetra(String l, ArrayList<String> let) {
		int cont = 0;
		int intento= 0 ;
		for(int i=0; i<let.size(); i++) {
			if(let.get(i).equals(l)) {
				cont++;
				intento=i;
				if(cont==2) break;
			}
			else continue;
		}
		if(cont > 1) {
			System.out.println("LETRA REPETIDA, INTRODUZCA NUEVAMENTE.");
			let.remove(intento);
			return 1;
		}
		else { 
			return 0;
		}
	}
	public static boolean letraEnPalabraSecreta (String l, String pSecreta) {
	    return pSecreta.indexOf(l) != -1;
	}	
	public static void mostrarBlancosActualizados(String letra, String pSecreta, String m[]) {
		for(int i=0; i<pSecreta.length(); i++) {
			if(pSecreta.substring(i, (i+1)).equals(letra)) {
				m[i]=letra;
			}
			else 
				continue;
		}
		for(int j=0; j<m.length; j++) {
			if(m[j] == null) {
				System.out.print("_ ");
			}
			else
				System.out.print(m[j]+ " ");
		}
		System.out.println();
		System.out.println("================================");
	}
	public static int comprobarPalabra(String p, String m[]) {
		int contador=0;
		for(int i=0; i<p.length(); i++) {
			if(p.substring(i, (i+1)).equals(m[i])) {
				contador++;
			}
			else continue;
		}		
		return contador;
	}
	public static void mostrarResultados(ArrayList<String> intento, String lSecret, int fin) {
		if(lSecret.length() == fin) {
			System.out.println("FELICITACIONES USTED GAN�!!");
			System.out.print("Intentos totales: " + intento.size());
		}
		else {
			System.out.println("ACABA DE PERDER, BUEN INTENTO");
			System.out.print("Intentos totales: " + intento.size());
		}
	}
}
