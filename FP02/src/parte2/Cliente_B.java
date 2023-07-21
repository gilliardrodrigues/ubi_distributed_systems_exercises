package parte2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Cliente_B {

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

	public static double lerDouble() {
		
		while (true) {
			try {
				return Double.valueOf(lerString().trim());
			} catch (NumberFormatException e) {
				System.out.println("Não é um double válido!!!");
			}
		}
	}

	public Cliente_B() {

		Socket conexao;
		ObjectInputStream entrada;
		ObjectOutputStream saida;
		String msg = "";
		String ip = "127.0.0.1";
		int porta = 2222;
		String tipoDeCliente = "";
		double valorDouble;

		try {
			conexao = new Socket(ip, porta);
			// Inicializando os fluxos de entrada e saída:
			saida = new ObjectOutputStream(conexao.getOutputStream());
			entrada = new ObjectInputStream(conexao.getInputStream());
			// Lendo a mensagem enviada pelo servidor:
			msg = (String) entrada.readObject();
			System.out.println("<Servidor> " + msg);
			System.out.print("<Cliente> Mensagem a enviar: ");
			tipoDeCliente = lerString();
			// Enviando mensagem para o servidor:
			saida.writeObject(tipoDeCliente);
			saida.flush();
			while (true) {
				// Lendo a mensagem enviada pelo servidor:
				msg = (String) entrada.readObject();
				System.out.println("<Servidor> " + msg);
				if (msg.equals("Conexão encerrada!")) {
					saida.close();
					entrada.close();
					conexao.close();
					break;
				}
				System.out.print("<Cliente> Valor a enviar: ");
				valorDouble = lerDouble();
				// Enviando mensagem para o servidor:
				saida.writeObject(valorDouble);
				saida.flush();
			}
		} catch (IOException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void main(String args[]) {

		var clienteB = new Cliente_B();
	}
}
