package exercicio1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class Cliente {

	private Socket conexao;
	private ObjectInputStream entrada;

	public Cliente() {
		
		try {
			conexao = new Socket("127.0.0.1", 1616);
			entrada = new ObjectInputStream(conexao.getInputStream());
			System.out.println(entrada.readObject());
			System.out.println(entrada.readObject());
			entrada.close();
			conexao.close();
		} catch (IOException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void main(String[] str) {
		
		var cliente = new Cliente();
	}
}
