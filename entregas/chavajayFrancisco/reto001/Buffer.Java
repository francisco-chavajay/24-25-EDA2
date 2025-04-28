package entregas.chavajayFrancisco.reto001;

public class Buffer {
    public void mostrarFrameElegido(Frame1 frame1, Frame2 frame2, ResultadoVisual resultado) {
        System.out.println("Mostrando frame elegido...");
        frame1.mostrar();
        frame2.mostrar();
        resultado.mostrar();
    }
}