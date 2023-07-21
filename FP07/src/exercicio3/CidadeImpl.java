package exercicio3;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CidadeImpl extends UnicastRemoteObject implements Cidade {

	private static final long serialVersionUID = 1394089578289673079L;
	
	private String nomeCidade;
	int populacao = 20000;

	public CidadeImpl() throws RemoteException {
		super();
	}

	public CidadeImpl(String nomeCidade) throws RemoteException {
		super();
		this.setNomeCidade(nomeCidade);
	}
	
	@Override
	public int getPopulacao() throws RemoteException {
		return populacao;
	}
	
	@Override
	public void setPopulacao(int populacao) throws RemoteException {
		this.populacao = populacao;
	}

	public String getNomeCidade() {
		return nomeCidade;
	}

	public void setNomeCidade(String nomeCidade) {
		this.nomeCidade = nomeCidade;
	}
}
