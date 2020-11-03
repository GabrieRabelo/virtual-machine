/*
RATE MY OS
 */
public class Application {
	public static void main(String[] args) {

		OS os = new OS();
		os.carga("p1.txt");
		os.carga("p2.txt");
		os.carga("p3.txt");
		os.carga("p4.txt");

		os.run(0);
		os.run(1);
		os.run(2);
		os.run(3);
	}
}