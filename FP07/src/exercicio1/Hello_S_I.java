package exercicio1;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Hello_S_I extends Remote {
	
	public void printOnServer(String s) throws RemoteException;

	public void subscribe(String s, Hello_C_I cliente) throws RemoteException;
}
