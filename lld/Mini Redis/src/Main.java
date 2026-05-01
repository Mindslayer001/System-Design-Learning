import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void printMemoryMetrics() {
        Runtime runtime = Runtime.getRuntime();
        long usedMemory = (runtime.totalMemory() - runtime.freeMemory()) / (1024 * 1024);
        System.out.println("Used Memory: " + usedMemory + " MB");
    }

    public static void main(String[] args) {
        MiniDataBase db = new MiniDataBase();
        int numThreads = 64; // K99 Contention Check
        AtomicInteger totalKeys = new AtomicInteger(0);

        List<Thread> threads = new ArrayList<>();
        List<Long> getLatencies = Collections.synchronizedList(new ArrayList<>());

        for (int t = 0; t < numThreads; t++) {
            Thread thread = new Thread(() -> {
                try {
                    while (true) {
                        for (int i = 0; i < 1000; i++) {
                            int currentKey = totalKeys.getAndIncrement();
                            String key = "Key" + currentKey;

                            long putStart = System.nanoTime();
                            try {
                                db.put(key, "LargeValuePayload..." + currentKey, 10);
                            } catch (KeyNotFoundException e) {
                                e.printStackTrace();
                            }
                            long putDuration = System.nanoTime() - putStart;

                            long getStart = System.nanoTime();
                            try {
                                db.get(key);
                            } catch (KeyNotFoundException ignored) {}
                            long getDuration = System.nanoTime() - getStart;

                            // Sample subset of latencies for P99
                            if (currentKey % 100 == 0) {
                                getLatencies.add(getDuration);
                            }
                        }
                    }
                } catch (OutOfMemoryError e) {
                    System.err.println("BREAKING POINT REACHED!");
                    System.err.println("Total Keys at Crash: " + totalKeys.get());
                    System.exit(1);
                }
            });
            threads.add(thread);
            thread.start();
        }

        // Monitoring thread for Memory, Throughput and P99
        new Thread(() -> {
            try {
                int lastTotal = 0;
                while (true) {
                    Thread.sleep(2000); // Check every 2s
                    int currentTotal = totalKeys.get();
                    System.out.println("-------------------------------------------------");
                    System.out.println("Inserted: " + currentTotal + " keys.");
                    System.out.println("Current Map Size: " + db.getSize());
                    printMemoryMetrics();
                    System.out.println("Throughput (keys/sec): " + (currentTotal - lastTotal) / 2);
                    lastTotal = currentTotal;

                    // P99 Check
                    synchronized (getLatencies) {
                        if (!getLatencies.isEmpty()) {
                            List<Long> sorted = new ArrayList<>(getLatencies);
                            Collections.sort(sorted);
                            int p99Index = (int) (sorted.size() * 0.99);
                            long p99Latency = sorted.get(Math.min(p99Index, sorted.size() - 1));
                            double p99Ms = p99Latency / 1_000_000.0;
                            
                            System.out.println("P99 db.get() Latency: " + p99Ms + " ms");
                            
                            if (p99Ms > 5.0) {
                                System.err.println("WARNING: P99 Latency exceeded 5ms! (" + p99Ms + " ms)");
                            }
                            getLatencies.clear(); // Reset for next timeframe
                        }
                    }
                }
            } catch (InterruptedException e) {
                //noinspection CallToPrintStackTrace
                e.printStackTrace();
            }
        }).start();

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}