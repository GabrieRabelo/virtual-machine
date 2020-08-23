import enums.Interrupts;
import enums.Opcode;

public class CPU {
	// característica do processador: contexto da CPU ...
	private int pc; 			// ... composto de program counter,
	private Word ir; 			// instruction register,
	private int[] reg;       	// registradores da CPU
	private Interrupts irpt; 	// durante instrucao, interrupcao pode ser sinalizada
	private int base;   		// base e limite de acesso na memoria
	private int limite; // por enquanto toda memoria pode ser acessada pelo processo rodando
	// ATE AQUI: contexto da CPU - tudo que precisa sobre o estado de um processo
	// para executar
	// nas proximas versoes isto pode modificar, e vai permitir salvar e restaurar
	// um processo na CPU

	private Word[] m;   // CPU acessa MEMORIA, guarda referencia 'm' a ela. memoria nao muda. ee sempre a mesma.

	public CPU(Word[] _m) {     // ref a MEMORIA passada na criacao da CPU
		m = _m; 				// usa o atributo 'm' para acessar a memoria.
		reg = new int[8]; 		// aloca o espaço dos registradores
	}

	public void setContext(int _base, int _limite, int _pc) {  // no futuro esta funcao vai ter que ser
		base = _base;                                          //expandida para setar TODO contexto de execucao,
		limite = _limite;									   // agora,  setamos somente os registradores base,
		pc = _pc;                                              // limite e pc (deve ser zero nesta versao)
		irpt = Interrupts.noInterrupt;                         // reset da interrupcao registrada
	}

	private boolean legal(int e) {                             // todo acesso a memoria tem que ser verificado
		if ((e < base) || (e > limite)) {                      //  valida se endereco 'e' na memoria ee posicao legal
			irpt = Interrupts.intEnderecoInvalido;             //  caso contrario ja liga interrupcao
			return false;
		};
		return true;
	}

	public void run() { 		// execucao da CPU supoe que o contexto da CPU, vide acima, esta devidamente setado
		while (true) { 			// ciclo de instrucoes. acaba cfe instrucao, veja cada caso.
			// FETCH
			if (legal(pc)) { 	// pc valido
				ir = m[pc]; 	// busca posicao da memoria apontada por pc, guarda em ir
				// EXECUTA INSTRUCAO NO ir
				switch (ir.opc) { // DADO,JMP,JMPI,JMPIG,JMPIL,JMPIE,ADDI,SUBI,ANDI,ORI,LDI,LDD,STD,ADD,SUB,MULT,LDX,STX,SWAP,STOP;

					case LDI: // Rd ← k
						reg[ir.r1] = ir.p;
						pc++;
						break;

					case STD: // [A] ← Rs
						if (legal(ir.p)) {
							m[ir.p].opc = Opcode.DADO;
							m[ir.p].p = reg[ir.r1];
							pc++;
						};
						break;

					case ADD: // Rd ← Rd + Rs

						break;

					case ADDI: // Rd ← Rd + k

						break;

					case STX: // [Rd] ←Rs

						break;

					case SUB: // Rd ← Rd - Rs

						break;

					case JMPIG: // If Rc > 0 Then PC ← Rs Else PC ← PC +1

						break;

					// falta entrar no switch JMP,JMPI,JMPIL,JMPIE,ADDI,ANDI,ORI,LDD,MULT,LDX,SWAP;

					case STOP: //  para execucao
						irpt = Interrupts.intSTOP;
						break;

					case DADO:

						break;

					default:

						break;
				}
			}
			// verifica int - agora simplesmente para programa em qualquer caso
			if (!(irpt == Interrupts.noInterrupt)) {
				System.out.print("Interrupcao ");
				System.out.println(irpt);
				break; // break sai do loop da cpu
			}
		}
	}
}