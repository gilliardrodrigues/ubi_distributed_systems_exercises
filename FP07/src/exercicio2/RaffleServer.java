package exercicio2;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class RaffleServer extends UnicastRemoteObject implements IRaffleServer {
	
	private static final long serialVersionUID = 8876652438416376212L;
	
	private Map<IClient, Long> clientsAndNumbers;
	private final AtomicLong sequentialNumber = new AtomicLong();
	private int drawnNumber;

	public RaffleServer() throws RemoteException {
		super();
		this.clientsAndNumbers = new HashMap<>();
	}
	// Métodos remotos:
	
	@Override
	public synchronized void registerClient(IClient client) throws RemoteException {
		var nextNumber = sequentialNumber.incrementAndGet();
		this.clientsAndNumbers.put(client, nextNumber);
		client.notifyClient("<Server> Your number is " + nextNumber);
		System.out.println("New client connected with number " + nextNumber);
		if (isDrawTime()) {
			drawNumber();
			notifyClients();
		}
	}
	// Métodos locais:
	
	private boolean isDrawTime() {

		return (this.clientsAndNumbers.size() != 0 && this.clientsAndNumbers.size() % 10 == 0);
	}

	private void drawNumber() {
		var random = new Random();
		var lastNumber = this.sequentialNumber.intValue();
		var interval = 10;
		this.drawnNumber = random.nextInt(interval) + lastNumber - interval + 1;
		System.out.println("The winner is the client whose number is " + this.drawnNumber);
	}

	private synchronized void notifyClients() {

		this.clientsAndNumbers.entrySet().stream()
			.sorted(Map.Entry.comparingByValue())
			.skip(Math.max(0, this.clientsAndNumbers.size() - 10))
			.forEach(entry -> {
				try {
					entry.getKey().notifyClient("<Server> The winner is the client whose number is " + this.drawnNumber);
				} catch (RemoteException e) {
					System.err.println("Failed to notify client: " + e.getMessage());
				}
				;
			});
	}
	// Main:
	
	public static void main(String[] args) {

		try {
			// Iniciando o registry:
			LocateRegistry.createRegistry(1099);
			System.out.println("RMI registry ready.");
		} catch (Exception e) {
			System.out.println("Exception starting RMI registry:");
		}

		try {
			// Registrando o objeto remoto:
			var raffleServer = new RaffleServer();
			Naming.rebind("RaffleServer", raffleServer);
		} catch (RemoteException r) {
			System.out.println("Exception in server" + r.getMessage());
		} catch (MalformedURLException u) {
			System.out.println("Exception in server - URL");
		}
	}

}
