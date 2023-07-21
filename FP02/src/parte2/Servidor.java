package parte2;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.lang.Math;

public class Servidor {

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
	
	public Servidor() {
		
		ServerSocket servidor = null;
		ObjectInputStream entrada;
		ObjectOutputStream saida;
		String msg = "";
		Socket conexao;
		int porta = 2222;
		String tipoDeCliente = "";
		int inteiroRecebido;
		double doubleRecebido;
		double resultado;
		
		try {
			// O ServerSocket vai esperar por um pedido de liga��o na porta definida:
			servidor = new ServerSocket(porta);
			System.out.println("<Servidor> � escuta na porta: " + porta);

			while (true) {
				// O m�todo accept() bloqueia a conex�o at� que o servidor receba um pedido de
				// conex�o:
				conexao = servidor.accept();
				System.out.println("<Servidor> Cliente conectado: " + conexao.getInetAddress().getHostAddress());
				// Obtendo os fluxos de entrada e sa�da:
				saida = new ObjectOutputStream(conexao.getOutputStream());
				entrada = new ObjectInputStream(conexao.getInputStream());
				saida.writeObject("Conex�o estabelecida na porta " + porta + "!\nInsira o seu tipo de cliente ('A' ou 'B'): ");
				saida.flush();
				tipoDeCliente = (String) entrada.readObject();
				System.out.println("<Servidor> O cliente conectado � do tipo " + tipoDeCliente + ".");
				
				if (tipoDeCliente.equals("A")) {
					saida.writeObject("Cliente do tipo " + tipoDeCliente + " confirmado.\nInsira um inteiro para que o quadrado seja calculado.\nObs.: para encerrar a conex�o insira -1");
					saida.flush();
					while (true) {
						inteiroRecebido = (int) entrada.readObject();
						System.out.println("<Cliente A> " + inteiroRecebido);
						if(inteiroRecebido == -1) {
							saida.writeObject("Conex�o encerrada!");
							entrada.close();
							saida.close();
							conexao.close();
							System.out.println("Conex�o encerrada! Aguardando um novo cliente.");
							break;
						}
						resultado = Math.pow(inteiroRecebido, 2);
						saida.writeObject("O resultado do quadrado de " + inteiroRecebido + " � " + (int) resultado);
						saida.flush();
						System.out.println("<Servidor> Resultado enviado!");
					}
				}
				else if (tipoDeCliente.equals("B")) {
					saida.writeObject("Cliente do tipo " + tipoDeCliente + " confirmado.\nInsira um double para que o quadrado seja calculado.\nObs.: para encerrar a conex�o insira -1.0");
					saida.flush();
					while (true) {
						doubleRecebido = (double) entrada.readObject();
						System.out.println("<Cliente B> " + doubleRecebido);
						if(doubleRecebido == -1.0) {
							saida.writeObject("Conex�o encerrada!");
							entrada.close();
							saida.close();
							conexao.close();
							System.out.println("Conex�o encerrada! Aguardando um novo cliente.");
							break;
						}
						resultado = Math.sqrt(doubleRecebido);
						saida.writeObject("O resultado da ra�z quadrada de " + doubleRecebido + " � " + resultado);
						saida.flush();
						System.out.println("<Servidor> Resultado da ra�z quadrada enviado!");
					}
				}
			}
		} catch (IOException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
	public static void main(String args[]) {

		var servidor = new Servidor();
	}
}
