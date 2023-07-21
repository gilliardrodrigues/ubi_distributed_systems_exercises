package exercicio2;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IClient extends Remote {
	
	public void notifyClient(String message) throws RemoteException;
}
