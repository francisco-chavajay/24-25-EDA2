package entregas.chavajayFrancisco.reto001;

import java.util.Arrays;

public class Frame1 {
    private char[][] matriz;
    private Clase clase;
    private Object posicionFrames; // Pixeles y ColoresCompuestos
    
    public void mostrar() {
        System.out.println("Mostrando Frame1: " + Arrays.deepToString(matriz));
    }
}