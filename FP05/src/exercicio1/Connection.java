package exercicio1;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;

public class Connection extends Thread {

	private Socket conexao;
	
	public Connection(Socket conexao) {
		super();
		this.conexao = conexao;
		start();
	}
	
	public void run() {

		try {
			ObjectOutputStream saida = new ObjectOutputStream(conexao.getOutputStream());
			saida.writeObject("(" + Thread.currentThread().getName() + ") " + "A data e hora do sistema: " + new Date());
			saida.flush();
			sleep(10000);
			saida.writeObject("(" + Thread.currentThread().getName() + ") " + "A data e hora do sistema: " + new Date());
			saida.flush();
			saida.close();
			conexao.close();
		} catch (IOException | InterruptedException e) {
			System.out.println(e.getMessage());
		}	
	}
}
