//Parte I - Claudia

//import java.util.ArrayList;
import java.io.*;

public class BmpHandlerCore {
    private int ancho;
    private int alto;
    private byte[] header = new byte[54];
    private int[][] Rojo;
    private int[][] Verde;
    private int[][] Azul;

    //Constructor.
    public BmpHandlerCore(String archivo) throws Exception{
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(archivo));
        bis.read(header);
        this.ancho = readAncho();
        this.alto = readAlto();
        this.Rojo = new int[this.alto][this.ancho];
        this.Verde = new int[this.alto][this.ancho];
        this.Azul = new int[this.alto][this.ancho];
        readBmp(bis);
    }

    //Lee los pixeles.
    private void readBmp(BufferedInputStream archivo) throws Exception{
        for (int i = alto - 1; i >= 0; i--) {
            for (int j = 0; j < ancho; j++) {
                int blue = archivo.read() & 0xFF;
                int green = archivo.read() & 0xFF;
                int red = archivo.read() & 0xFF;

                Azul[i][j] = blue;
                Verde[i][j] = green;
                Rojo[i][j] = red;
            }
        }
    }

    //Métodos para leer datos de la imagen bmp.
    private int readAncho() {
        return getInt(header, 18);
    }
    private int readAlto() {
        return getInt(header, 22);
    }
    private static int getInt(byte[] data, int offset){
        return (data[offset + 0] & 0xFF) |
               ((data[offset + 1] & 0xFF) << 8) |
               ((data[offset + 2] & 0xFF) << 16) |
               ((data[offset + 3] & 0xFF) << 24);
    }

    //Creación de imagen bmp.
    private void writeBmp(String archivo, int[][] red, int[][] green, int[][] blue) throws IOException {
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(archivo))) {
            bos.write(header);
            for (int i = this.alto - 1; i >= 0; i--) {
                for (int j = 0; j < this.ancho; j++) {
                    bos.write(blue[i][j]);
                    bos.write(green[i][j]);
                    bos.write(red[i][j]);
                }
            }
            bos.write(0);
            bos.write(0);
        }
    }

    //Descarga de imágenes según el color.
    public void RedImage(String filePath) throws IOException {
        writeBmp(filePath, this.Rojo, new int[this.alto][this.ancho], new int[this.alto][this.ancho]);
    }
    public void GreenImage(String filePath) throws IOException {
        writeBmp(filePath, new int[this.alto][this.ancho], this.Verde, new int[this.alto][this.ancho]);
    }
    public void BlueImage(String filePath) throws IOException {
        writeBmp(filePath, new int[this.alto][this.ancho], new int[this.alto][this.ancho], this.Azul);
    }
}