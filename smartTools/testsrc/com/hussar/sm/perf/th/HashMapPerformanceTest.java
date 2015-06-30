package com.hussar.sm.perf.th;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author yi.xiao
 *
 */
public class HashMapPerformanceTest {
    
    public Map<Integer, String> getMap(int size){
        Map<Integer, String> map = new ConcurrentHashMap<Integer, String>();
        for(int i = 0; i < size; i++){
            map.put(i, UUID.randomUUID().toString());
        }
        return map;
    }
    
    public List<String> getAllUids(Map<Integer, String> map){
        List<String> list = new ArrayList<String>();
        Iterator<Integer> it = map.keySet().iterator();
        while (it.hasNext()) {
            Integer key = it.next();
            list.add(map.get(key));
        }
        return list;
    }
    
    public void testGetInThread(String name, final Map<Integer, String> map){
        (new Thread(){
            
            public void run(){
                int i = 0;
                while(i < 200){
                    long start = System.currentTimeMillis();
                    getAllUids(map);
                    long end = System.currentTimeMillis();
                    if(end - start > 1000){
                        System.out.println("Warning...get all spend to much time" + this.getName() + "_" + i + ", time is " + (end - start));
                    }
                    i++;
                }
            }
        }).start();
    }
    
    
    public void testInManyThread(int size){
        System.out.println("start to test...");
        Map<Integer, String> map = getMap(2000);
        for(int i = 0; i < size; i++){
            testGetInThread("map_iterator_all_" + i, map);
        }
    }
    
    public static void main(String[] args) {
        HashMapPerformanceTest performance = new HashMapPerformanceTest();
        performance.testInManyThread(500);
    }
    
    
}
