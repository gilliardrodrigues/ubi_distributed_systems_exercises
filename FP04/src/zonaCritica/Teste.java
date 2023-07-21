package zonaCritica;

public class Teste {

	public static void main(String[] args) {
		
		VariavelPartilhada vp = new VariavelPartilhada(0);
		Processo1 p1 =  new Processo1(vp);
		Processo2 p2 = new Processo2(vp);
		p1.start();
		p2.start();
		System.out.println(vp.getValor());
	}
}
