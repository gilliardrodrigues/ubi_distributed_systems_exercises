package desportos_radicais;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Server {

	public Server() {

		try {
			LocateRegistry.createRegistry(1099);
			System.out.println("RMI registry OK!");
		} catch (RemoteException e) {
			System.out.println("Error: " + e.getMessage());
		}

		try {
			ICampaignService remoteObj = new CampaignService();
			Naming.rebind("CampaignService", remoteObj);
			System.out.println("Remote object OK!");
		} catch (MalformedURLException | RemoteException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		
		new Server();
	}
}
