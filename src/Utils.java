public class Utils {
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