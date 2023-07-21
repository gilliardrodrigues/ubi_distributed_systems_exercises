import java.net.*;
import java.io.*;

public class UDPServer {

	public static String umaString() {
		String s = "";
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			s = in.readLine();
		} catch (IOException e) {
			System.out.println("Erro ao ler fluxo de entrada.");
		}
		return s;
	}

	public static void main(String args[]) {

		DatagramSocket aSocket = null;
		String s;

		try {
			aSocket = new DatagramSocket(2222);
			System.out.println("<Server> Socket Datagram à escuta no porto 2222");
			while (true) {
				byte[] buffer = new byte[100];
				DatagramPacket request = new DatagramPacket(buffer, buffer.length);
				aSocket.receive(request);
				s = new String(request.getData());
				System.out.println("<Server> Server Recebeu: " + s);
				System.out.println("<Server> Digite a mensagem a ser enviada: ");
				s = umaString();
				request.setData(s.getBytes());
				DatagramPacket reply = new DatagramPacket(request.getData(), request.getLength(), request.getAddress(),
						request.getPort());
				aSocket.send(reply);
			}
		} catch (SocketException e) {
			System.out.println("Socket: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("IO: " + e.getMessage());
		} finally {
			if (aSocket != null)
				aSocket.close();
		}
	}
}