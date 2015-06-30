package com.hussar.sm.cm.th.cache.lt;

import com.hussar.sm.cm.th.cache.GeneralLocalCache;

/**
 * @author yi.xiao
 *
 */
public class RemoveExpiredDataThread extends BasicThread {

    private GeneralLocalCache<String, String> cache;

    public RemoveExpiredDataThread(String name, GeneralLocalCache<String, String> cache) {
        super(name);
        this.cache = cache;
    }
    
    public void run(){
        while(true){
            this.cache.removeAllExpiredData(80);
            sleepNow(120);
        }
    }
    
}
