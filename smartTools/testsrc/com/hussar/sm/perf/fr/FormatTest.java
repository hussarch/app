package com.hussar.sm.perf.fr;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.commons.lang.StringUtils;


public class FormatTest {

    
    ThreadLocal<SimpleDateFormat> tl = new ThreadLocal<SimpleDateFormat>(){

       transient int i = 0;
        
        public SimpleDateFormat initialValue(){
            System.out.println(i++);
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
        
    };
    
    public Date getParseDate(String date){
        if(StringUtils.isNotBlank(date)){
            try {
                return tl.get().parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    
    private String[] array = {
            "2015-09-25 12:23:33", "2015-05-25 12:23:33", "2015-09-25 12:23:33","2015-09-25 12:23:33", "2015-09-25 12:23:33","2015-09-25 12:23:33" ,"2015-09-25 12:23:33",
            "2015-12-25 12:23:33", "2015-05-15 12:23:33", "2015-09-25 12:23:33","2015-09-25 12:23:33", "2015-09-25 12:23:33","2015-09-25 12:23:33" ,"2015-09-25 12:23:33",
            "2015-01-25 12:23:33", "2015-12-25 12:23:33", "2015-09-25 12:23:33","2015-09-25 12:23:33", "2015-09-25 12:23:33","2015-09-25 12:23:33" ,"2015-09-25 12:23:33",
    };
    
    
    public void test(){
        (new Thread(){
            public void run(){
                for(int i = 0; i < 20; i++){
                    getParseDate(array[i]);
                    Random  random = new Random();
                    sleepNow(random.nextInt(50));
                }
            }
        }).start();
    }
    
    private void sleepNow(long millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    
    public void testInThread(){
        for(int i = 0; i < 10; i++){
            test();
        }
    }
    
    public static void main(String[] args) {
        FormatTest test = new FormatTest();
        test.testInThread();
    }
    
    
}
