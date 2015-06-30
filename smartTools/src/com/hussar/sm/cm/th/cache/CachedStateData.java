package com.hussar.sm.cm.th.cache;

/**
 * @author yi.xiao
 *
 */
public class CachedStateData<T> {

    private T data;
    private Long cachedTime;
    
    public CachedStateData(T t) {
        this.data = t;
        this.cachedTime = System.currentTimeMillis();
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Long getCachedTime() {
        return cachedTime;
    }

    public void setCachedTime(Long cachedTime) {
        this.cachedTime = cachedTime;
    }

    public boolean isExpired(long currentTime, int second) {
        return (currentTime - cachedTime) > second * 1000;
    }


}
