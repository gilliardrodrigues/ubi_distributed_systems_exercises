package alunosRMI;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Servidor {

	public Servidor() {

		// Iniciando a execução do registry no porto desejado:
		try {
			LocateRegistry.createRegistry(1099);
			System.out.println("RMI registry OK!");
		} catch (RemoteException e) {
			System.out.println("Erro: " + e.getMessage());
		}

		try {
			// Instanciando objeto remoto:
			AlunoService objRemoto = new AlunoServiceImpl();
			// Registrando o objeto remoto no Registry:
			Naming.rebind("AlunoService", objRemoto);
			System.out.println("Objeto remoto OK!");
		} catch (MalformedURLException | RemoteException e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

	public static void main(String[] argv) {

		new Servidor();
	}
}
