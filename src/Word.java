import enums.Opcode;

public class Word { 	// cada posicao da memoria ee uma plavra, e tem uma instrucao (ou um dado)
	public Opcode opc; 	//
	public int r1; 		// indice do primeiro registrador da operacao (Rs ou Rd cfe opcode na tabela)
	public int r2; 		// indice do segundo registrador da operacao (Rc ou Rs cfe operacao)
	public int p; 		// parametro para instrucao (k ou A cfe operacao), ou o dado, se opcode = DADO

	public Word(Opcode _opc, int _r1, int _r2, int _p) {
		opc = _opc;   r1 = _r1;    r2 = _r2;	p = _p;
	}
}