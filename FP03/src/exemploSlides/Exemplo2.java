package exemploSlides;

public class Exemplo2 {

	private int a = 1, b = 2;
	
	public synchronized void ab() {
		a = b;
	}
	public synchronized void ba() {
		b = a;
	}
	public int getA() {
		return a;
	}
	public void setA(int a) {
		this.a = a;
	}
	public int getB() {
		return b;
	}
	public void setB(int b) {
		this.b = b;
	}
}
