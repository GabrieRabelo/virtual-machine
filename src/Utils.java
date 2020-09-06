public class Utils {
	public void dump(Word w) {
		System.out.print("[ ");
		System.out.print(w.opCode); System.out.print(", ");
		System.out.print(w.r1);  System.out.print(", ");
		System.out.print(w.r2);  System.out.print(", ");
		System.out.print(w.param);   System.out.println("  ] ");
	}
	public void dump(Word[] m, int ini, int fim) {
		for (int i = ini; i < fim; i++) {
			System.out.print(i); System.out.print(":  ");  dump(m[i]);
		}
	}
	public void carga(Word[] p, Word[] m) {
	    //Aqui teremos uma lista de processos. No caso, pode ser um dict com id, numero do processo e lista de páginas da memória. Talvez o carga pode retornar esse dict para a VM
        // Aqui na carga iremos também chamar uma nova classe, o GM (Gerente de Memória) para alocarmos a memória]
		for (int i = 0; i < p.length; i++) {
			m[i].opCode = p[i].opCode;     m[i].r1 = p[i].r1;     m[i].r2 = p[i].r2;     m[i].param = p[i].param;
		}
	}
}