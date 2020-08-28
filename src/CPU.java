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
						programCounter = instrucionRegister.param;
						break;

					case JMPI: // PC ← Rs
						programCounter = registers[instrucionRegister.r1];
						break;

					case JMPIG: // If Rc > 0 Then PC ← Rs Else PC ← PC +1

						break;

					case JMPIL: // if Rc < 0 then PC ← Rs
						// Else PC ← PC +1
						break;

					case JMPIE: // if Rc = 0 then PC ← Rs
						// Else PC ← PC +1
						break;

					case JMPIM: // PC ← [A]
						break;

					case JMPIGM: // if Rc > 0 then PC ← [A]
						//Else PC ← PC +1
						break;

					case JMPILM: // if Rc < 0 then PC ← [A]
						//Else PC ← PC +1
						break;

					case JMPIEM: // if Rc = 0 then PC ← [A]
						//Else PC ← PC +1
						break;

					case ADDI: // Rd ← Rd + k
						registers[instrucionRegister.r1] = registers[instrucionRegister.r1] + instrucionRegister.param;
						programCounter++;
						break;

					case SUBI: // Rd ← Rd – k
						registers[instrucionRegister.r1] = registers[instrucionRegister.r1] - instrucionRegister.param;
						programCounter++;
						break;

					//implementado pelo professor
					case LDI: // Rd ← k
						registers[instrucionRegister.r1] = instrucionRegister.param;
						programCounter++;
						break;

					case LDD: // Rd ← [A]
						if (isLegal(instrucionRegister.param)) {
							registers[instrucionRegister.r1] = this.memory[instrucionRegister.param].param;
							programCounter++;
							break;
						}

					//implementado pelo professor
					case STD: // [A] ← Rs
						if (isLegal(instrucionRegister.param)) {
							memory[instrucionRegister.param].opCode = Opcode.DADO;
							memory[instrucionRegister.param].param = registers[instrucionRegister.r1];
							programCounter++;
						}
						break;

					case ADD: // Rd ← Rd + Rs
						registers[instrucionRegister.r1] = registers[instrucionRegister.r1] + registers[instrucionRegister.r2];
						programCounter++;
						break;

					case SUB: // Rd ← Rd - Rs
						registers[instrucionRegister.r1] = registers[instrucionRegister.r1] - registers[instrucionRegister.r2];
						programCounter++;
						break;

					case MULT: // Rd ← Rd * Rs
						registers[instrucionRegister.r1] = registers[instrucionRegister.r1] * registers[instrucionRegister.r2];
						programCounter++;
						break;

					case LDX: // Rd ← [Rs]
						if (isLegal(registers[instrucionRegister.r2])) {
							registers[instrucionRegister.r1] = memory[registers[instrucionRegister.r2]].param;
						}
						programCounter++;
						break;

					case STX: // [Rd] ←Rs
						if (isLegal(registers[instrucionRegister.r1])) {
							memory[registers[instrucionRegister.r1]].param = registers[instrucionRegister.r2];
						}
						programCounter++;
						break;

					case SWAP: //T ← Rd  Rd ← Rs  Rs ← T Eu não sei se esse T pode ser uma variavel comum ou se será uma LocalStack da CPU
						int T = registers[instrucionRegister.r1];
						registers[instrucionRegister.r1] = registers[instrucionRegister.r2];
						registers[instrucionRegister.r2] = T;
						programCounter++;
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