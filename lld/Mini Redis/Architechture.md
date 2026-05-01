CacheItem:
    - String key
    - String value
    - DateTime ExpireTime
    - getters and setters

DataStore:
    - DataMap <key_Hash(cacheItem), CacheItem>
    - getters and setters and delete method

Expire Collectors:
    - SingleTon
    - sleep for 60 secs and do its job and sleep again

ExpireRuleManager:
    - Custom Rules to handle the Expiration.

MiniDataBase:
    - DataStore ds
    - Expire Collector ec
    - put, get and delete methods