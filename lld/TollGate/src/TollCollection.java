import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

class TollCollection {
    Map<String, List<Integer>> log;
    private static TollCollection singleInstance = null;
    private TollCollection(){
        log = new ConcurrentHashMap<>();
    }

    public static synchronized TollCollection getInstance(){
        if (singleInstance == null)
            singleInstance = new TollCollection();

        return singleInstance;
    }

    public void addLog(Vehicle vehicle, Integer paid) {
        log.computeIfAbsent(vehicle.toString(), k -> Collections.synchronizedList(new ArrayList<>())).add(paid);
        return;
    }

    public int TotalCollection(){
        return log.values().stream().flatMap(List::stream).mapToInt(Integer::intValue).sum();
    }
}
