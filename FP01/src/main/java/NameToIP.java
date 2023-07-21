import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class NameToIP {
	
public static void main(String args[]) throws IOException {
		
		String s = "";
		char c;
		System.out.println("Name?");
		while((c=(char) System.in.read()) != 10) {
			s += c;
		}
		s = s.trim();
		InetAddress host;
		try {
			host = InetAddress.getByName(s);
			System.out.println(host.getHostAddress());
		}
		catch (UnknownHostException e) {
			System.out.println("Name malformed");
		}
	}
}
