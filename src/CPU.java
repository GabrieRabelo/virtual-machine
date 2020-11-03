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
	private int[] allocatedPages;

	public CPU(Word[] memory) {
		this.memory = memory;
		registers = new int[8];
	}

	public int translateMemory(int address){
		System.out.println(address);
		return (allocatedPages[(address / 16)] * 16) + (address % 16);
	}

	public void setContext(int base, int limite, int[] allocatedPages, int programCounter) {
		this.base = base;
		this.limite = limite;
		this.allocatedPages = allocatedPages;
		this.programCounter = programCounter;
		this.interrupts = Interrupts.NO_INTERRUPT;
	}

	private boolean isLegal(int e) {
//		System.out.println("About to validate address " + e);
		if ((e < base) || (e > limite)) {
			interrupts = Interrupts.INT_ENDERECO_INVALIDO;
			return false;
		}
		//Check if the adress is in a legal page
		int page = e/16;
		boolean isLegal = false;
		for(int i =0; i< allocatedPages.length; i++){
			if (page == allocatedPages[i]) {
				isLegal = true;
				break;
			}
		}
		if(!isLegal){
			interrupts = Interrupts.INT_ENDERECO_INVALIDO;
			System.out.println("Páginas alocadas: \n");
			for(int i =0; i< allocatedPages.length; i++){
				System.out.println(allocatedPages[i]);
			}
			return false;
		}
		return true;
	}

	public void run() {
		while (true) {
			//Fetch
			if (isLegal(translateMemory(programCounter))) {
				instrucionRegister = memory[translateMemory(programCounter)];
				System.out.println(instrucionRegister);
				// EXECUTA INSTRUCAO NO ir
				switch (instrucionRegister.opCode) {
					case JMP: // PC ← k
						programCounter = instrucionRegister.param;
						break;

					case JMPI: // PC ← Rs
						programCounter = registers[instrucionRegister.r1];
						break;

					case JMPIG: // If Rc > 0 Then PC ← Rs Else PC ← PC +1
						if(registers[instrucionRegister.r1] > 0)
							programCounter = registers[instrucionRegister.r2];
						else
							programCounter++;
						break;

					case JMPIL: // if Rc < 0 then PC ← Rs  // Else PC ← PC +1
						if(registers[instrucionRegister.r1] < 0)
							programCounter = registers[instrucionRegister.r2];
						else
							programCounter++;
//                        System.out.println(programCounter);
						break;

					case JMPIE: // if Rc = 0 then PC ← Rs // Else PC ← PC +1
						if(registers[instrucionRegister.r1] == 0)
							programCounter = registers[instrucionRegister.r2];
						else
							programCounter++;
//                        System.out.println(programCounter);
						break;

					case JMPIM: // PC ← [A]
						if (isLegal(instrucionRegister.param)) {
							programCounter = instrucionRegister.param;
						}
						break;

					case JMPIGM: // if Rc > 0 then PC ← [A]  //Else PC ← PC +1
						if(registers[instrucionRegister.r1] > 0 && isLegal(translateMemory(instrucionRegister.param)))
							programCounter = instrucionRegister.param;
						else
							programCounter++;
//                        System.out.println(programCounter);
						break;

					case JMPILM: // if Rc < 0 then PC ← [A]  //Else PC ← PC +1
						if(registers[instrucionRegister.r1] < 0 && isLegal(translateMemory(instrucionRegister.param)))
							programCounter = instrucionRegister.param;
						else
							programCounter++;
//                        System.out.println(programCounter);
						break;

					case JMPIEM: // if Rc = 0 then PC ← [A] //Else PC ← PC +1
						if(registers[instrucionRegister.r1] == 0 && isLegal(translateMemory(instrucionRegister.param))) {
                            programCounter = instrucionRegister.param;
                        }else
							programCounter++;
//                        System.out.println(programCounter);
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
						if (isLegal(translateMemory(instrucionRegister.param))) {
							registers[instrucionRegister.r1] = this.memory[translateMemory(instrucionRegister.param)].param;
							programCounter++;
							break;
						}

					//implementado pelo professor
					case STD: // [A] ← Rs
						if (isLegal(translateMemory(instrucionRegister.param))) {
							memory[translateMemory(instrucionRegister.param)].opCode = Opcode.DADO;
							memory[translateMemory(instrucionRegister.param)].param = registers[instrucionRegister.r1];
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
						if (isLegal(translateMemory(registers[instrucionRegister.r2]))) {
							registers[instrucionRegister.r1] = memory[translateMemory(registers[instrucionRegister.r2])].param;
						}
						programCounter++;
						break;

					case STX: // [Rd] ←Rs

						if (isLegal(translateMemory(registers[instrucionRegister.r1]))) {
							memory[translateMemory(registers[instrucionRegister.r1])].opCode = Opcode.DADO;
							memory[translateMemory(registers[instrucionRegister.r1])].param = registers[instrucionRegister.r2];
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

					case DADO: //[A] <- [Rd]
						if (isLegal(translateMemory(instrucionRegister.param))) {
							memory[translateMemory(instrucionRegister.param)].opCode = Opcode.DADO;
							memory[translateMemory(instrucionRegister.param)].param = instrucionRegister.r1;
						}
						programCounter ++;
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