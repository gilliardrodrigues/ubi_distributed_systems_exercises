
public class MyThread2 implements Runnable {

	public MyThread2() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void run() {

		System.out.println("Hi there, from " + Thread.currentThread().getName());
	}
	
	
}
