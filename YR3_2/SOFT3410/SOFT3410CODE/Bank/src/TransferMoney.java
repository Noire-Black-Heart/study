public class TransferMoney implements Runnable {
private Bank from;
private Bank to;
public TransferMoney(Bank from, Bank to) {
this.from = from;
this.to = to;
}
public void run() {
for (int i = 0; i < 60; ++i) {
try {
Thread.sleep(500);
} catch (Exception e) {
System.err.println("Already interrupted.");
}
// lock the first account
from.lock.lock();
// lock the second account
to.lock.lock();
// subtract money from one
from.subtractMoney(100);
// add money to the other
to.addMoney(100);
// release locks
from.lock.unlock();
to.lock.unlock();
}
}
}
