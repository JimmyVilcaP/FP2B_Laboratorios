package lab3;

public class Ejercicio5 {
	public static void main(String[] args) {
		int cant1=(int)(Math.random()*5+1);
		int cant2=(int)(Math.random()*5+1);
		Ejercito [] miEjercito1 = new Ejercito[cant1];
		Ejercito [] miEjercito2 = new Ejercito[cant2];
		iniciarDatos(miEjercito1, miEjercito2);
		mostrarDatos(miEjercito1, miEjercito2);
	}
	static void iniciarDatos(Ejercito [] m1, Ejercito [] m2) {
		final String name="Soldado";
		for(int i=0; i<m1.length; i++) {
			m1[i]=new Ejercito();
			m1[i].setNombre(name+(i+1));
		}
		for(int j=0; j<m2.length; j++) {
			m2[j]=new Ejercito();
			m2[j].setNombre(name+(j+1));
		}
	}
	static void mostrarDatos(Ejercito [] m1, Ejercito [] m2) {
		System.out.println("\tEJ�RCITO 1");
		for(int i=0; i<m1.length; i++) {
			System.out.println(m1[i].getNombre());
		}
		System.out.println("\n\tEJ�RCITO 2");
		for(int j=0; j<m2.length; j++) {
			System.out.println(m2[j].getNombre());
		}
		if(m1.length>m2.length)
			System.out.print("\nEl ganador es el ej�rcito 1.");
		else if(m1.length<m2.length)
			System.out.print("\nEl ganador es el ej�rcito 2");
		else
			System.out.print("\nEj�rcitos empatados, no hay ganador.");
	}
}
