import java.rmi.*;
import java.util.ArrayList;

public class HelloClient {
	public static void main(String args[]) {
		
		ArrayList<BankAccount> accounts = new ArrayList<BankAccount>();
		
		try {
			int port = 16790;         
			String host = "localhost";
			String registryURL = "rmi://" + host + ":" + port + "/hello";
			AccountInterface h = (AccountInterface)Naming.lookup(registryURL);
			System.out.println("Lookup completed " );
			
			
			
			
			
		} 
		
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
