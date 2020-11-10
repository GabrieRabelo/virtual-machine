import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Shell extends Thread {

	private Integer program;
	private Scanner in = new Scanner(System.in);
	private Semaphore semaphore;

	public Shell(Semaphore semaphore) {
		this.semaphore = semaphore;
	}

	public void run() {
		while(true) {
			try{
				semaphore.acquire();
				Integer entrada = Integer.parseInt(in.nextLine());
				program = entrada;
				semaphore.release();
			} catch (NumberFormatException nfe) {
				System.out.println("Apenas números inteiros");
			} catch (InterruptedException ie) {
				System.out.println("Programa Interrompido.");
			} finally {
				semaphore.release();
			}
		}
	}

	public Integer getProgram() {
		return program;
	}

	public void setProgram(Integer program) {
		this.program = program;
	}
}
