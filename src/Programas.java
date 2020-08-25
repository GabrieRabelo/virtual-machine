import enums.Opcode;

public class Programas {
	public Word[] progMinimo = new Word[] {
			new Word(Opcode.LDI, 1, -1, 10),
			new Word(Opcode.LDI, 2, -1, 15),
			new Word(Opcode.ADDI, 1, -1, 20),
			new Word(Opcode.SUBI, 2, -1, 10),
			new Word(Opcode.STD, 1, -1, 13),
			new Word(Opcode.STD, 2, -1, 14),
			new Word(Opcode.STOP, -1, -1, -1)
	};
}