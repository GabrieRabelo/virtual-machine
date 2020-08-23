import enums.Interrupts;
import enums.Opcode;

public class CPU {

	private int programCounter;
	private Word instrucionRegister;
	private int[] registers;
	private Interrupts interrupts;
	private int base;
	private int limite;
	private Word[] memory;

	public CPU(Word[] memory) {
		this.memory = memory;
		registers = new int[8];
	}

	public void setContext(int base, int limite, int programCounter) {
		this.base = base;
		this.limite = limite;
		this.programCounter = programCounter;
		this.interrupts = Interrupts.NO_INTERRUPT;
	}

	private boolean isLegal(int e) {
		if ((e < base) || (e > limite)) {
			interrupts = Interrupts.INT_ENDERECO_INVALIDO;
			return false;
		}
		return true;
	}

	public void run() {
		while (true) {
			//Fetch
			if (isLegal(programCounter)) {
				instrucionRegister = memory[programCounter];
				// EXECUTA INSTRUCAO NO ir
				switch (instrucionRegister.opCode) {
					case JMP: // PC ← k

						break;

					case JMPI: // PC ← Rs

						break;

					case JMPIG: // If Rc > 0 Then PC ← Rs Else PC ← PC +1

						break;

					case JMPIL: // if Rc < 0 then PC ← Rs
						// Else PC ← PC +1
						break;

					case JMPIE: // if Rc = 0 then PC ← Rs
						// Else PC ← PC +1
						break;

					case ADDI: // Rd ← Rd + k

						break;

					case SUBI: // Rd ← Rd – k

						break;
					case ANDI: 	// Rd ←Rd AND k

						break;

					case ORI: // Rd ←Rd OR k

						break;

					case LDI: // Rd ← k
						registers[instrucionRegister.r1] = instrucionRegister.param;
						programCounter++;
						break;

					case LDD: // Rd ← [A]

						break;

					case STD: // [A] ← Rs
						if (isLegal(instrucionRegister.param)) {
							memory[instrucionRegister.param].opCode = Opcode.DADO;
							memory[instrucionRegister.param].param = registers[instrucionRegister.r1];
							programCounter++;
						}
						break;

					case ADD: // Rd ← Rd + Rs

						break;

					case SUB: // Rd ← Rd - Rs

						break;

					case MULT: // Rd ← Rd * Rs

						break;

					case LDX: // Rd ← [Rs]

						break;

					case STX: // [Rd] ←Rs

						break;

					case SWAP: //Rd7←Rd3, Rd6←Rd2,
								//Rd5←Rd1, Rd4←Rd0
						break;

					case STOP:
						interrupts = Interrupts.INT_STOP;
						break;

					case DADO:

						break;

					default:

						break;
				}
			}

			if (interrupts != Interrupts.NO_INTERRUPT) {
				System.out.print("Interrupcao ");
				System.out.println(interrupts);
				break;
			}
		}
	}
}