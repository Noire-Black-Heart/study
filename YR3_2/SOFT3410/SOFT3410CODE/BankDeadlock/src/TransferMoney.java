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
            System.out.println("Acquiring first lock on (" + from.hashCode() + ")");
            from.lock.lock();
            System.out.println("Acquiring second lock on (" + to.hashCode() + ")");
            to.lock.lock();
            System.out.println("Transferring money from (" + from.hashCode() + ") to (" + to.hashCode() + ")");
            from.subtractMoney(1000);
            to.addMoney(1000);
            System.out.println("Transferred money from (" + from.hashCode() + ") to (" + to.hashCode() + ")");
            to.lock.unlock();
            System.out.println("Releasing second lock on (" + to.hashCode() + ")");
            from.lock.unlock();
            System.out.println("Releasing first lock on (" + from.hashCode() + ")");
        }
    }
}
