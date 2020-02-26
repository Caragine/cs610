import java.rmi.*;
import java.rmi.registry.LocateRegistry;

public class HelloServer {
	public static void main(String args[]){
		
		try {     
			int port = 16790; 
			String host = "localhost";
			BankAccount exportedObj = new BankAccount();
			LocateRegistry.createRegistry(port);
			String registryURL = "rmi://" + host + ":" + port + "/hello";
			Naming.rebind(registryURL, exportedObj);
			System.out.println("Hello Server ready.");
				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

