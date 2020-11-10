import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class Escalonador extends Thread {

	private LinkedList<PCB> prontos;
	private int pointer;
	private Semaphore escSemaphore;
	private Semaphore cpuSemaphore;
	private CPU cpu;

	public Escalonador(LinkedList<PCB> prontos, Semaphore escSemaphore, CPU cpu) {
		this.prontos = prontos;
		this.pointer = 0;
		this.escSemaphore = escSemaphore;
		this.cpu = cpu;
	}

	public void run() {
		while(true){
			try{
				escSemaphore.acquire();
				if(pointer >= prontos.size()){
					pointer = 0;
				}
				PCB pcb = prontos.get(pointer);
				int old = pointer;
				pointer = (pointer + 1) % prontos.size();
				prontos.remove(old);
				cpu.setContext(pcb.getContext());
				cpuSemaphore.release();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public LinkedList<PCB> getProntos() {
		return prontos;
	}
}
