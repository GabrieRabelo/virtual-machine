import enums.Opcode;

public class Word { 	// cada posicao da memoria ee uma plavra, e tem uma instrucao (ou um dado)
	public Opcode opCode; 	//
	public int r1; 		// indice do primeiro registrador da operacao (Rs ou Rd cfe opcode na tabela)
	public int r2; 		// indice do segundo registrador da operacao (Rc ou Rs cfe operacao)
	public int param; 		// parametro para instrucao (k ou A cfe operacao), ou o dado, se opcode = DADO

	public Word(Opcode opCode, int r1, int r2, int param) {
		this.opCode = opCode;   this.r1 = r1;    this.r2 = r2;	this.param = param;
	}

	public String toString() {
		return "[ " + opCode + ", " + r1 + ", " + r2 + ", " + param + " ]";
	}
}