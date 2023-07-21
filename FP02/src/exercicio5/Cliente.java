package exercicio5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Cliente {

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

	public Cliente() {

		Socket conexao;
		ObjectInputStream entrada;
		ObjectOutputStream saida;
		String msg = "";
		String ip = "127.0.0.1";
		int porta = 1616;

		try {
			conexao = new Socket(ip, porta);
			// Inicializando os fluxos de entrada e saída:
			saida = new ObjectOutputStream(conexao.getOutputStream());
			entrada = new ObjectInputStream(conexao.getInputStream());
			while (true) {
				// Lendo a mensagem enviada pelo servidor:
				msg = String.valueOf(entrada.readObject());
				System.out.println("<Servidor> " + msg);
				if (msg.equals("Conexão encerrada!")) {
					saida.close();
					entrada.close();
					conexao.close();
					break;
				}
				System.out.print("<Cliente> Mensagem a enviar: ");
				msg = lerString();
				// Enviando mensagem para o servidor:
				saida.writeObject(msg);
				saida.flush();
			}
		} catch (IOException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void main(String args[]) {

		var cliente = new Cliente();
	}
}
