import java.util.LinkedList;

public class Escalonador {

	private LinkedList<PCB> prontos;
	private int pointer;

	public Escalonador(LinkedList<PCB> prontos) {
		this.prontos = prontos;
		this.pointer = 0;
	}

	public PCB escalona() {
		//Esse IF está aqui para o caso de um processo ser desalocado enquanto o pointer estava apontando para a última posição.
		if(pointer >= prontos.size()){
			pointer = 0;
		}
		PCB pcb = prontos.get(pointer);
		pointer = (pointer + 1) % prontos.size();
		return pcb;
	}

}
