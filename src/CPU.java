import enums.Interrupts;
import enums.Opcode;

public class CPU {

	private int pc;
	private Word ir;
	private int[] reg;
	private Interrupts interrupts;
	private int base;
	private int limite;
	private Word[] mem;

	public CPU(Word[] mem) {
		this.mem = mem;
		reg = new int[8];
	}

	public void setContext(int base, int limite, int pc) {
		this.base = base;
		this.limite = limite;
		this.pc = pc;
		this.interrupts = Interrupts.noInterrupt;
	}

	private boolean isLegal(int e) {
		if ((e < base) || (e > limite)) {
			interrupts = Interrupts.intEnderecoInvalido;
			return false;
		}
		return true;
	}

	public void run() {
		while (true) {
			//Fetch
			if (isLegal(pc)) {
				ir = mem[pc];
				// EXECUTA INSTRUCAO NO ir
				switch (ir.opc) {
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
						reg[ir.r1] = ir.p;
						pc++;
						break;

					case LDD: // Rd ← [A]

						break;

					case STD: // [A] ← Rs
						if (isLegal(ir.p)) {
							mem[ir.p].opc = Opcode.DADO;
							mem[ir.p].p = reg[ir.r1];
							pc++;
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
						interrupts = Interrupts.intSTOP;
						break;

					case DADO:

						break;

					default:

						break;
				}
			}

			if (interrupts != Interrupts.noInterrupt) {
				System.out.print("Interrupcao ");
				System.out.println(interrupts);
				break;
			}
		}
	}
}