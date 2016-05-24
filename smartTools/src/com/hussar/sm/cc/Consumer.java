package com.hussar.sm.cc;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable{  
  
    protected BlockingQueue queue = null;  
  
    public Consumer(BlockingQueue queue) {  
        this.queue = queue;  
    }  
  
    public void run() {  
        try {
            int i = 0;
            while(i < 20){
                System.out.println(queue.take()); 
                i++;
            }
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
    }  
}  