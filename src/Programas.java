import enums.Opcode;

public class Programas {
	public Word[] fibonacci = new Word[] {
			//Dois primeiros termos (0 e 1)
			new Word(Opcode.LDI, 1, -1, 0),
			new Word(Opcode.LDI, 2, -1, 1),
			//Qtd de números de fibonacci
			new Word(Opcode.LDI, 5, -1, 10),
			//Carregando o valor 50 no R4 (ponteiro para a primeira posição que iremos salvar o primeiro número da sequência
			new Word(Opcode.LDI, 4, -1, 50),
			//Salvando 0 e 1 nas posições 50 e 51 da memória --> STX [Rd],Rs
			new Word(Opcode.STX, 4, 1, -1),
			new Word(Opcode.ADDI, 4, -1, 1),
			new Word(Opcode.STX, 4, 2, -1),
			//Posição da memória que começa a repetição no R6 (posição 8)
			new Word(Opcode.LDI, 6, -1, 8),

			//Repetição:
			//Apontamos para o valor anterior na memória
			new Word(Opcode.SUBI, 4, -1, 1),
			//Carrega no R3 o valor anterior (carrega direto da memória usando o ponteiro R4 que criamos)
			new Word(Opcode.LDX, 3, 4, 0),
			//Somando termo atual e o anterior
			new Word(Opcode.ADD, 2, 3, -1),
			//Retorna o ponteiro R4 para a próxima posição a salvar
			new Word(Opcode.ADDI, 4, -1, 2),
			//Salvamos o novo número na memória
			new Word(Opcode.STX, 4, 2, -1),
			//Testa se deve continuar a repetição ou parar
			new Word(Opcode.SUBI, 5, -1, 1),
			new Word(Opcode.JMPIG, 5, 6, -1),

			//Fim
			new Word(Opcode.STOP, -1, -1, -1)
	};


	/*, um programa que le um valor de uma determinada posição (carregada no inicio),
 se o número for menor que zero coloca -1 no início da posição de memória para saída;
 se for maior que zero este é o número de valores
 da sequencia de fibonacci a serem escritos em sequencia a partir de uma posição de
 memoria;*/

	public Word[] P2 = new Word[] {
			//Determinada Posição será carregada no início do programa no R5
			new Word(Opcode.LDI, 5, -1, 1),
			//se o número for menor que zero, aponta para onde bota -1 na posição 50 e acaba
			new Word(Opcode.JMPILM, 5, -1, 21),

			//se o número for igual a zero, aponta para onde bota -1 na posição 0 e acaba
			new Word(Opcode.JMPIEM, 5, -1, 21),

			//se o número for igual a um, aponta para onde escreve 0 e acaba
            new Word(Opcode.LDI, 1, -1, 1),
            new Word(Opcode.SUB, 1, 5, -1), //r1 = r1 - r5 (1 -1)
			new Word(Opcode.JMPIEM, 1, -1, 25),

			//fibonacci de antes
            new Word(Opcode.LDI, 1, -1, 0),
            new Word(Opcode.LDI, 2, -1, 1),
            new Word(Opcode.LDI, 4, -1, 50),
            new Word(Opcode.STX, 4, 1, -1),
            new Word(Opcode.ADDI, 4, -1, 1),
            new Word(Opcode.STX, 4, 2, -1),
            new Word(Opcode.LDI, 6, -1, 13),
            new Word(Opcode.SUBI, 4, -1, 1),
            new Word(Opcode.LDX, 3, 4, 0),
            new Word(Opcode.ADD, 2, 3, -1),
            new Word(Opcode.ADDI, 4, -1, 2),
            new Word(Opcode.STX, 4, 2, -1),
            new Word(Opcode.SUBI, 5, -1, 1),
            new Word(Opcode.JMPIG, 5, 6, -1),
            new Word(Opcode.STOP, -1, -1, -1),

	//bota -1 na posição 0 e acaba - posição 21
			new Word(Opcode.LDI, 1, -1, 50),
			new Word(Opcode.LDI, 2, -1, -1),
			new Word(Opcode.STX, 1, 2, -1),
            new Word(Opcode.STOP, -1, -1, -1),
	//escreve 0 e acaba - posição 25
			new Word(Opcode.LDI, 4, -1, 50),
			new Word(Opcode.LDI, 1, -1, 0),
			new Word(Opcode.STX, 4, 1, -1),
            new Word(Opcode.STOP, -1, -1, -1),

			//Fim - posição 29
			new Word(Opcode.STOP, -1, -1, -1)
	};

	/*dado um inteiro em alguma posição de memória,
 	se for negativo armazena -1 na saída;
 	se for positivo responde o fatorial do número na saída*/
	public Word[] P3 = new Word[]{
		new Word(Opcode.LDI,1,-1,10),
		new Word(Opcode.JMPILM,1,-1,9),
		new Word(Opcode.LDI,2,-1,1),
		//loop
		new Word(Opcode.MULT,2,1,-1),
		new Word(Opcode.LDI,3,-1,1),
		new Word(Opcode.SUB,1,3,-1),
		new Word(Opcode.JMPIGM,1,-1,3),
		new Word(Opcode.STD,2,-1,50),
		new Word(Opcode.STOP,-1,-1,-1),

		new Word(Opcode.LDI,1,-1,-1),
		new Word(Opcode.STD,1,-1,50),
		new Word(Opcode.STOP,-1,-1,-1),

	};

/*para um N definido (5 por exemplo)
o programa ordena um vetor de N números em alguma posição de memória;
ordena usando bubble sort
loop ate que não swap nada
passando pelos N valores
faz swap de vizinhos se da esquerda maior que da direita*/
	public Word[] P4 = new Word[]{


	};

}