package exercicio3;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Cidade extends Remote {
	
	public int getPopulacao() throws RemoteException;
	public void setPopulacao(int populacao) throws RemoteException;
}
