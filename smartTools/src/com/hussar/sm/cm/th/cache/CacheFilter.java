package com.hussar.sm.cm.th.cache;

/**
 * @author yi.xiao
 *
 */
public interface CacheFilter<K> {

    public boolean match(K key);
    
    
    
}
