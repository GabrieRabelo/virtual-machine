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
	public void fibonacci(){
		Word[] p = new Programas().fibonacci;
		utils.carga(p, mem);
		cpu.setContext(0, tamMem - 1, 0);
		System.out.println("---------------------------------- programa carregado ");
		utils.dump(mem, 0, 16);
		System.out.println("---------------------------------- após execucao ");
		cpu.run();
		utils.dump(mem, 50, 60);
	}

	public void P2(){
		Word[] p = new Programas().P2;
		utils.carga(p, mem);
		cpu.setContext(0, tamMem - 1, 0);
		System.out.println("---------------------------------- programa carregado ");
		utils.dump(mem, 0, 16);
		System.out.println("---------------------------------- após execucao ");
		cpu.run();
		utils.dump(mem, 50, 60);
	}
}