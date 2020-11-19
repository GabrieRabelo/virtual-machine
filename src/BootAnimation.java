public class BootAnimation {
	private String fakeCommandsTxt;
	private String OSName;

	public BootAnimation() {
		this.fakeCommandsTxt = "Press F12 Boot for boot Menu or Del for Setup";
		this.OSName = "    __  ___                           ____  _____\n" +
				"   /  |/  /___ _____ ___  ____ ______/ __ \\/ ___/\n" +
				"  / /|_/ / __ `/ __ `__ \\/ __ `/ ___/ / / /\\__ \\ \n" +
				" / /  / / /_/ / / / / / / /_/ / /__/ /_/ /___/ / \n" +
				"/_/  /_/\\__,_/_/ /_/ /_/\\__,_/\\___/\\____//____/  ";
	}

	public void load() throws InterruptedException {
		System.out.println(fakeCommandsTxt);
		System.out.println("\n");
		System.out.println(OSName);
		System.out.println("\n");

		System.out.print("Loading");
		for(int i=0; i<10; i++) {
			System.out.print(".");
			Thread.sleep(250);
		}
	}
}
