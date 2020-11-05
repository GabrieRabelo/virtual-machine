/*
RATE MY OS
 */
public class Application {
	public static void main(String[] args) {

		OS os = new OS();

		//Cria processos na memória e coloca na fila de prontos
		os.carga("p1.txt");
		os.carga("p2.txt");
		os.carga("p3.txt");
		os.carga("p4.txt");

		os.run();

	}
}