package exemploSlides;

public class Thread2 extends Thread {

	private Exemplo2 ex;
	
	public Thread2(Exemplo2 ex) {
		super();
		this.ex = ex;
	}

	public void run() {
		ex.ba();
	}
}
