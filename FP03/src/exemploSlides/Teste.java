package exemploSlides;

public class Teste {

	public static void main(String args[]) {
		
		var ex2 = new Exemplo2();
		var threadA = new Thread1(ex2);
		var threadB = new Thread2(ex2);
		
		threadB.start();
		threadA.start();
		
		
		System.out.println("A: " + ex2.getA() + "\nB: " + ex2.getB());
	}
}
