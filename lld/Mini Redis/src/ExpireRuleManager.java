import java.time.LocalDateTime;

public class ExpireRuleManager {
    public boolean checkAllRules(CacheItem item){
        return expireTimeCheck(item);
    }

    public boolean expireTimeCheck(CacheItem item){
        if (item.getExpireTime().isBefore(LocalDateTime.now()) || item.getExpireTime().isEqual(LocalDateTime.now())){
            return false;
        }
        return true;
    }

}
