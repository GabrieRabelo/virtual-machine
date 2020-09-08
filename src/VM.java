// PUCRS, Escola Politécnica, Engenharia de Software
// Disciplina Sistemas Operacionais
// Prof. Fernando Luís Dotti
// Trabalho - Parte I
//
// Código fornecido pelo professor como uma forma de resolver o enunciado
// Este código compila e executa na VM o pequeno programa ao final, com somente tres instrucoes diferentes.
// Alem das definicoes dos elementos solicitados, os cuidados de acesso valido a memoria, instrucoes validas,
// interrupcoes, o ciclo de instrucao com as tres fases, ja estao contemplados.
// Pede-se estudar e enteder este codigo. Os alunos podem adotar ideias parecidas.   
// Falta implementar as demais instrucoes da CPU, assim como os programas solicitados.
// Este trabalho tem menos de 200 linhas de código.
// A VM completa, construida pelo professor, incluindo o programa P1, tem 234 linhas.

import enums.Opcode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class VM {

	public int tamMem;    
    public Word[] mem;
    public CPU cpu;
//    public GM gm; vai para a classe OS
//    public HashMap<Integer, int[]> proccesses; vai para a classe OS em forma de Linked list, a principio

    public VM(){
		tamMem = 1024;
		mem = new Word[tamMem];
		for (int i=0; i<tamMem; i++)
			mem[i] = new Word(Opcode.___,-1,-1,-1);
		cpu = new CPU(mem);
		gm = new GM(tamMem);
		proccesses = new HashMap();
	}

	/**
	 * Teste da VM
	 */
	private void run(Word[] p) {
		carga(p, mem);
		cpu.setContext(0, tamMem - 1, 0);
		System.out.println("---------------------------------- programa carregado ");
		dump(mem, 0, 16);
		System.out.println("---------------------------------- após execucao ");
		cpu.run();
		dump(mem, 50, 60);
		// Aqui iremos também chamar uma nova classe, o GM (Gerente de Memória) para desalocar a memória
	}

	public void assembly(String arquivo){
		String path = "src/in/" + arquivo;
		int size = getFileSize(path);
		Word[] programa = new Word[size];
		int line = 0;
		try {
			File myObj = new File(path);
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String[] data = myReader.nextLine().replaceAll("\\s+","").split(",");
				Opcode code = Opcode.valueOf(data[0]);
				int r1 = Integer.parseInt(data[1]);
				int r2 = Integer.parseInt(data[2]);
				int param = Integer.parseInt(data[3]);
				programa[line] = new Word(code, r1, r2, param);
				line++;
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		run(programa);
	}

	private int getFileSize(String path) {
		int line = 0;
		try {
			File myObj = new File(path);
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				myReader.nextLine();
				line++;
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return line;
	}

	//utils
	private void dump(Word w) {
		System.out.print("[ ");
		System.out.print(w.opCode); System.out.print(", ");
		System.out.print(w.r1);  System.out.print(", ");
		System.out.print(w.r2);  System.out.print(", ");
		System.out.print(w.param);   System.out.println("  ] ");
	}
	private void dump(Word[] m, int ini, int fim) {
		for (int i = ini; i < fim; i++) {
			System.out.print(i); System.out.print(":  ");  dump(m[i]);
		}
	}
	private void carga(Word[] p, Word[] m) {
		//Aqui teremos uma lista de processos. No caso, pode ser um dict com id, numero do processo e lista de páginas da memória. Talvez o carga pode retornar esse dict para a VM
		// Aqui na carga iremos também chamar uma nova classe, o GM (Gerente de Memória) para alocarmos a memória]
		int[] allocated = gm.alloc(p.length);

		proccesses.put(1, allocated); //hard code.

		for (int i = 0; i < p.length; i++) {
			m[i].opCode = p[i].opCode;     m[i].r1 = p[i].r1;     m[i].r2 = p[i].r2;     m[i].param = p[i].param;
		} 	
	}

}