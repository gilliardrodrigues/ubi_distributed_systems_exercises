package grupoDeThreads;

public class MyThread extends Thread {
	
	public MyThread(String name) {
		super(name);
	}
	
	public MyThread(ThreadGroup tg, String name) {
		super(tg, name);
	}

	public void run() {
		while (true) {
			System.out.println("Sou a " + this.getName());
			if (isInterrupted())
				break;
			Thread.yield();
		}
	}
}
