//Archivo de prueba para crear imágenes Parte II.
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws Exception{
        try {
            BmpHandlerCore handler = new BmpHandlerCore("imagenesPrueba/image3.bmp");
            handler.RedImage("imagenesPrueba/Image-red.bmp");
            handler.GreenImage("imagenesPrueba/Image-green.bmp");
            handler.BlueImage("imagenesPrueba/Image-blue.bmp");
        } catch (IOException e) {
            System.out.println("ERROR");
        }
    }
}
