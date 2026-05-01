import java.util.Collection;
import java.util.List;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DataStore {
    private Map<String,CacheItem> datamap;

    public DataStore(){
        datamap = new ConcurrentHashMap<>();
    }

    public void put(String key, String value, LocalDateTime expireTime) throws KeyNotFoundException {
        CacheItem item = new CacheItem(key,value,expireTime);
        datamap.put(key, item);
    }

    public void delete(String key){
        datamap.remove(key);
    }

    public CacheItem get(String key){
        return datamap.get(key);
    }
    public boolean containKey(String key){
        return datamap.containsKey(key);
    }

    public boolean deleteExpired(String key,CacheItem item){
        return datamap.remove(key,item);
    }

    public Collection<CacheItem> getAllValues(){
        return this.datamap.values();
    }

    public int getSize() {
        return this.datamap.size();
    }
}
