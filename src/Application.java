import java.util.concurrent.Semaphore;

/*
RATE MY OS
 */
public class Application {
	public static void main(String[] args) {

		Semaphore semaphore = new Semaphore(1);

		Shell shell = new Shell(semaphore);
		shell.setName("shell");
		shell.start();

		OS os = new OS();
		os.run();

		while(true) {
			Integer entrada = Integer.parseInt(in.nextLine());
					os.carga("p" + shell.getProgram() + ".txt");
					shell.setProgram(null);

		}
	}
}