package lab10;
import java.util.*;

public class VideoJuego7 {
	static void lucha(Soldado s, Soldado sB) { 
        String batalla = "\t     ╔══════════════════╗\n" +
                         "\t     ║ BATALLA INICIADA ║\n" +
                         "\t     ╚══════════════════╝";
 
	    String soldados = 
	            "\t _/﹋\\_                _/﹋\\_              \r\n"
	            + "\t (҂`_´)                (｀_´҂)              \r\n"
	            + "\t <,︻╦╤─ -- ҉      ҉ -- ─╦╤︻>,\r\n"
	            + "\t  _/﹋\\_              _/﹋\\_              ";

	    System.out.println(s.toString()+"\n"+sB.toString()+"\n");
	    System.out.println(batalla);
	    System.out.println(soldados);
	    System.out.println("\t"+s.getNombre()+"    VS    "+sB.getNombre()
	    		+"\n\t  VIDA"+s.getVidaActual()
	    		+"                VIDA"+sB.getVidaActual());
	    System.out.println();
	}
	static void ganador() {
        String cel = 	 "\t          ╔════════════════╗\n" +
                		 "\t          ║ FELICITACIONES ║\n" +
                		 "\t          ╚════════════════╝";
		String celebracion = 
			      "  _/﹋\\\\_    _/﹋\\\\_   _/﹋\\\\_    _/﹋\\\\_   _/﹋\\\\_\r\n"
				+ " \\(￣▽￣)/   \\(＾◇＾)/   \\(≧∇≦)/   \\(o▽o)/   \\(＾∀＾)/\r\n"
			    + "   |  |       | |       |  |      |  |      |  |\r\n"
			    + "   |  |        | |      |  |      |  |      |  |\r\n"
			    + "  _/﹋\\_      _/﹋\\_   _/﹋ \\_    _/﹋\\_    _/﹋\\_\r\n";

		System.out.println(cel+"\n"+celebracion);
	}
	
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
			//Métodos para el movimiento
			
			empezarJuego(misSoldados, ejercito1, ejercito2);
			
