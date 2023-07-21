package zonaCritica;

public class Processo2 extends Thread {

	private VariavelPartilhada variavelPart;
	
	public Processo2(VariavelPartilhada variavelPart) {
		super();
		setDaemon(true);
		this.variavelPart = variavelPart;
	}
	
	public void run() {
		
		while(true) {
			synchronized (variavelPart) {
				this.variavelPart.setValor(this.variavelPart.getValor() + 1);
			}
			System.out.println("variavelPart = " + this.variavelPart.getValor());
		}
	}
}
