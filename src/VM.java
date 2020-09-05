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
import java.util.Scanner;

public class VM {

	public int tamMem;    
    public Word[] mem;
    public CPU cpu;    
    public Utils utils;

    public VM(){
		tamMem = 1024;
		mem = new Word[tamMem];
		for (int i=0; i<tamMem; i++)
			mem[i] = new Word(Opcode.___,-1,-1,-1);
		cpu = new CPU(mem);
		utils = new Utils();
	}

	/**
	 * Teste da VM
	 */
	private void run(Word[] p) {
		utils.carga(p, mem);
		cpu.setContext(0, tamMem - 1, 0);
		System.out.println("---------------------------------- programa carregado ");
		utils.dump(mem, 0, 16);
		System.out.println("---------------------------------- após execucao ");
		cpu.run();
		utils.dump(mem, 50, 60);
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
				String[] data = myReader.nextLine().split(" ");
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

}