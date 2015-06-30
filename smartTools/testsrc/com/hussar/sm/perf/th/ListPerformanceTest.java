package com.hussar.sm.perf.th;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author yi.xiao
 *
 */
public class ListPerformanceTest {
    
    public List<String> getList(int size){
        List<String> list = new ArrayList<String>();
        for(int i = 0; i < size; i++){
            list.add(UUID.randomUUID().toString());
        }
        return list;
    }
    
    public List<String> getAllUids(List<String> vals){
        List<String> list = new ArrayList<String>();
        for (String val : vals) {
            list.add(val);
        }
        return list;
    }
    
    public void testGetInThread(String name, final List<String> list){
        (new Thread(){
            
            public void run(){
                int i = 0;
                while(i < 200){
                    long start = System.currentTimeMillis();
                    getAllUids(list);
                    long end = System.currentTimeMillis();
                    if(end - start > 500){
                        System.out.println("Warning...get all spend to much time" + this.getName() + "_" + i + ", time is " + (end - start));
                    }
                    i++;
                }
            }
        }).start();
    }
    
    
    public void testInManyThread(int size){
        System.out.println("start to test...");
        List<String> list = getList(2000);
        for(int i = 0; i < size; i++){
            testGetInThread("map_iterator_all_" + i, list);
        }
    }
    
    public static void main(String[] args) {
        ListPerformanceTest performance = new ListPerformanceTest();
        performance.testInManyThread(500);
    }
    
    
}
