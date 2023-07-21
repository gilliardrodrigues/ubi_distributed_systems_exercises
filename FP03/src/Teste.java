
public class Teste {

	public static void main(String[] str) {

		MyThread Ta, Tb;
		Ta = new MyThread();
		Tb = new MyThread();
		Ta.start();
		Tb.start();
	}
}
