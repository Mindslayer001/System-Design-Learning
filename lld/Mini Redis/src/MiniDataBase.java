import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;

public class MiniDataBase {
    private DataStore ds = new DataStore();
    private ExpireRuleManager ruleManager = new ExpireRuleManager();
    private ExpirationThreadManager garbageManager;
    public MiniDataBase() {

        try{
            garbageManager = new ExpirationThreadManager(2,ds);
        } catch (InterruptedException e){
            System.out.println("Garbage Manage error: " + e.getMessage());
        }
    }

    public String get(String key) throws KeyNotFoundException {
        if (!ds.containKey(key)) {

            throw new KeyNotFoundException("The key is not available");
        }
        CacheItem item = ds.get(key);
        if (!ruleManager.checkAllRules(item)){
            this.delete(key);
            throw new KeyNotFoundException("The key is not available");
        }
        return item.getValue();
    }

    public void put(String key, String value, long ttlInSecs) throws KeyNotFoundException {
        LocalDateTime expireTime = (ttlInSecs<0) ? LocalDateTime.MAX : LocalDateTime.now().plusSeconds(ttlInSecs);
        ds.put(key,value,expireTime);
    }

    public boolean ContainsKey (String key){
        return ds.containKey(key);
    }

    public void delete(String key) throws KeyNotFoundException {
        if (!ds.containKey(key)) {
            throw new KeyNotFoundException("The key is not available");
        }
        ds.delete(key);
        return;
    }

    public int getSize() {
        return ds.getSize();
    }
}
