import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Escalonador extends Thread {

	private LinkedList<PCB> prontos;
	private int pointer;
	private Semaphore escSemaphore;
	private Semaphore cpuSemaphore;
	private PCB runningProcess;
	private CPU cpu;

	public Escalonador() { }

	@Override
	public void run() {
		while(true){
			try{
				escSemaphore.acquire();
				if(prontos.isEmpty()){
					continue;
				}
				PCB pcb = prontos.get(pointer);
				this.runningProcess = pcb;
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

	public void setAttributes(LinkedList<PCB> prontos, Semaphore escSemaphore, Semaphore cpuSemaphore, CPU cpu){
		this.prontos = prontos;
		this.pointer = 0;
		this.escSemaphore = escSemaphore;
		this.cpuSemaphore = cpuSemaphore;
		this.cpu = cpu;
	}

	public PCB getRunningProcess() {
		return runningProcess;
	}

	public void setRunningProcessAsNull(){
		this.runningProcess = null;
	}

	public List<PCB> getProntos() {
		return prontos;
	}
}
