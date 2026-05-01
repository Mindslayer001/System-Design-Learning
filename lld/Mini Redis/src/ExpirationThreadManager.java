import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExpirationThreadManager {
    ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    public ExpirationThreadManager(long sleepTimeInSec, DataStore ds) throws InterruptedException {
        Runnable cleanUpTask = new ExpireCollectors(ds);

        scheduler.scheduleAtFixedRate(cleanUpTask,sleepTimeInSec,sleepTimeInSec, TimeUnit.SECONDS);

    }
}
