
import java.io.*;
import java.net.*;

public class ClienteTCP {

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

	public ClienteTCP() {
		
		Socket conexao;
		ObjectInputStream entrada;
		ObjectOutputStream saida;
		String msg = "";
		
		try {
			conexao = new Socket("127.0.0.1", 2222);
			System.out.println("<Cliente> Cliente na porta 2222");
			// Inicializando os fluxos de entrada e saída:
			saida = new ObjectOutputStream(conexao.getOutputStream());
			entrada = new ObjectInputStream(conexao.getInputStream());
			// Lendo a mensagem enviada pelo servidor:
			msg = (String) entrada.readObject();
			System.out.println("<Client> Recebeu: " + msg);
			while(true) {
				// Lendo a mensagem enviada pelo servidor:
				msg = (String) entrada.readObject();
				System.out.println("<Client> Recebeu: " + msg);
				if(msg.equals("fim")) {
					saida.close();
					entrada.close();
					conexao.close();
					System.out.println("Conexão encerrada pelo servidor!");
					break;
				}
				System.out.print("<Client> Mensagem a enviar = ");
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
		ClienteTCP c = new ClienteTCP();
	}
}
