package com.hussar.sm.cm.th.cache;

/**
 * @author yi.xiao
 *
 */
public interface CacheFilterDisposer<K, T, V> extends CacheFilter<K>{

    public V dispose(T val); 
    
    
}
