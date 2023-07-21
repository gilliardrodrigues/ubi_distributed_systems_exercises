package exercicio2;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRaffleServer extends Remote {

	public void registerClient(IClient client) throws RemoteException;
}
