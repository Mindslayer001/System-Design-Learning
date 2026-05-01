public class Car extends Vehicle{
    public Car(int RFIDBalance){
        this.RFIDBalance = RFIDBalance;
    }
    @Override
    public int calculateToll() {
        return 50;
    }

    @Override
    public String toString() {
        return String.valueOf(this.getClass());
    }
}
