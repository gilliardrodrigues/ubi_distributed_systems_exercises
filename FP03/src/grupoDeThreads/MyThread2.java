package grupoDeThreads;

public class MyThread2 extends Thread {

	public MyThread2(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	
	public void run() {
		System.out.println("Sou a " + this.getName());
	}

}
