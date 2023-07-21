package desportos_radicais;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Client extends UnicastRemoteObject implements IClient {

	private static final long serialVersionUID = -2464954728637310650L;

	public Client() throws RemoteException {
		super();
	}
	// Remote methods:
	
	@Override
	public void notifyDonator(String message) throws RemoteException {
		
		System.out.println(message);
	}
	// Local methods:
	
	public static String readString() {

		String s = "";
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			s = in.readLine();
		} catch (IOException e) {
			System.out.println("Error when reading input stream.");
		}
		return s;
	}

	public static int readInteger() {

		while (true) {
			try {
				return Integer.parseInt(readString().trim());
			} catch (NumberFormatException e) {
				System.out.println("It's not a valid integer!!!");
			}
		}
	}

	public static double readDouble() {

		while (true) {
			try {
				return Double.valueOf(readString().trim());
			} catch (NumberFormatException e) {
				System.out.println("It's not a valid double!!!");
			}
		}
	}

	public static void main(String[] args) {

		String menuOptions = "\n<Server> Enter a number according to the operation you want to perform:\n"
				+ "1 - Donate\n" + "2 - Consult total\n" + "3 - Consult donators\n" + "Another option - Quit\n";
		int chosenOption;
		
		try {
			var campaignService = (ICampaignService) Naming.lookup("CampaignService");
			
			while(true) {
				System.out.println(menuOptions);
				chosenOption = readInteger();
				if(chosenOption == 1) {
					System.out.println("<Server> OK. Enter your name: ");
					var donator = readString();
					System.out.println("<Server> Now enter the amount you want to donate (example: 22.50): ");
					var donate = readDouble();
					var client = new Client();
					campaignService.donate((IClient) client, donate, donator);
					System.out.println("<Server> Done! you donated €" + donate);
				}
				else if(chosenOption == 2) {
					var totalDonated = campaignService.getTotalDonations();
					System.out.println("<Server> Total donated:: " + totalDonated);
				}
				else if(chosenOption == 3) {
					var donators = campaignService.getDonators().toString();
					System.out.println("<Server> Donators list:\n" + donators);
				}
				else {
					System.out.println("Connection terminated!");
					break;
				}
			}
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			System.out.println("Received exception: " + e.getMessage());
			System.exit(0);
		}
	}
}
