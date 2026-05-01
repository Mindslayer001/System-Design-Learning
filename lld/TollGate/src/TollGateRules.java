import java.time.DayOfWeek;
import java.time.LocalDateTime;

class TollGateRules {
    private int weekendRules(int amount, LocalDateTime localdate){
       if (localdate.getDayOfWeek() == DayOfWeek.SATURDAY || localdate.getDayOfWeek() == DayOfWeek.SUNDAY)
           return amount*2;
       return amount;
    }

    public int checkConstraints(int amount, LocalDateTime localdate){
        amount = weekendRules(amount, localdate);
        return amount;
    }

    private static TollGateRules singleInstance = null;

    public static synchronized TollGateRules getInstance(){
        if (singleInstance == null)
            singleInstance =  new TollGateRules();

        return singleInstance;
    }
}
