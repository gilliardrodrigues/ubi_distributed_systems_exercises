package exercicio3;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CidadeFactory extends Remote {
	
	public Cidade getServidorCidade(String nomeCidade) throws RemoteException;
}