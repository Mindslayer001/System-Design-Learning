public class Truck extends Vehicle{
    int weight;
    Truck(int RFIDBalance, int weight){
        this.RFIDBalance = RFIDBalance;
        this.weight = weight;
    }

    @Override
    public int calculateToll() {
        return 50 + (10 * weight/100);
    }


    public int getWeight(){
        return weight;
    }

    @Override
    public String toString() {
        return String.valueOf(this.getClass());
    }
}
