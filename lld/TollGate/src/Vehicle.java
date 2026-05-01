public abstract class Vehicle {
    int RFIDBalance = 0;
    public final int getRFID() {
        return RFIDBalance;
    }
    public abstract int calculateToll();
    public final void deductRFID(int amount) {
        this.RFIDBalance -= amount;
    }
}
