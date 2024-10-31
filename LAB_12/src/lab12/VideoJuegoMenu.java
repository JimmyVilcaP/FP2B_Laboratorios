package lab12;
import java.util.*;

public class VideoJuegoMenu {
	static final Scanner sc = new Scanner(System.in);
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
	
	//OPCIONES DEL SUBMENU
	static void agregarSoldado(Soldado [][] tabla, ArrayList<Soldado> e) {
		System.out.print("Nombre del soldado: ");
		String n=sc.nextLine();
		
		int ataque=(int)(Math.random()*5+1);
		int defensa=(int)(Math.random()*5+1);
		int vida=(int)(Math.random()*5+1);
		int ejr= e.get(0).getEjercito();
		while(true) {
			int fil=(int)(Math.random()*10);
			int col=(int)(Math.random()*10);
			String nom = "E" + ejr + n + e.size();
			if(tabla[fil][col] == null) {
				Soldado soldado = new Soldado(nom, ataque, defensa, vida, fil, col, ejr, true);
				e.add(soldado);
				tabla[fil][col]=soldado;
				break;
			}
		}
	}
	static void eliminarSoldado(Soldado [][] tabla, ArrayList<Soldado> e) {
		System.out.print("Elija la posición del soldado a eliminar: ");
		int pos = comprobarSeleccion(1, e.size())-1;
		tabla [e.get(pos).getFila()][e.get(pos).getColumna()] = null;
		e.remove(pos);
	}
	static void clonarSoldado(Soldado [][] tabla, ArrayList<Soldado> e) {
		System.out.print("Indique la posición del soldado a clonar: ");
		int pos= comprobarSeleccion(1, e.size())-1;
		Soldado soldado = new Soldado();
		soldado.clone(e.get(pos));
		while(true) {
			int fil=(int)(Math.random()*10);
			int col=(int)(Math.random()*10);
			
			if(tabla[fil][col]==null) {
				soldado.setFila(fil);
				soldado.setColumna(col);
				e.add(soldado);
				tabla[fil][col]=soldado;
				break;
			}
		}
	}
	static void modificarSoldado(Soldado [][] tabla, ArrayList<Soldado> e) {
		imprimirSoldados(e);
		System.out.print("Indice del soldado a modificar");
		int ind = comprobarSeleccion(1, e.size())-1;
		System.out.println("Atributo a modificar \n1. ATAQUE\n2. DEFENSA\n3. VIDA");
		int atr = comprobarSeleccion(1, 3);
		System.out.println("Indique el nuevo valor permitido para el atributo (1-5): ");
		int val = comprobarSeleccion(1, 5);
		if(atr==1) {
			e.get(ind).setAtaque(val);
		} else if(atr==2) {
			e.get(ind).setDefensa(val);
		} else {
			e.get(ind).setVidaActual(val);
		}
		tabla[e.get(ind).getFila()][e.get(ind).getColumna()]=e.get(ind);
	}
	static void compararSoldados(ArrayList<Soldado> e) {
		System.out.print("Indique el primer soldado a comparar: ");
		int pri = comprobarSeleccion(1, e.size())-1;
		System.out.print("Indique el segundo soldado a comparar: ");
		int seg = comprobarSeleccion(1, e.size())-1;
		
		if(e.get(pri).comparar(e.get(seg))) {
			System.out.println("Los soldados son iguales");
		} else 
			System.out.println("Los soldados no son iguales");
	}
	static void intercambiarSoldados(Soldado [][] tabla, ArrayList<Soldado> e) {
		System.out.print("Indique el primer soldado a intercambiar: ");
		int pri = comprobarSeleccion(1, e.size())-1;
		System.out.print("Indique el segundo soldado a intercambiar: ");
		int seg = comprobarSeleccion(1, e.size())-1;
		
		Soldado temp = e.get(pri);
		e.set(pri, e.get(seg));
		e.set(seg, temp);
		
		tabla[e.get(pri).getFila()][e.get(pri).getColumna()]=e.get(pri);
		tabla[e.get(seg).getFila()][e.get(seg).getColumna()]=e.get(seg);	
	}
	static void buscarSoldado(ArrayList<Soldado> e) {
		System.out.print("Indique el nombre del soldado a buscar: ");
		String nombre=sc.next();
		int cont=0;
		for (Soldado soldado : e) {
	        if (soldado.getNombre().equalsIgnoreCase(nombre)) {
	            System.out.println("SOLDADO ENCONTRADO");
	            System.out.println(soldado);
	            cont=1;
	            break;
	        }
	    }
		if(cont == 0) System.out.println("SOLDADO NO ENCONTRADO");
	}
	static void sumarNiveles(ArrayList<Soldado> e) {
		Soldado acumulador = new Soldado("Sumatoria", 0, 0, 0, 0, 0, 0, false);
		
		for (Soldado soldado : e) {
	        acumulador.sumar(soldado);
	    }
		imprimirEncabezado();
		System.out.println("    "+acumulador);
	}
	
