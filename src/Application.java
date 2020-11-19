import java.util.Scanner;

/*
RATE MY OS
 */
public class Application {
	public static void main(String[] args) throws InterruptedException {

		BootAnimation ba = new BootAnimation();
		ba.load();

		OS os = new OS();

		Scanner in = new Scanner(System.in);
		int entrada;
		while(true) {

			try{
				System.out.println("\nDigite o número do programa a ser executado: ");
				entrada = Integer.parseInt(in.nextLine());
				if(entrada == -1)
					Runtime.getRuntime().exit(1);
			} catch (NumberFormatException nfe) {
				System.out.println("Apenas números!");
				continue;
			}
			os.carga("p" + entrada + ".txt");
		}
	}
}