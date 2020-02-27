import java.rmi.*;
import java.util.ArrayList;

public class HelloClient {
	public static void main(String args[]) {

		try {
			int port = 16790;         
			String host = "localhost";
			String registryURL = "rmi://" + host + ":" + port + "/hello";
			AccountInterface h = (AccountInterface) Naming.lookup(registryURL);
			System.out.println("Lookup completed " );

			// BankAccount accountOne = new BankAccount();
			// accountOne.createAccount("Bob", 1000f);

			String str = h.createAccount("Rodrigo", 10024f);
			System.out.println(str);
			
		} 
		
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
