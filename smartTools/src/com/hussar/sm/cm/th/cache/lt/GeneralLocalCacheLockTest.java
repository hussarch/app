package com.hussar.sm.cm.th.cache.lt;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.hussar.sm.cm.th.DeadlockConsoleHandler;
import com.hussar.sm.cm.th.DeadlockDetector;
import com.hussar.sm.cm.th.cache.CacheFilter;
import com.hussar.sm.cm.th.cache.GeneralLocalCache;

public class GeneralLocalCacheLockTest {

    public void runAll(int searchSize){
        ExecutorService pool = Executors.newFixedThreadPool(searchSize + 3);
        GeneralLocalCache<String, String> cache = new GeneralLocalCache<String, String>("GeneralLocalCache", 200); 
        pool.execute(new PutDataThread("PutDataThread", cache));
        pool.execute(new SearchDataThread("S1", cache, new CacheFilter<String>() {
            @Override
            public boolean match(String key) {
                return key.contains("1");
            }
        }));
        final Random random = new Random();
        for(int i =0; i < searchSize; i++){
            pool.execute(new SearchDataThread("S" + (i + 1), cache, new CacheFilter<String>() {
                @Override
                public boolean match(String key) {
                    return key.contains(String.valueOf(random.nextInt(1000)));
                }
            }));
        }
        pool.execute(new RemoveExpiredDataThread("RemoveExpiredDataThread", cache));
    }
    
    public static void main(String[] args) {
        GeneralLocalCacheLockTest test = new GeneralLocalCacheLockTest();
        test.runAll(30);
        DeadlockDetector deadlockDetector = new DeadlockDetector(new DeadlockConsoleHandler(), 5, TimeUnit.SECONDS);
        deadlockDetector.start();
    }
    
}
