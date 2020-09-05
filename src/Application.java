public class Application {
	public static void main(String[] args) {
		VM vm = new VM();
		String arquivo = "p2.txt";
		vm.assembly(arquivo);
	}
}