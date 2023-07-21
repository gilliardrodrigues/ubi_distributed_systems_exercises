package grupoDeThreads;

public class Teste2 {

	public static void main(String args[]) {
		
		MyThread2 threadA, threadB, threadC;

		threadA = new MyThread2("Thread A");
		threadB = new MyThread2("Thread B");
		threadC = new MyThread2("Thread C");
		
		threadA.setPriority(Thread.MIN_PRIORITY);
		threadB.setPriority(Thread.NORM_PRIORITY);
		threadC.setPriority(Thread.MAX_PRIORITY);
		
		threadA.start();
		threadB.start();
		threadC.start();
	}
 }
