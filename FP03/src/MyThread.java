
public class MyThread extends Thread {

	public MyThread() {
		super();
	}
	public void run() {
		System.out.println("Hello there, from " + getName());
		System.out.println("Testing on " + getName());
	}
}
