package com.hussar.sm.cm.th.cache.lt;

public class BasicThread extends Thread {
    
    public BasicThread(String name) {
        super(name);
    }

    public void sleepNow(int second){
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public void sleepNowMills(int second){
        try {
            Thread.sleep(second);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
}
