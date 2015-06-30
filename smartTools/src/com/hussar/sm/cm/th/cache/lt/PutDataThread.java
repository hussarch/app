package com.hussar.sm.cm.th.cache.lt;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.hussar.sm.cm.th.cache.CachedStateData;
import com.hussar.sm.cm.th.cache.GeneralLocalCache;

/**
 * @author yi.xiao
 *
 */
public class PutDataThread extends BasicThread {
    
    private GeneralLocalCache<String, String> cache;
    
    public PutDataThread(String name, GeneralLocalCache<String, String> cache){
        super(name);
        this.cache = cache;
    }
 
    public void run(){
         while(true){
            System.out.println("Start update the map");
            cache.putAll(getInitMap());
            sleepNow(60);
         }
    }   
    
    private Map<String, CachedStateData<String>> getInitMap(){
        Map<String, CachedStateData<String>> map = new HashMap<String, CachedStateData<String>>();
        Random random = new Random();
        for(int i = 0; i < 1000; i++){
            int val = random.nextInt(10000);
            map.put(String.valueOf(val), new CachedStateData<String>("abc_" + val));
        }
        return map;
    }
    
}
