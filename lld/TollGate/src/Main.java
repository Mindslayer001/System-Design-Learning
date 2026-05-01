class simulation implements Runnable {

    @Override
    public void run() {
        for (int i =0; i<10000;i++){
            Vehicle v1 = new Car(100);
            RFIDPayment paymentMethod =  new RFIDPayment();
            try {
                paymentMethod.collectPayment(v1);
                System.out.println("payment is Completed for " + i + "th time");
            } catch (InsufficientBalanceException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Runnable task1 = new simulation();
        Thread t1 = new Thread(task1);
        Thread t2 = new Thread(task1);
        Thread t3 = new Thread(task1);

        t1.start();
        t2.start();
        t3.start();

        // Wait for all toll booths to finish their shifts
        t1.join();
        t2.join();
        t3.join();

        // Let's count the money in the vault
        int totalMoney = TollCollection.getInstance().TotalCollection();
        System.out.println("\n--- SHIFT OVER ---");
        System.out.println("Expected Total: " + 50*10000*3);
        System.out.println("Actual Total:   " + totalMoney);

        if (totalMoney != 50*10000*3) {
            System.out.println("🚨 ALERT: We lost $" + ((50*10000*3) - totalMoney) + " to a race condition!");
        }
    }
}