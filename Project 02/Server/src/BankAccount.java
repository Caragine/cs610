import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;

public class BankAccount extends UnicastRemoteObject implements AccountInterface {

    private ArrayList<Account> accounts = new ArrayList<>();

    public BankAccount() throws RemoteException {
        super();
    }

    public String createAccount(String name, float balance) throws RemoteException {
        // In creating an account, it is the bank that selects an account number. In our project the first new account is 100, the next is 101, next is 102, ...
        // Anyone can open a new account. It does not matter if the person has already accounts.
        // In that case I created an ArrayList of object bankAccounts itself in order to control the account number of each object.
        accounts.add(new Account());
        for (int i=0; i<accounts.size(); i++) {
            if (accounts.size() == 1) {
                accounts.get(0).setAccountNo(100);
                accounts.get(0).setName(name);
                accounts.get(0).setBalance(balance);
            } else {
                accounts.get(i).setAccountNo(100+i);
                accounts.get(i).setName(name);
                accounts.get(i).setBalance(balance);
            }
        }
        return "AccountNo: " + Integer.toString(accounts.get(accounts.size()-1).getAccountNo()) +
                "\nAccountName: " + accounts.get(accounts.size()-1).getName() +
                "\nBalance: " + accounts.get(accounts.size()-1).getBalance();
    }

    public String getBalance(String name, int accountNo) throws RemoteException {
        // If the name with accountNo is in the bank return the person balance as a string otherwise either name has no account or his account no is wrong.
        String string = " ";
        for (int i=0; i<accounts.size(); i++) {
            if (accounts.get(i).getName().equalsIgnoreCase(name) && accounts.get(i).getAccountNo() == accountNo) {
                string = "Account number " + accounts.get(i).getAccountNo() + "'s balance: " + accounts.get(i).getBalance();
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
            if (accounts.get(i).getName().equalsIgnoreCase(name) && accounts.get(i).getAccountNo()== accountNo) {
                if (amt <= accounts.get(i).getBalance()) {
                    accounts.get(i).setBalance(accounts.get(i).getBalance()-amt);
                    string = "Account number " + accounts.get(i).getAccountNo() + "'s new balance: " + accounts.get(i).getBalance();
                } else {
                    string = "Insufficient funds! Balance: " + accounts.get(i).getBalance();
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
            if (accounts.get(i).getName().equalsIgnoreCase(name) && accounts.get(i).getAccountNo()== accountNo) {
                accounts.get(i).setBalance(accounts.get(i).getBalance()+amt);
                string = "Account number " + accounts.get(i).getAccountNo()+ "'s new balance: " + accounts.get(i).getBalance();
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
            if (accounts.get(i).getName().equalsIgnoreCase(name) && accounts.get(i).getAccountNo() == fromAccount) {
                if (amt <= accounts.get(i).getBalance()) {
                    for (int j=0; j<accounts.size(); j++) {
                        if (accounts.get(j).getName().equalsIgnoreCase(name) && accounts.get(j).getAccountNo() == toAccount) {
                            accounts.get(i).setBalance(accounts.get(i).getBalance()-amt);
                            accounts.get(j).setBalance(accounts.get(j).getBalance()+amt);
                            string = "Account number " + accounts.get(i).getAccountNo() + "'s new balance: " + accounts.get(i).getBalance() +
                                    "\nAccount number " + accounts.get(j).getAccountNo() + "'s new balance: " + accounts.get(j).getBalance();
                        } else {
                            string = "One of account number is wrong!";
                        }
                    }
                } else {
                    string = "Insufficient funds! Balance: " + accounts.get(i).getBalance();
                }
            }
        }
        return string;
    }
}