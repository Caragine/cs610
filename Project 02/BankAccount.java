import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;

public class BankAccount extends UnicastRemoteObject implements AccountInterface {
	
	ArrayList<BankAccount> accounts = new ArrayList<BankAccount>();
   private String name;
   private float balance;
   public int accountNo;
	  
	public BankAccount() throws RemoteException {
		super();		
	}
		
	public String createAccount(String name, float balance) throws RemoteException {
		// In creating an account, it is the bank that selects an account number. In our project the first new account is 100, the next is 101, next is 102, ...
		// Anyone can open a new account. It does not matter if the person has already accounts.
		// In that case I created an ArrayList of object bankAccounts itself in order to control the account number of each object.
		accounts.add(new BankAccount());
		for (int i=0; i<accounts.size(); i++) {
			if (accounts.size() == 1) {
				accounts.get(0).accountNo = 100;
				accounts.get(0).name = name;
				accounts.get(0).balance = balance;
			} else {
				accounts.get(i).accountNo = 100+i;
				accounts.get(i).name = name;
				accounts.get(i).balance = balance;
			}
		}
		return "AccountNo: " + Integer.toString(accounts.get(-1).accountNo) +
				"\nAccountName: " + accounts.get(-1).name + 
				"\nBalance: " + accounts.get(-1).balance;
	}
  
	public String getBalance(String name, int accountNo) throws RemoteException {
		// If the name with accountNo is in the bank return the person balance as a string otherwise either name has no account or his account no is wrong.  
		String string = " ";
		for (int i=0; i<accounts.size(); i++) {
			if (accounts.get(i).name.equalsIgnoreCase(name) && accounts.get(i).accountNo == accountNo) {
				string = "Account number " + accounts.get(i).accountNo + "'s balance: " + accounts.get(i).balance;
			} else {
				string = "Either name has no account or account number is wrong";
			}
		}
		return string;
	}
  
	public String withdraw(String name, float amt, int accountNo)throws RemoteException {
		// The account number with the name must be in the bank. 
		// There is not enough fund return to the user a message.
		// There is enough funds. Withdraw and return the new balance.  
		String string = " ";
		for (int i=0; i<accounts.size(); i++) {
			if (accounts.get(i).name.equalsIgnoreCase(name) && accounts.get(i).accountNo == accountNo) {
				if (amt <= accounts.get(i).balance) {
					accounts.get(i).balance -= amt;
					string = "Account number " + accounts.get(i).accountNo + "'s new balance: " + accounts.get(i).balance;
				} else {
					string = "Insufficient funds! Balance: " + accounts.get(i).balance;
				}
			}
		}
		return string;
	}

	public String deposit(String name, float amt, int accountNo) throws RemoteException {
		// The account number with the name must be in the bank.
		// If account number with the name is in the bank, deposit and return the new balance.
		String string = " ";
		for (int i=0; i<accounts.size(); i++) {
			if (accounts.get(i).name.equalsIgnoreCase(name) && accounts.get(i).accountNo == accountNo) {
				accounts.get(i).balance += amt;
				string = "Account number " + accounts.get(i).accountNo + "'s new balance: " + accounts.get(i).balance;
			} else {
				string = "Wrong account number!";
			}
		}
		return string;
	}
	
	public String transfer(String name, int fromAccount, int toAccount, float amt) throws RemoteException {
		// Both account numbers with the same name must be in the bank.
		// There is not enough fund in the account that is going to transfer return to the user a message.
		// There is enough funds. Withdraw and return the new balance.
		String string = " ";
		for (int i=0; i<accounts.size(); i++) {
			if (accounts.get(i).name.equalsIgnoreCase(name) && accounts.get(i).accountNo == fromAccount) {	
				if (amt <= accounts.get(i).balance) {
					for (int j=0; j<accounts.size(); j++) {
						if (accounts.get(j).name.equalsIgnoreCase(name) && accounts.get(j).accountNo == toAccount) {	
							accounts.get(i).balance -= amt;
							accounts.get(j).balance += amt;
							string = "Account number " + accounts.get(i).accountNo + "'s new balance: " + accounts.get(i).balance + 
										"\nAccount number " + accounts.get(j).accountNo + "'s new balance: " + accounts.get(j).balance;
						} else {
							string = "One of account number is wrong!";
						}
					}
				} else {
					string = "Insufficient funds! Balance: " + accounts.get(i).balance;
				}
			}
		}
		return string;
	}
}
