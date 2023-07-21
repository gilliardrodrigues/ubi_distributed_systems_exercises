package exemploSlides;

public class Thread1 extends Thread {

	private Exemplo2 ex;
	
	public Thread1(Exemplo2 ex) {
		super();
		this.ex = ex;
	}

	public void run() {
		ex.ab();
	}
}
