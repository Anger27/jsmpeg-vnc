package tool;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class GetmyIP {
	
	public String myIP = null;
	
	public GetmyIP() {
		myIP = get();
	}
	
	protected String get(){
		InetAddress local = null;
		try {
			local = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String ip = local.getHostAddress();

		if(ip.equals(null))
			ip = "Not found IP";
		
		return ip;
	}

}
