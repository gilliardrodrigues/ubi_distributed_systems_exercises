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
				System.out.println("Não é um inteiro válido!!!");
			}
		}
	}
	
	public static void main(String[] argv) {

		int opcaoEscolhida;
		String novaAdicao;
		// Se está a trabalhar com uma versão do java inferior a 17, não comente a
		// instrução abaixo:
		// System.setSecurityManager(new SecurityManager());
		try {
			// bind server object to object in client
			RMIInterface myServerObject = (RMIInterface) Naming.lookup("RMIImpl");
			// invoke method on server object
			while (true) {
				System.out.println("-----------------MENU-----------------");
				System.out.println("Qual operação deseja efetuar?");
				System.out.println("1 - Adicionar elemento à lista");
				System.out.println("2 - Consultar lista");
				System.out.println("3 - Ver quantas vezes o método 'consulta' foi invocado");
				System.out.println("Outro número - Encerrar conexão");
				System.out.println("Insira abaixo a opção escolhida:");
				opcaoEscolhida = lerInteiro();
				if(opcaoEscolhida == 1) {
					System.out.println("Insira abaixo o que deseja adicionar:");
					novaAdicao = lerString();
					myServerObject.adiciona(novaAdicao);
					System.out.println("Adição efetuada!");
				} else if(opcaoEscolhida == 2) {
					System.out.println(myServerObject.consulta());
				} else if(opcaoEscolhida == 3) {
					System.out.println("Número de invocações do método 'consulta': " + myServerObject.getCount());
				} else {
					System.out.println("Conexão encerrada!");
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
