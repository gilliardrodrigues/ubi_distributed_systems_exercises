package cinema;

public class Teste {

	public static void main(String[] args) {
		
		var sala1 = new SalaCinema("Super Mario Bros. - O Filme", 100);
		var sala2 = new SalaCinema("John Wick: Capítulo 4", 100);
		var sala3 = new SalaCinema("Gato de Botas 2: O Último Pedido", 100);
		var sala4 = new SalaCinema("Homem-Formiga e a Vespa: Quantumania", 100);
		
		var posto1 = new PostoVenda("Posto 1", sala1);
		var posto2 = new PostoVenda("Posto 2", sala1);
		var posto3 = new PostoVenda("Posto 3", sala1);
		var posto4 = new PostoVenda("Posto 4", sala2);
		var posto5 = new PostoVenda("Posto 5", sala2);
		var posto6 = new PostoVenda("Posto 6", sala2);
		var posto7 = new PostoVenda("Posto 7", sala3);
		var posto8 = new PostoVenda("Posto 8", sala3);
		var posto9 = new PostoVenda("Posto 9", sala3);
		var posto10 = new PostoVenda("Posto 10", sala4);
		var posto11 = new PostoVenda("Posto 11", sala4);
		var posto12 = new PostoVenda("Posto 12", sala4);
		
		posto1.start();
		posto2.start();
		posto3.start();
		posto4.start();
		posto5.start();
		posto6.start();
		posto7.start();
		posto8.start();
		posto9.start();
		posto10.start();
		posto11.start();
		posto12.start();
	}
}