	public static void main(String[] args) {
		while(true) {
			Soldado [][] misSoldados = new Soldado[10][10];
			ArrayList <Soldado> ejercito1 = new ArrayList<Soldado>();
			ArrayList <Soldado> ejercito2 = new ArrayList<Soldado>();
			
			generarEjercitos(misSoldados, ejercito1, 1);
			generarEjercitos(misSoldados, ejercito2, 2);
			
			while (true) {
			    int menu = menuPrincipal();
			    if (menu == 1) {
			        empezarJuego(misSoldados, ejercito1, ejercito2);

			        System.out.println("\n¿Desea simular otra batalla? Si/No");
			        String salir = sc.next();
			        if (salir.equalsIgnoreCase("si")) continue;
			        else break;
			    } 
			    else if (menu == 2) {
			        int e = seleccionarEjercito(ejercito1, ejercito2);
			        
			        ArrayList<Soldado> ejerSel = (e == 1) ? ejercito1 : ejercito2;
		        	imprimirSoldados(ejerSel);

			        while (true) {
			            int sm = opcionesSubmenu();

			            switch (sm) {
			                case 1: 
			                    if (ejerSel.size() < 10) {
			                        agregarSoldado(misSoldados, ejerSel);
			                    } else System.out.println("Limite de soldados alcanzado, elimine algunos soldados si desea agregar más.");
			                    break;

			                case 2: 
			                	if (ejerSel.size() > 1) {
			                		eliminarSoldado(misSoldados, ejerSel);
			                		System.out.println("");
			                	} else System.out.println("NO PUEDES ELIMINAR MAS SOLDADOS");
			                	break;
			                case 3: 
			                    if (ejerSel.size() < 10) {
			                    	clonarSoldado(misSoldados, ejerSel);
			                    }
			                    break;
			                case 4: 
			                    modificarSoldado(misSoldados, ejerSel);
			                    break;
			                case 5: 
			                    compararSoldados(ejerSel);
			                    break;
			                case 6: 
			                    intercambiarSoldados(misSoldados, ejerSel);
			                    break;
			                case 7: 
			                    buscarSoldado(ejerSel);
			                    break;
			                case 8: 
			                    imprimirSoldados(ejerSel);
			                    break;
			                case 9: 
			                    sumarNiveles(ejerSel);
			                    break;
			                case 10: 
			                    empezarJuego(misSoldados, ejercito1, ejercito2);
			                    break;
			                case 11: 
			                    break;
			            } 
			            if (sm == 11) break;
			        }
			    } 
			    else if (menu == 3) {
			        System.out.println("GRACIAS POR JUGAR");
			        break;
			    }
			}
		}
	}
	public static int menuPrincipal() {
		System.out.println("ELIJA UNA OPCIÓN");
		System.out.println("1. Juego rápido");
		System.out.println("2. Juego personalizado");
		System.out.println("3. Salir");		

		int op = comprobarSeleccion(1, 3);
		return op;
	}
	public static int opcionesSubmenu() {
		System.out.println("ELIJA UNA OPCIÓN");
		System.out.println("  1) Crear Soldado");
		System.out.println("  2) Eliminar Soldado");
		System.out.println("  3) Clonar Soldado");
		System.out.println("  4) Modificar Soldado");
		System.out.println("  5) Comparar Soldados");
		System.out.println("  6) Intercambiar Soldados");
		System.out.println("  7) Ver Soldado");
		System.out.println("  8) Ver Ejérrcito");
		System.out.println("  9) Sumar Niveles");
		System.out.println("  10) Jugar");
		System.out.println("  11) Volver");
		
		int op = comprobarSeleccion(1, 11);
		return op;
	}
	public static int seleccionarEjercito(ArrayList<Soldado> e1, ArrayList<Soldado> e2) {
		System.out.println("Seleccione su ejercito a gestionar: 1 ó 2");
		System.out.println("EJÉRCITO 1");
		imprimirSoldados(e1);
		System.out.println("EJÉRCITO 2");
		imprimirSoldados(e2);
			
		int valor = comprobarSeleccion(1, 2);
		return valor;
	}
	public static int comprobarSeleccion(int min, int may) {		
		while(true) {
			int val=sc.nextInt();
			sc.nextLine();
			if(val<min || val>may) {
				System.out.println("OPCIÓN NO VÁLIDA... VUELVA A ELEGIR");
				continue;
			} else return val;
		}	
	}
	static void generarEjercitos(Soldado[][] dat, ArrayList<Soldado> e, int num) {
		int cant=(int)(Math.random()*10+1);
		
		for(int i=0; i<cant; i++) {
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
				Soldado soldado = new Soldado(n + i, ataque, defensa, vida, fil, col, num, true);
				e.add(soldado);
				dat[fil][col] = soldado;
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
	            		    + dat[i][j].getNombre().charAt(dat[i][j].getNombre().length() - 1) 
	            		    + "|");
	            }
	        }
	        System.out.println();
	    }

	    System.out.println("\t     E1 = Ejercito 1 // E2 = Ejercito 2");
	}

	static void empezarJuego(Soldado [][] tab, ArrayList<Soldado> e1, ArrayList<Soldado> e2) {
		int c1=e1.size();
		int c2=e2.size();
		int sol=0;
		int cont=1;
		int ver=0;
		int e=0;
		boolean comprobar=false;
		System.out.println("\t\t\t   SOLDADOS CREADOS");
		imprimirSoldados(e1);
		imprimirSoldados(e2);
		System.out.println("\n\n\t\t|----INICIANDO LA BATALLA----|");
		mostrarTablero(tab);
		while(c1 != 0 || c2!=0) {
			if(cont%2 != 0) {
				e=1;
				imprimirSoldados(e1);
				System.out.print("Jugador 1: Seleccione su soldado: ");
				sol=sc.nextInt();
				if(sol>e1.size() || sol<1) {
					System.out.println("Soldado no encontrado, elija un soldado válido!!\n");
				} else if(!e1.get(sol-1).estaVivo()) {
					System.out.println("El soldado seleccionado ha caído en batalla elija otro.\n");
				} else { 
					comprobar=true;
				}
			} else {
				e=2;
				imprimirSoldados(e2);
				System.out.print("Jugador 2 seleccione su soldado: ");
				sol=sc.nextInt();
				if(sol>e2.size() || sol<1) {
					System.out.println("Soldado no encontrado, elija un soldado válido!!\n");
				} else if(!e2.get(sol-1).estaVivo()) {
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
							if(e1.size()==0) {
				    		    System.out.println("El ejército 1 ha sido derrotado. ¡Ejército 2 gana!");
				    		    ganador();
				    		    System.exit(0);
							} else if(e2.size()==0) {
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
	static int moverSoldados(Soldado[][] tab, ArrayList<Soldado> e1, ArrayList<Soldado> e2, int i, int f, int c, int e) {
		Soldado soldado = (e == 1) ? e1.get(i) : e2.get(i);

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
	public static void imprimirEncabezado() {
	    System.out.printf("%-2s | %-10s | %-3s | %-3s | %-3s | %-3s | %-4s | %-3s | %-4s | %-10s%n",
	                      "N°", "Nombre", "COL", "FIL", "ATQ", "DEF", "VIDA", "VEL", "Vive", "Actitud");
	    System.out.println("------------------------------------------------------------------------");
	}
	public static void imprimirSoldados(ArrayList<Soldado> e) {
		System.out.println();
		System.out.println("\t \t \tEJÉRCITO " + e.get(0).getEjercito() + " || SOLDADOS");
	    imprimirEncabezado();
	    for (int i = 0; i < e.size(); i++) {
	        System.out.printf("%-2d→|", (i + 1));
	        System.out.println(e.get(i));
	    }
	    System.out.println();
	}
	
	static Soldado buscarSoldado(ArrayList<Soldado> e, int f, int c) {
		for(int i=0; i<e.size(); i++) {
			if(e.get(i).getFila()==f && e.get(i).getColumna()==c) {
				return e.get(i);
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
	    double sum=s.getVidaActual()+sB.getVidaActual();
	    double prob1=(s.getVidaActual()*100)/sum;
	    prob1 = Math.round(prob1 * 100.0) / 100.0;
	    double prob2=100-prob1;
	    
	    double ganador = Math.random() * 100;
	    ganador = Math.round(ganador * 100.0) / 100.0;
	    System.out.println("\t\tPROBABILIDADES");
	    System.out.print("     "+(s.getNombre())+" "+ prob1+"%");
	    System.out.println("\t"+(sB.getNombre())+" "+ prob2+"%\n");
	    System.out.println("\t   NÚMERO ALEATORIO GENERADO\n\t\t   "+ ganador+"%");
	    
	    if (ganador <= prob1) {
            s.setVidaActual(s.getVidaActual()+1);
	    	return s;
        } else {
            sB.setVidaActual(sB.getVidaActual()+1);
        	return sB;
        }
	}
}
