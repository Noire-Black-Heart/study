 public class Bank {
 private int account = 100;
 private int account1 = 0;
 private int account2 = 0;
 public static void main(String[] args) {
 Bank account = new Bank();
 Thread adding = new Thread(new AddMoney(account));
 Thread subtracting = new Thread(new TakeMoney(account));
 adding.start();
 subtracting.start();
 
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
 System.out.print("Account balance is " + account + ". Adding " + amount + ".");
 account = account + amount;
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

 System.out.print("Account balance is " + account + ". Subtracting " + amount + ".");
 account = account - amount;
 System.out.println("New balance is " + account + ".");
 return;
 }

 }