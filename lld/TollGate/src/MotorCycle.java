public class MotorCycle extends Vehicle{
    MotorCycle(int RFIDBalance){
        this.RFIDBalance = RFIDBalance;
    }

    @Override
    public int calculateToll() {
        return 30;
    }

    @Override
    public String toString() {
        return String.valueOf(this.getClass());
    }
}
