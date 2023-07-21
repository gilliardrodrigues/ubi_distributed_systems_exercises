package exercicio1;

import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class HelloClient extends UnicastRemoteObject implements Hello_C_I {
	
	
	private static final long serialVersionUID = 4685186633218629513L;

	public HelloClient() throws RemoteException {
		super();
	}

	//Método remoto
	public void printOnClient(String s) throws RemoteException {
		System.out.println("Message from server: " + s);
	}

	public static void main(String[] args) {
		
		try {
			Hello_S_I h = (Hello_S_I) Naming.lookup("Hello");
			HelloClient c = new HelloClient();
			h.subscribe(InetAddress.getLocalHost().getHostName(), (Hello_C_I) c);
		} catch (Exception r) {
			System.out.println("Exception in client" + r.getMessage());
		}
	}
}
