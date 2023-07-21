package exercicio1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class HelloServer extends UnicastRemoteObject implements Hello_S_I {

	private static final long serialVersionUID = 357918557692622487L;

	private static Hello_C_I client;

	public HelloServer() throws RemoteException {
		super();
	}

	// Método local
	public static String lerString() {
		String s = "";
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in), 1);
			s = in.readLine();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return s;
	}

	// Método remoto
	@Override
	public void printOnServer(String s) throws RemoteException {
		System.out.println("SERVER : " + s);
	}

	// Método remoto
	@Override
	public void subscribe(String name, Hello_C_I c) throws RemoteException {
		System.out.println("Subscribing " + name);
		client = c;
	}

	public static void main(String[] args) {

		String s;

		try {
			//Iniciando o registry:
			LocateRegistry.createRegistry(1099);
			System.out.println("RMI registry ready.");
		} catch (Exception e) {
			System.out.println("Exception starting RMI registry:");
		}

		try {
			HelloServer h = new HelloServer();
			Naming.rebind("Hello", h);
			while (true) {
				System.out.println("Mensagem para o cliente:");
				s = lerString();
				client.printOnClient(s);
			}
		} catch (RemoteException r) {
			System.out.println("Exception in server" + r.getMessage());
		} catch (MalformedURLException u) {
			System.out.println("Exception in server - URL");
		}
	}
}
