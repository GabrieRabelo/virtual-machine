import java.util.Scanner;
import java.util.concurrent.Semaphore;

/*
RATE MY OS
 */
public class Application {

    public static void main(String[] args) throws InterruptedException {

        BootAnimation ba = new BootAnimation();
        ba.load();
        int entrada;
        Semaphore semaphore = new Semaphore(0);
        OS os = new OS(semaphore);

        Scanner in = new Scanner(System.in);

        while (true) {

            try {
                System.out.println("\nDigite uma entrada, iniciando por S para shell e C para console");
                String palavra = in.nextLine();
                entrada = Integer.parseInt(palavra.split(" ")[1]);

                if (palavra.split(" ")[0].equals("s")) {
	                os.carga("p" + entrada + ".txt");
	                continue;
                } else if(palavra.split(" ")[0].equals("c")) {
	                os.setEntradaConsole(entrada);
	                continue;
                }
                //entrada = Integer.parseInt(in.nextLine());
                if (entrada == -1)
                    Runtime.getRuntime().exit(1); break;

            } catch (NumberFormatException nfe) {
                System.out.println("Apenas números!");
                continue;
            }


        }
    }
}