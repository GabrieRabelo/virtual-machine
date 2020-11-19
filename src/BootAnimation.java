public class BootAnimation {
	private String fakeCommands;
	private String OSName;

	public BootAnimation() {
		this.fakeCommands = "Press F12 Boot for boot Menu or Del for Setup";
		this.OSName = "    __  ___                           ____  _____\n" +
				"   /  |/  /___ _____ ___  ____ ______/ __ \\/ ___/\n" +
				"  / /|_/ / __ `/ __ `__ \\/ __ `/ ___/ / / /\\__ \\ \n" +
				" / /  / / /_/ / / / / / / /_/ / /__/ /_/ /___/ / \n" +
				"/_/  /_/\\__,_/_/ /_/ /_/\\__,_/\\___/\\____//____/  ";
	}

	public void load() throws InterruptedException {
		System.out.println(fakeCommands);
		System.out.println("\n");
		System.out.println(OSName);
		System.out.println("\n");
		Thread.sleep(5000);
	}
}
