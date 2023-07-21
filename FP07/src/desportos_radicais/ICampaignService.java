package desportos_radicais;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ICampaignService extends Remote {

	public void donate(IClient client, Double amount, String donator) throws RemoteException;
	public Double getTotalDonations() throws RemoteException;
	public List<String> getDonators() throws RemoteException;
}