			System.out.println("\nDesea simular otra batalla? Si/No");
			String salir=sc.next();
			if(salir.equalsIgnoreCase("si")) continue;
			else break;
		}
	}
	static void generarEjercitos(Soldado[][] dat, Soldado[] e, int num) {
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
				e[i] = new Soldado((n+i), ataque, defensa, vida, fil, col, num, true);
				dat[fil][col]=e[i];
			}
			else i--;
		}
	}
	static void mostrarTablero(Soldado[][] dat) {
	    System.out.print("   ");
	    for (int col = 0; col < dat[0].length; col++) {
	        System.out.print("   " + (char)('A' + col) + "  ");
	    }
	    System.out.println();

	    for (int i = 0; i < dat.length; i++) {
	        System.out.printf("%2d ", i + 1);
	        for (int j = 0; j < dat[i].length; j++) {
	            if (dat[i][j] == null) {
	                System.out.print("|____|");
	            } else {
	                System.out.print("|" 
	                    + dat[i][j].getNombre().charAt(0)
	                    + dat[i][j].getNombre().charAt(1)
	                    + dat[i][j].getNombre().charAt(2)
	                    + dat[i][j].getNombre().charAt(9) + "|");
	            }
	        }
	        System.out.println();
	    }

	    System.out.println("\t     E1 = Ejercito 1 // E2 = Ejercito 2");
	}

	static void vidaSoldados(Soldado [] e1, Soldado [] e2) {
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
	static void datosSoldados(Soldado [] e1, Soldado [] e2) {
		int a1=0, a2=0;
		
		System.out.println("EJÉRCITO 1 // SOLDADOS");
		for(int i=0; i<e1.length; i++) {
			a1+=e1[i].getAtaque();
			System.out.println(e1[i]);
		}
		System.out.println();
		System.out.println("EJÉRCITO 2 // SOLDADOS");
		for(int j=0; j<e2.length; j++) {
			a2+=e2[j].getAtaque();
			System.out.println(e2[j]);
		}
		System.out.println();
		
		//Ranking de poder por Ejercito
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
	static void ejercito1Burbuja(Soldado [] e, Soldado p) {
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
	static void ejercito2Seleccion(Soldado [] e, Soldado p) {
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
	static void empezarJuego(Soldado [][] tab, Soldado [] e1, Soldado [] e2) {
		Scanner sc=new Scanner(System.in);
		int c1=e1.length;
		int c2=e2.length;
		int sol=0;
		int cont=1;
		int ver=0;
		int e=0;
		boolean comprobar=false;
		
		System.out.println("\n\n\t\t|----INICIANDO LA BATALLA----|");
		mostrarTablero(tab);
		while(c1 != 0 || c2!=0) {
			if(cont%2 != 0) {
				e=1;
				imprimirSoldados(e1);
				System.out.print("Jugador 1: Seleccione su soldado: ");
				sol=sc.nextInt();
				if(sol>e1.length || sol<1) {
					System.out.println("Soldado no encontrado, elija un soldado válido!!\n");
				} else if(!e1[sol-1].estaVivo()) {
					System.out.println("El soldado seleccionado ha caído en batalla elija otro.\n");
				} else { 
					comprobar=true;
				}
			} else {
				e=2;
				imprimirSoldados(e2);
				System.out.print("Jugador 2 seleccione su soldado: ");
				sol=sc.nextInt();
				if(sol>e2.length || sol<1) {
					System.out.println("Soldado no encontrado, elija un soldado válido!!\n");
				} else if(!e2[sol-1].estaVivo()) {
					System.out.println("El soldado seleccionado ha caído en batalla elija otro.\n	");
				} else { 
					comprobar=true;
				}
			}
			if(comprobar) {	
				while(true) {
					System.out.print("Ingrese la columna a la que desea mover (A-J): ");
					char letraColumna = sc.next().charAt(0);
					int columna = letraACodigo(letraColumna);
					System.out.print("Ingrese la fila a la que desea mover (1-10): ");
					int fila = sc.nextInt()-1;
					
					if((fila>=0 && fila<=9) && columna != -1) {
						ver = moverSoldados(tab, e1, e2, (sol - 1), fila, columna, e);
						if(ver==0) {
							cont++;
							comprobar=false;
							if(!comprobarSoldados(e1)) {
				    		    System.out.println("El ejército 1 ha sido derrotado. ¡Ejército 2 gana!");
				    		    ganador();
				    		    System.exit(0);
							} else if(!comprobarSoldados(e2)) {
				    		    System.out.println("El ejército 2 ha sido derrotado. ¡Ejército 1 gana!");
				    		    ganador();
				    		    System.exit(0);
							}
							break;
						}
					}
					else {
						System.out.println("Ingrese una posición válida...");
					}						
				}
				System.out.println("\n----------------------------------------------------------------");
				mostrarTablero(tab);
			}
		}
	}
	static int moverSoldados(Soldado[][] tab, Soldado[] e1, Soldado[] e2, int i, int f, int c, int e) {
		Soldado soldado = (e == 1) ? e1[i] : e2[i];

		if (tab[f][c] != null) {
		       if(tab[f][c].getEjercito() == e) {
				System.out.println("Posición ocupada por otro soldado de tu Ejército. Seleccione otro lugar.");
		        return -1;
		       } else {
		    	   Soldado soldadoB;
		    	   if(tab[f][c].getEjercito()==1) {
		    		   soldadoB = buscarSoldado(e1, f, c);
		    	   }
		    	   else {
		    		   soldadoB = buscarSoldado(e2, f, c);
		    	   }
		    	   
		    	   metricaBatalla(tab, soldado, soldadoB);
		    	   return 0;
		       }
	    }
		tab[f][c] = soldado;
	    tab[soldado.getFila()][soldado.getColumna()] = null;

	    soldado.setFila(f);
	    soldado.setColumna(c);
	    
	    return 0;
	}
	static int letraACodigo(char letra) {
	    letra = Character.toUpperCase(letra);
	    
	    if (letra >= 'A' && letra <= 'J') return letra - 65;
	    else System.out.println("Letra de columna no válida: " + letra);
	    return -1;
	}
	public static void imprimirSoldados(Soldado [] e) {
		System.out.println();
		System.out.println("\t \t \tEJÉRCITO " + e[0].getEjercito() + " || SOLDADOS");
	    imprimirEncabezado();
	    for (int i = 0; i < e.length; i++) {
	        System.out.printf("%-2d→|", (i + 1));
	        System.out.println(e[i]);
	    }
	    System.out.println();
	}
	public static void imprimirEncabezado() {
	    System.out.printf("%-2s | %-10s | %-3s | %-3s | %-3s | %-3s | %-4s | %-3s | %-4s | %-10s%n",
	                      "N°", "Nombre", "COL", "FIL", "ATQ", "DEF", "VIDA", "VEL", "Vive", "Actitud");
	    System.out.println("------------------------------------------------------------------------");
	}
	static Soldado buscarSoldado(Soldado [] e, int f, int c) {
		for(int i=0; i<e.length; i++) {
			if(e[i].getFila()==f && e[i].getColumna()==c) {
				return e[i];
			}
		}
		return null;
	}
	static void metricaBatalla(Soldado[][] tab, Soldado s, Soldado sB) {
	    lucha(s, sB); 
	    Soldado ganador = determinarGanador(s, sB);
	    Soldado perdedor = (ganador == s) ? sB : s;
	    perdedor.morir();
	    tab[perdedor.getFila()][perdedor.getColumna()] = null;
	    if (ganador == s) {
	    	tab[s.getFila()][s.getColumna()] = null;
	        s.setFila(sB.getFila());
	        s.setColumna(sB.getColumna());
	        tab[sB.getFila()][sB.getColumna()] = s;
	    }
	    System.out.println("GANADOR: " + ganador.getNombre() + "!!!");
	}
	static Soldado determinarGanador(Soldado s, Soldado sB) {
	    if (s.getVidaActual() > sB.getVidaActual()) {
	        return s;
	    } else if (s.getVidaActual() < sB.getVidaActual()) {
	        return sB;
	    } else {
	        return (Math.random() < 0.5) ? s : sB;
	    }
	}
	static boolean comprobarSoldados(Soldado[] ejercito) {
	    for (Soldado soldado : ejercito) {
	        if (soldado.estaVivo()) {
	            return true;
	        }
	    }
	    return false;
	}
}
