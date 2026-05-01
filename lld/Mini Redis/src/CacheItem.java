import java.time.LocalDateTime;

public class CacheItem {
    private final String key;
    private final String value;
    private final LocalDateTime expireTime;

    public CacheItem(String key, String value, LocalDateTime expireTime){
        this.key = key;
        this.value = value;
        this.expireTime = expireTime;
    }


    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public String getValue() {
        return value;
    }

    public String getKey() {
        return key;
    }
}
