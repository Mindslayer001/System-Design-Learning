import java.time.LocalDateTime;

public class CashPayment implements IPayment {
    TollCollection tollLog = TollCollection.getInstance();
    TollGateRules gateRules = TollGateRules.getInstance();
    @Override
    public boolean collectPayment(Vehicle vehicle) {
        LocalDateTime now = LocalDateTime.now();
        tollLog.addLog(vehicle, gateRules.checkConstraints(vehicle.calculateToll(),now));
        return true;
    }
}
