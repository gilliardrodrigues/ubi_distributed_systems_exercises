package lista;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.Naming;

public class RMIClient {

	public static String lerString() {

		String s = "";
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			s = in.readLine();
		} catch (IOException e) {
			System.out.println("Erro ao ler fluxo de entrada.");
		}
		return s;
	}

	public static int lerInteiro() {

		while (true) {
			try {
				return Integer.parseInt(lerString().trim());
			} catch (NumberFormatException e) {
				System.out.println("N�o � um inteiro v�lido!!!");
			}
		}
	}
	
	public static void main(String[] argv) {

		int opcaoEscolhida;
		String novaAdicao;
		// Se est� a trabalhar com uma vers�o do java inferior a 17, n�o comente a
		// instru��o abaixo:
		// System.setSecurityManager(new SecurityManager());
		try {
			// bind server object to object in client
			RMIInterface myServerObject = (RMIInterface) Naming.lookup("RMIImpl");
			// invoke method on server object
			while (true) {
				System.out.println("-----------------MENU-----------------");
				System.out.println("Qual opera��o deseja efetuar?");
				System.out.println("1 - Adicionar elemento � lista");
				System.out.println("2 - Consultar lista");
				System.out.println("3 - Ver quantas vezes o m�todo 'consulta' foi invocado");
				System.out.println("Outro n�mero - Encerrar conex�o");
				System.out.println("Insira abaixo a op��o escolhida:");
				opcaoEscolhida = lerInteiro();
				if(opcaoEscolhida == 1) {
					System.out.println("Insira abaixo o que deseja adicionar:");
					novaAdicao = lerString();
					myServerObject.adiciona(novaAdicao);
					System.out.println("Adi��o efetuada!");
				} else if(opcaoEscolhida == 2) {
					System.out.println(myServerObject.consulta());
				} else if(opcaoEscolhida == 3) {
					System.out.println("N�mero de invoca��es do m�todo 'consulta': " + myServerObject.getCount());
				} else {
					System.out.println("Conex�o encerrada!");
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("Exception occured: " + e);
			System.exit(0);
		}
		System.out.println("RMI connection successful");
	}
}
