import java.util.LinkedList;

public class Escalonador {

	private LinkedList<PCB> prontos;
	private int pointer;

	public Escalonador(LinkedList<PCB> prontos) {
		this.prontos = prontos;
		this.pointer = 0;
	}

	public PCB escalona() {
		PCB pcb = prontos.get(pointer);
		pointer = (pointer + 1) % prontos.size();
		return pcb;
	}

}
