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

import enums.Interrupts;
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

    public VM(){
		tamMem = 1024;
		mem = new Word[tamMem];
		for (int i=0; i<tamMem; i++)
			mem[i] = new Word(Opcode.___,-1,-1,-1);
		cpu = new CPU(mem);
	}

	/**
	 * Teste da VM
	 */
	public Interrupts run(PCB proccess) {
		cpu.setContext(0, tamMem - 1, proccess.getAllocatedPages(), 0);
		switch(cpu.run()){
			case INT_STOP:
				return Interrupts.INT_STOP;
			case INT_ENDERECO_INVALIDO:
				return  Interrupts.INT_ENDERECO_INVALIDO;
			case INT_INSTRUCAO_INVALIDA:
				return Interrupts.INT_INSTRUCAO_INVALIDA;
		}
		return Interrupts.INT_TIMER;
	}



	public void dump(int ini, int fim) {
		for (int i = ini; i < fim; i++) {
			System.out.println(i + ": " + mem[i]);
		}
	}
}