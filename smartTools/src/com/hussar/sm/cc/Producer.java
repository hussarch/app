package com.hussar.sm.cc;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable{  
  
    protected BlockingQueue queue = null;  
  
    public Producer(BlockingQueue queue) {  
        this.queue = queue;  
    }  
  
    public void run() {  
        try {  
            for(int i = 0; i < 10; i++){
                queue.put(String.valueOf(i));
                Thread.sleep(1000);
                System.out.println("Set data .." + i);
            }
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
    }  
}  