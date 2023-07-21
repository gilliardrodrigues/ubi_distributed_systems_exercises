package lista;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class RMIImpl extends UnicastRemoteObject implements RMIInterface {
	
	private static final long serialVersionUID = -7945985488754020391L;
	
	private ArrayList<String> lista;
	private int count;
	
	public RMIImpl() throws RemoteException {
		super();
		this.lista = new ArrayList<String>();
		this.count = 0;
	}

	public void adiciona(String s) throws java.rmi.RemoteException {
		this.lista.add(s);
	}

	public ArrayList<String> consulta() throws java.rmi.RemoteException {
		
		this.count++;
		return this.lista;
	}
	
	public int getCount() throws RemoteException {

		return this.count;
	}
}