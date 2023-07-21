package exercicio2;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Client extends UnicastRemoteObject implements IClient {

	private static final long serialVersionUID = -3658337081775596310L;
	
	protected Client() throws RemoteException {
		super();
	}	
	// Métodos remotos:
	
	@Override
	public void notifyClient(String message) throws RemoteException {

		System.out.println(message);

	}
	// Main:
	
	public static void main(String[] args) {

		try {
			IRaffleServer raffleServer = (IRaffleServer) Naming.lookup("RaffleServer");
			var client = new Client();
			raffleServer.registerClient((IClient) client);
		} catch (Exception r) {
			System.out.println("Exception in client" + r.getMessage());
		}
	}
}
