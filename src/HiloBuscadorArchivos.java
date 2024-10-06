import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class HiloBuscadorArchivos extends Thread{
    private final String fileName;
    private final String playId;

    public HiloBuscadorArchivos(String fileName, String playId) {
        this.fileName = fileName;
        this.playId = playId;
    }


    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        String threadName = Thread.currentThread().getName();

        System.out.println("Hilo " + threadName + " está buscando en el archivo: " + fileName);

        boolean found = false;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values[0].equals(playId)) {
                    found = true;
                    System.out.println("Hilo " + threadName + ": Encontrado en el archivo: " + fileName);
                    System.out.println("Información completa: " + line + "encontardo por " + threadName);
                    break;
                }
            }

            if (!found) {
                System.out.println("Hilo " + threadName + ": play_id " + playId + " no encontrado en el archivo: " + fileName);
            }

        } catch (IOException e) {
            System.out.println("Hilo " + threadName + ": Error al leer el archivo " + fileName);
        }

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        System.out.println("Hilo " + threadName + ": Tiempo de ejecución para el archivo " + fileName + ": " + duration + " ms");
    }
}
