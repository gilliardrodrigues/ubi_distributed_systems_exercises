package desportos_radicais;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class CampaignService extends UnicastRemoteObject implements ICampaignService {

	private static final long serialVersionUID = 4809292612896052990L;
	private Double totalDonated;
	private List<String> donators;
	
	protected CampaignService() throws RemoteException {
		super();
		this.totalDonated = 0.0;
		this.donators = new ArrayList<String>();
	}
	// Remote methods:
	
	@Override
	public synchronized void donate(IClient client, Double amount, String donator) throws RemoteException {
		
		totalDonated += amount;
		if(!donators.contains(donator)) {
			donators.add(donator);
			if(donators.size() == 100) {
				client.notifyDonator("Message from server: congratulations! You were the 100th donator!!!");
			}
		}
	}

	@Override
	public Double getTotalDonations() throws RemoteException {
		
		return this.totalDonated;
	}

	@Override
	public List<String> getDonators() throws RemoteException {
		
		return this.donators;
	}
}
