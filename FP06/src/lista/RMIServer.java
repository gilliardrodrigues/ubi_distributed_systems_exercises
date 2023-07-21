package lista;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class RMIServer {
	
	public static void main(String[] argv) {
		
		//Se está a trabalhar com uma versão do java inferior a 17, não comente a instrução abaixo:
		// System.setSecurityManager(new SecurityManager());
		try { 
			// Iniciar a execução do registry no porto desejado
			LocateRegistry.createRegistry(1099);
			System.out.println("RMI registry ready.");
		} catch (Exception e) {
			System.out.println("Exception starting RMI registry:");
		}
		try {
			// instanciar objeto remoto
			RMIInterface objRemoto = new RMIImpl();
			// registar o objeto remoto no Registry
			Naming.rebind("RMIImpl", objRemoto);
			System.out.println("Remote object ready.");
		} catch (MalformedURLException | RemoteException e) {
			System.out.println(e.getMessage());
		}
	}
}