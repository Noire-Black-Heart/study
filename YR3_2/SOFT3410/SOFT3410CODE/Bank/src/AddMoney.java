public class AddMoney implements Runnable {
 private Bank account;
 public AddMoney(Bank account) {
 this.account = account;
 }
 public void run() {
 for (int i = 0; i < 60; ++i) {
 try {
 Thread.sleep(1000);
 } catch (Exception e) {
 System.err.println("Already interrupted.");
 }
 account.addMoney(1000);
 }
 }
 }
