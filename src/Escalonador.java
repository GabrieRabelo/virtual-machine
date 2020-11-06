import java.util.LinkedList;

public class Escalonador {

	private LinkedList<PCB> prontos;
	private int pointer;
	private VM vm;

	public Escalonador(LinkedList<PCB> prontos, VM vm) {
		this.prontos = prontos;
		this.pointer = 0;
		this.vm = vm;
	}

	public void escalona() {
		PCB pcb = prontos.get(pointer);
		pointer = (pointer + 1) % prontos.size();
		vm.run(pcb);
	}

}
