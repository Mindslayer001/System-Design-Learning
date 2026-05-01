public class ExpireCollectors implements Runnable {
    private static ExpireCollectors singleInstance = null;
    DataStore ds;
    ExpireRuleManager manager;
    private long sleepTime;
    public ExpireCollectors(DataStore ds){
        this.ds = ds;
        this.manager = new ExpireRuleManager();
    }
    private long count = 0;
    public static ExpireCollectors getInstance(DataStore ds){
        if (singleInstance == null)
            singleInstance = new ExpireCollectors(ds);
        return singleInstance;
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        for(CacheItem item : ds.getAllValues()){
            if(!manager.checkAllRules(item)){
                if (ds.deleteExpired(item.getKey(),item)) {
                    count++;
                }
            }
        }
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        System.out.println(this.getClass().getSimpleName()+": Cleaned "+count+" Expired keys in " + duration + " ms");
        count=0;
    }
}
