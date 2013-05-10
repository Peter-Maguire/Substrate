package game.console;

public class ThreadConsole implements Runnable{

	public ConsoleWindow console;
	@Override
	public void run() {
		console = new ConsoleWindow();
		
	}

}
