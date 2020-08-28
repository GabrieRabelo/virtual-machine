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
}