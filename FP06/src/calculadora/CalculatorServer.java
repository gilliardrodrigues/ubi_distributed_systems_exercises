package calculadora;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class CalculatorServer {

	public CalculatorServer() {
		
		try { 
			// Iniciar a execução do registry no porto desejado
			LocateRegistry.createRegistry(1099);
			System.out.println("RMI registry ready.");
		} catch (Exception e) {
			System.out.println("Exception starting RMI registry:");
		}
		
		try {
			Calculator c = new CalculatorImpl();
			Naming.rebind("CalculatorService", c);
		} catch (Exception ex) {
			System.out.println("Trouble: " + ex.getMessage());
		}
	}
	
	public static void main(String[] args) {
		
		new CalculatorServer();
	}
}
