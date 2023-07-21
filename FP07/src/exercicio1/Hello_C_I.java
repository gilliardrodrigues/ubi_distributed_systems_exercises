package exercicio1;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Hello_C_I extends Remote {
	
	public void printOnClient(String s) throws RemoteException;
}
