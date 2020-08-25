import enums.Opcode;

public class Programas {
	public Word[] progMinimo = new Word[] {
			new Word(Opcode.LDI, 1, -1, 10),
			new Word(Opcode.LDI, 2, -1, 15),
			new Word(Opcode.LDI, 3, -1, 14),
//			new Word(Opcode.STD, 0, -1, 10),
//			new Word(Opcode.STD, 0, -1, 11),
//			new Word(Opcode.STD, 0, -1, 12),
//			new Word(Opcode.STD, 0, -1, 13),
//			new Word(Opcode.STD, 0, -1, 14),
//			new Word(Opcode.STOP, -1, -1, -1) };
			new Word(Opcode.MULT, 1, 2, -1),
			new Word(Opcode.STD, 1, -1, 14),
			new Word(Opcode.LDX, 4, 3, -1),
			new Word(Opcode.STD, 4, -1, 13),
			new Word(Opcode.STOP, -1, -1, -1)
	};
}