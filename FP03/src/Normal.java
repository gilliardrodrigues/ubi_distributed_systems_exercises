
public class Normal extends Thread {

	public Normal() {
		super();
	}

	public void run() {
		for (int i = 0; i < 5; i++) {
			try {
				sleep(500);
			} catch (InterruptedException e) {
				e.getMessage();
			}
			System.out.println(" I' m the normal Thread");
		}
		System.out.println(" The normal Thread is exiting");
	}
}


