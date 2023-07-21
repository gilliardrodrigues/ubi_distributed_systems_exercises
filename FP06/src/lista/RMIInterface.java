package lista;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface RMIInterface extends Remote {

	public void adiciona(String s) throws RemoteException;
	
	public ArrayList<String> consulta() throws RemoteException;
	
	public int getCount() throws RemoteException;
}
