import java.util.concurrent.Semaphore;

/*
RATE MY OS
 */
public class Application {
	public static void main(String[] args) {

		Semaphore semaphore = new Semaphore(1);

		Shell shell = new Shell(semaphore);
		shell.start();

		OS os = new OS();

		while(true) {
			try{
				if(shell.getProgram() != null) {
					semaphore.acquire();
					os.carga("p" + shell.getProgram() + ".txt");
					os.run();
					shell.setProgram(null);
					semaphore.release();
				}
			} catch (InterruptedException ie) {
				System.out.println("Programa Interrompido.");
			}
		}
	}
}