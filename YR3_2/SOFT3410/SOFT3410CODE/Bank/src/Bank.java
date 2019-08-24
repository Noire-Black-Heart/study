import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {
 private int account = 100;

 Lock lock = new ReentrantLock();
 
 public static void main(String[] args) {
//	 Bank account1 = new Bank();
 Bank account1 = new Bank();
 Bank account2 = new Bank();
 
// Thread adding = new Thread(new AddMoney(account));
// Thread subtracting = new Thread(new TakeMoney(account));
// adding.start();
// subtracting.start();
 Thread transfer = new Thread(new TransferMoney(account1, account2));
 Thread transfer2 = new Thread(new TransferMoney(account2, account1));
 
 transfer.start();
 transfer2.start();
 
// try {
//	adding.join();
//} catch (InterruptedException e) {
//	// TODO Auto-generated catch block
//	e.printStackTrace();
//}
 }

 public void addMoney(int amount) {
 if (amount < 1) {
 System.out.print("Trying to add a negative amount to the account!");
 System.out.println(" Transaction rejected.");
 return;
 }
 int newBalance = account + amount;
 System.out.print("Account balance is " + account + ". Adding " + amount + ".");
 account = newBalance;
 //syncAccount();
 System.out.println("New balance is " + account + ".");
 return;
 }

 public void subtractMoney(int amount) {
 if (amount < 1) {
 System.out.print("Trying to subtract a negative amount from the account!");
 System.out.println(" That's generous, but the transaction is rejected.");
 return;
 }

 int newBalance = account - amount;
 System.out.print("Account balance is " + account + ". Subtracting " + amount + ".");
 account = newBalance;
 System.out.println("New balance is " + account + ".");
 return;
 }

 }