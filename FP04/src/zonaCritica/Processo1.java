package zonaCritica;

public class Processo1 extends Thread {
	
	public Processo1(VariavelPartilhada variavelPart) {
		super();
		this.variavelPart = variavelPart;
	}

	private int x = 1000000;
	private int y = -1000000;
	private VariavelPartilhada variavelPart;
	
	public void run() {
		
		while(true) {
			synchronized (variavelPart) {
				this.x = x - variavelPart.getValor();
				this.y = y + variavelPart.getValor();
			}
			if(x + y != 0) {
				System.out.println("Seção crítica violada!");
				break;
			}
		}
	}
}
