public class TakeMoney implements Runnable {
 private Bank account;
 public TakeMoney(Bank account) {
 this.account = account;
 }
 public void run() {
 for (int i = 0; i < 60; ++i) {
 try {
 Thread.sleep(1000);
 } catch (Exception e) {
 System.err.println("Already interrupted.");
 }
 account.subtractMoney(1000);
 }
 }
 }