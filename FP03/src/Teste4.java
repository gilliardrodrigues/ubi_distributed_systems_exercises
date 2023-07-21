
public class Teste4 {

	public static void main(String[] str) {
		
		Normal normalThread = new Normal();
		Daemon daemonThread = new Daemon();
		
		normalThread.start();
		daemonThread.start();
	}
}
