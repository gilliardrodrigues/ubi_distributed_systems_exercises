package exercicio1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	
	private ServerSocket servidor;
	private Socket conexao;
	private Connection connectionThread;
	
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
		
		try {
			this.servidor = new ServerSocket(1616);
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			while(true) {
				this.conexao = servidor.accept();
				this.connectionThread = new Connection(conexao);
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void main(String args[]) {
		
		var dataHora = new Servidor();
	}
}
