package com.hussar.sm.cm.th.cache.lt;

import java.util.List;
import java.util.Random;

import com.hussar.sm.cm.th.cache.CacheFilter;
import com.hussar.sm.cm.th.cache.GeneralLocalCache;

public class SearchDataThread extends BasicThread {
    
    private GeneralLocalCache<String, String> cache;
    private CacheFilter<String> filter;

    public SearchDataThread(String name, GeneralLocalCache<String, String> cache, CacheFilter<String> filter) {
        super(name);
        this.cache = cache;
        this.filter = filter;
    }
    
    public void run(){
        Random random = new Random();
        while(true){
            List<String> list = this.cache.getList(filter);
            System.out.println(this.getName() + ", Find the result: " + list.size());
            sleepNowMills(random.nextInt(10) * 100);
        }
    }
    
    
}
