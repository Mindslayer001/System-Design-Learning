import java.time.LocalDateTime;

public class RFIDPayment implements IPayment{
    TollCollection tollLog = TollCollection.getInstance();
    TollGateRules gateRules = TollGateRules.getInstance();
    @Override
    public boolean collectPayment(Vehicle vehicle) throws InsufficientBalanceException{
        LocalDateTime now = LocalDateTime.now();
        int tollAmt =  gateRules.checkConstraints(vehicle.calculateToll(),now);
        if (vehicle.getRFID()>= tollAmt){
            vehicle.deductRFID(tollAmt);
            tollLog.addLog(vehicle,tollAmt);
        } else{
                throw new InsufficientBalanceException(vehicle.toString() + "has no enough balance");
            }
        return true;
    }
}

