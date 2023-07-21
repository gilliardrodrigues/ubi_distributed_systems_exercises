package exercicio3;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class CidadeFactoryImpl extends UnicastRemoteObject implements CidadeFactory {

	private static final long serialVersionUID = -7653832265280157761L;

	public CidadeFactoryImpl() throws RemoteException {
		super();
	}
	
	@Override
	public Cidade getServidorCidade(String nomeCidade) throws RemoteException {
		
		CidadeImpl ServidorCidade = new CidadeImpl(nomeCidade);
		return (Cidade) ServidorCidade;
	}

	public static void main(String arg[]) {
		
		try { 
			LocateRegistry.createRegistry(1099);
			System.out.println("RMI registry ready.");
		} catch (Exception e) {
			System.out.println("Exception starting RMI registry:");
		}
		
		try {
			CidadeFactory factory = new CidadeFactoryImpl();
			Naming.rebind("CidadeFactory", factory);
			System.out.println("CidadeFactory registada!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}