public class Application {
	public static void main(String[] args) {
		String arquivo = "p1.txt";

		OS os = new OS();
		os.carga(arquivo);
		//os.run();
	}
}