package entregas.chavajayFrancisco.reto001;

import java.util.Arrays;

public class Frame2 {
    private char[][] matriz;
    private Clase clase;
    private Object posicionFrames; // Pixeles y ColoresCompuestos
    
    public void mostrar() {
        System.out.println("Mostrando Frame2: " + Arrays.deepToString(matriz));
    }
}