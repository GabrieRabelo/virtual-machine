import enums.Opcode;

public class Programas {
	public Word[] progMinimo = new Word[] {
			new Word(Opcode.LDI, 0, -1, 999),
			new Word(Opcode.STD, 0, -1, 10),
			new Word(Opcode.STD, 0, -1, 11),
			new Word(Opcode.STD, 0, -1, 12),
			new Word(Opcode.STD, 0, -1, 13),
			new Word(Opcode.STD, 0, -1, 14),
			new Word(Opcode.STOP, -1, -1, -1) };
}