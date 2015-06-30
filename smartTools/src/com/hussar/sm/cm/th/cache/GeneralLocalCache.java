package com.hussar.sm.cm.th.cache;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


/**
 * @author yi.xiao
 *
 */
public class GeneralLocalCache<K, T> {
    
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss S");
    
    private String name;
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    private Map<K, CachedStateData<T>> map;

    public GeneralLocalCache(String name, int initialCapacity) {
        this.name = name;
        map = new HashMap<K, CachedStateData<T>>(initialCapacity);
    }

    public void put(K key, T value) {
        try {
            lock.writeLock().lock();
            map.put(key, getNewCachedStateData(value));
        } finally {
            lock.writeLock().unlock();
        }
    }
    
    public void putAll(final Map<K, CachedStateData<T>> newMap) {
        if(newMap.size() == 0){
            return;
        }
        try {
            lock.writeLock().lock();
            System.out.println("=================== start to put data, current size is " + map.size());
            map.putAll(newMap);
        } finally {
            System.out.println("=================== after put data, current size is " + map.size());
            lock.writeLock().unlock();
        }
    }
    
    @Deprecated
    public void putOnebyOne(K key, T value, boolean isFirest, boolean isLast) {
        if(isFirest){
            lock.writeLock().lock(); 
            System.out.println("=================== start to put data, current size is " + map.size());
        }
        map.put(key, getNewCachedStateData(value));
        if(isLast){
            System.out.println("=================== after put data, current size is " + map.size());
            lock.writeLock().unlock(); 
        }
    }
    
    public void realseWriteLock() {
        lock.writeLock().unlock(); 
    }

    private CachedStateData<T> getNewCachedStateData(T t) {
        return new CachedStateData<T>(t);
    }

    public T get(K key) {
        try {
            lock.readLock().lock();
            CachedStateData<T> data = map.get(key);
            if (data != null) {
                return data.getData();
            } else {
                return null;
            }
        } finally {
            lock.readLock().unlock();
        }
    }
    
    public T getOneByOne(K key, boolean isFirest, boolean isLast) {
        try {
            if(isFirest){
                lock.readLock().lock();
            }
            CachedStateData<T> data = map.get(key);
            if (data != null) {
                return data.getData();
            } else {
                return null;
            }
        } finally {
            if(isLast){
                lock.readLock().unlock();
            }
        }
    }
    
    public void realseReadLock() {
        lock.readLock().unlock(); 
    }

    public CachedStateData<T> getCachedStateData(K key) {
        return map.get(key);
    }

    public void removeAllExpiredData(int second) {
        int startSize = 0;
        try {
            lock.writeLock().lock();
            startSize = map.size();
            long currentTime = System.currentTimeMillis();
            System.out.println("------------------ start to remove the expired data, current size is " + startSize + " --- current Datetime: " 
                    + dateFormat.format(new Date(currentTime)) + " --- expired time long second: " + second + ", hour: " + second/3600);
            Iterator<K> it = this.map.keySet().iterator();
            CachedStateData<T> data;
            
            while (it.hasNext()) {
                K key = it.next();
                data = this.map.get(key);
                if (data.isExpired(currentTime, second)) {
//                    System.out.println("------------------ remove the product, cached date time: " + dateFormat.format(new Date(data.getCachedTime())) + ", product: " + key);
                    it.remove();
                }
            }
        } finally {
            System.out.println("------------------ after remove the expired data, current size is " + map.size() + ", removed " + (startSize - map.size()));
            lock.writeLock().unlock();
        }
    }

    public int getSize() {
        return this.map.size();
    }

    public List<T> getList(List<K> keys) {
        try {
            lock.readLock().lock();
            List<T> list = new ArrayList<T>();
            CachedStateData<T> data;
            for (K key : keys) {
                data = this.map.get(key);
                if (data != null) {
                    list.add(data.getData());
                }
            }
            return list;
        } finally {
            lock.readLock().unlock();
        }
    }

    public List<T> getList(CacheFilter<K> filter) {
        try {
            lock.readLock().lock();
            List<T> list = new ArrayList<T>();
            Iterator<K> it = this.map.keySet().iterator();
            CachedStateData<T> data;
            while (it.hasNext()) {
                K key = it.next();
                if (filter.match(key)) {
                    data = this.map.get(key);
                    list.add(data.getData());
                }
            }
            return list;
        } finally {
            lock.readLock().unlock();
        }
    }
    
    public <V> Set<V> getSet(CacheFilterDisposer<K, T, V> filter) {
        try {
            lock.readLock().lock();
            Set<V> set = new HashSet<V>();
            Iterator<K> it = this.map.keySet().iterator();
            CachedStateData<T> data;
            while (it.hasNext()) {
                K key = it.next();
                if (filter.match(key)) {
                    data = this.map.get(key);
                    set.add(filter.dispose(data.getData()));
                }
            }
            return set;
        } finally {
            lock.readLock().unlock();
        }
    }
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
