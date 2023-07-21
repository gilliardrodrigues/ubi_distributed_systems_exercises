package desportos_radicais;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IClient extends Remote {
	
	public void notifyDonator(String message) throws RemoteException;
}
