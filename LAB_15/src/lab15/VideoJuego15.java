package lab15;
import java.util.*;

public class VideoJuego15 {
    public static void main(String[] args) {
        List<String> nombresReinos = Arrays.asList("Inglaterra", "Francia", "Sacro Imperio", "Castilla-Aragón", "Moros");
        Random random = new Random();

        String reinoNombre1 = nombresReinos.get(random.nextInt(nombresReinos.size()));
        String reinoNombre2;
        
        do {
            reinoNombre2 = nombresReinos.get(random.nextInt(nombresReinos.size()));
        } while (reinoNombre1.equals(reinoNombre2));
        
        
        
        // Crear e iniciar el juego
        Juego juego = new Juego(reinoNombre1, reinoNombre2);

        System.out.println("¡Juego finalizado entre " + reinoNombre1 + " y " + reinoNombre2 + "!");
    }
}

