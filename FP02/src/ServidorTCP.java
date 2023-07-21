
import java.io.*;
import java.net.*;

public class ServidorTCP {

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

	public ServidorTCP() {

		ServerSocket servidor = null;
		ObjectInputStream entrada;
		ObjectOutputStream saida;
		String msg = "";
		Socket conexao;

		try {
			// O ServerSocket vai esperar por um pedido de liga��o no porto 2222:
			servidor = new ServerSocket(2222);
			System.out.println("<Servidor> � escuta na porta: 2222");

			while (true) {
				// O m�todo accept() bloqueia a conex�o at� que o servidor receba um pedido de
				// conex�o:
				conexao = servidor.accept();
				System.out.println("<Servidor> Cliente conectado: " + conexao.getInetAddress().getHostAddress());
				// Obtendo os fluxos de entrada e sa�da:
				saida = new ObjectOutputStream(conexao.getOutputStream());
				entrada = new ObjectInputStream(conexao.getInputStream());
				saida.writeObject("Conex�o estabelecida!");
				saida.flush();
				while (true) {
					System.out.println("<Servidor> Digite a mensagem a ser enviada: ");
					msg = lerString();
					saida.writeObject(msg);
					saida.flush();
					if (msg.equals("fim")) {
						entrada.close();
						saida.close();
						conexao.close();
						System.out.println("Conex�o encerrada pelo servidor!");
						break;
					}
					msg = (String) entrada.readObject();
					System.out.println("<Servidor> Server recebeu: " + msg);
				}
			}
		} catch (IOException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void main(String args[]) {

		ServidorTCP s = new ServidorTCP();
	}
}