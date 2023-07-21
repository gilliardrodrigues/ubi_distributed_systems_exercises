package cinema;

public class PostoVenda extends Thread {

	private SalaCinema salaCinema;

	public PostoVenda(String name, SalaCinema salaCinema) {
		super(name);
		this.salaCinema = salaCinema;
	}
	
	public void run() {
		
		int pausa;
		
		while(true) {
			try {
				pausa = (int) (Math.random() * 2000);
				sleep(pausa);
				System.out.println(this.getName() + " vendeu o bilhete " + salaCinema.venderBilhete() + " para o filme " + salaCinema.getNomeFilme() + "!");
				if(salaCinema.getNumBilhetesDisponiveis().equals(0)) {
					System.out.println(this.getName() + " encerrou as vendas.");
					break;
				}
			} catch(InterruptedException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}
}
