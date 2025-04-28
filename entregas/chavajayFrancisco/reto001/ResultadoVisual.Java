package entregas.chavajayFrancisco.reto001;

import java.util.Arrays;

public class ResultadoVisual {
    private char[][] matriz;
    private Clase clase;

    public void combinar(Frame1 frame1, Frame2 frame2) {
        System.out.println("Combinando Frame1 y Frame2 en ResultadoVisual...");
    }
    
    public void mostrar() {
        System.out.println("Mostrando ResultadoVisual: " + Arrays.deepToString(matriz));
    }
}