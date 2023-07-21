package alunosMultithread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
		Servico servico;
		int porta = 1616;
		int MAX_THREADS = 5;
		ThreadServidor[] pool = new ThreadServidor[MAX_THREADS];
		try {
			// O ServerSocket vai esperar por um pedido de ligação na porta definida:
			servidor = new ServerSocket(porta);
			System.out.println("<Servidor> À escuta na porta: " + porta);
			servico = new Servico();
			
			for(int i = 0; i < MAX_THREADS; i++) {
				pool[i] = new ThreadServidor(servidor, servico);
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void main(String args[]) {

		var servidor = new Servidor();
	}
}
