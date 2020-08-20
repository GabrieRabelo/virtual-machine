// PUCRS, Escola Politécnica, Engenharia de Software
// Disciplina Sistemas Operacionais
// Prof. Fernando Luís Dotti
// Trabalho - Parte I
//
// Código fornecido pelo professor como uma forma de resolver o enunciado
// Este código compila e executa na VM o pequeno programa ao final, com somente tres instrucoes diferentes.
// Alem das definicoes dos elementos solicitados, os cuidados de acesso valido a memoria, instrucoes validas,
// interrupcoes, o ciclo de instrucao com as tres fases, ja estao contemplados.
// Pede-se estudar e enteder este codigo. Os alunos podem adotar ideias parecidas.   
// Falta implementar as demais instrucoes da CPU, assim como os programas solicitados.
// Este trabalho tem menos de 200 linhas de código.
// A VM completa, construida pelo professor, incluindo o programa P1, tem 234 linhas.


import java.util.*;

public class VM {

	// --------------------- definicoes de opcode e palavra de memoria ---------------------------------------
	private enum Opcode {
		DADO, ___,		    // se memoria nesta posicao tem um dado, usa DADO, se nao usada ee NULO
		JMP, JMPI, JMPIG, JMPIL, JMPIE, ADDI, SUBI, ANDI, ORI, LDI, LDD, STD, ADD, SUB, MULT, LDX, STX, SWAP, STOP;
	}

	private class Word { 	// cada posicao da memoria ee uma plavra, e tem uma instrucao (ou um dado)
		public Opcode opc; 	//
		public int r1; 		// indice do primeiro registrador da operacao (Rs ou Rd cfe opcode na tabela)
		public int r2; 		// indice do segundo registrador da operacao (Rc ou Rs cfe operacao)
		public int p; 		// parametro para instrucao (k ou A cfe operacao), ou o dado, se opcode = DADO

		public Word(Opcode _opc, int _r1, int _r2, int _p) {  
			opc = _opc;   r1 = _r1;    r2 = _r2;	p = _p;
		}
	}

    // --------------------- definicoes da CPU ---------------------------------------------------------------
	private enum Interrupts {  // possiveis interrupcoes
		noInterrupt, intEnderecoInvalido, intInstrucaoInvalida, intSTOP;
	}

	private class CPU {
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

	// -------------------------------------------  classes e funcoes auxiliares
	private class Aux {
		public void dump(Word w) {
			System.out.print("[ "); 
			System.out.print(w.opc); System.out.print(", ");
			System.out.print(w.r1);  System.out.print(", ");
			System.out.print(w.r2);  System.out.print(", ");
			System.out.print(w.p);   System.out.println("  ] ");
		}
		public void dump(Word[] m, int ini, int fim) {
			for (int i = ini; i < fim; i++) {
				System.out.print(i); System.out.print(":  ");  dump(m[i]);
			}
		}
		public void carga(Word[] p, Word[] m) {
			for (int i = 0; i < p.length; i++) {
				m[i].opc = p[i].opc;     m[i].r1 = p[i].r1;     m[i].r2 = p[i].r2;     m[i].p = p[i].p;
			}
		}
	}
	// -------------------------------------------  fim classes e funcoes auxiliares

	// -------------------------------------------- atributos e construcao da VM
	public int tamMem;    
    public Word[] m;     
    public CPU cpu;    
    public Aux aux;

    public VM(){
		tamMem = 1024;
		m = new Word[tamMem]; // m ee a memoria
		for (int i=0; i<tamMem; i++) { m[i] = new Word(Opcode.___,-1,-1,-1); };
		cpu = new CPU(m);
		aux = new Aux();
	}	

	// -------------------------------------------- teste da VM ,  veja classe de programas
	public void test1(){
		Word[] p = new Programas().progMinimo;
		aux.carga(p, m);
		cpu.setContext(0, tamMem - 1, 0);
		System.out.println("---------------------------------- programa carregado ");
		aux.dump(m, 0, 15);
		System.out.println("---------------------------------- após execucao ");
		cpu.run();
		aux.dump(m, 0, 15);
	}

   // ---------------------------------------------- instancia e testa VM
	public static void main(String args[]) {
		VM vm = new VM();
		vm.test1();
	}

   //  -------------------------------------------- programas aa disposicao para copiar na memoria (vide aux.carga)
   private class Programas {
	   public Word[] progMinimo = new Word[] {
		    new Word(Opcode.LDI, 0, -1, 999), 		
			new Word(Opcode.STD, 0, -1, 10), 
			new Word(Opcode.STD, 0, -1, 11), 
			new Word(Opcode.STD, 0, -1, 12), 
			new Word(Opcode.STD, 0, -1, 13), 
			new Word(Opcode.STD, 0, -1, 14), 
			new Word(Opcode.STOP, -1, -1, -1) };
    }
}