import enums.Opcode;

public class Programas {
	public Word[] progMinimo = new Word[] {
			new Word(Opcode.LDI, 1, -1, 10),
			new Word(Opcode.LDI, 2, -1, 25),
			new Word(Opcode.STX, 1, 2, -1),
			new Word(Opcode.SUBI, 2, -1, 10),


			new Word(Opcode.STD, 1, -1, 13),
			new Word(Opcode.STD, 2, -1, 14),
			new Word(Opcode.STOP, -1, -1, -1)
	};
}