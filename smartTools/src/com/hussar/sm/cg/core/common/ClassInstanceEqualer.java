package com.hussar.sm.cg.core.common;

import java.util.Arrays;


/**
 * @author yi.xiao
 *
 */
public class ClassInstanceEqualer {

    private boolean equal;
    private boolean continueFlag = true;
    
    private ClassInstanceEqualer(Object obj1, Object obj2){
        if(obj1 == obj2){
            equal = true;
            continueFlag = false;
        }else if(!obj1.getClass().equals(obj2.getClass())){
            equal = false;
            continueFlag = false;
        }
    }
    
    public static ClassInstanceEqualer getInstance(Object obj1, Object obj2){
        return new ClassInstanceEqualer(obj1, obj2);
    }
    
    public ClassInstanceEqualer append(boolean val1, boolean val2) {
        if(continueFlag && equal){
            equal = val1 == val2;
        }
        return this;
    }
    
    public ClassInstanceEqualer append(char val1, char val2) {
        if(continueFlag && equal){
            equal = val1 == val2;
        }
        return this;
    }
    
    public ClassInstanceEqualer append(byte val1, byte val2) {
        if(continueFlag && equal){
            equal = val1 == val2;
        }
        return this;
    }
    
    public ClassInstanceEqualer append(short val1, short val2) {
        if(continueFlag && equal){
            equal = val1 == val2;
        }
        return this;
    }
    
    public ClassInstanceEqualer append(int val1, int val2) {
        if(continueFlag && equal){
            equal = val1 == val2;
        }
        return this;
    }
    
    public ClassInstanceEqualer append(long val1, long val2) {
        if(continueFlag && equal){
            equal = val1 == val2;
        }
        return this;
    }
    
    public ClassInstanceEqualer append(float val1, float val2) {
        if(continueFlag && equal){
            equal = val1 == val2;
        }
        return this;
    }
    
    public ClassInstanceEqualer append(double val1, double val2) {
        if(continueFlag && equal){
            equal = val1 == val2;
        }
        return this;
    }
    
    public ClassInstanceEqualer append(Object val1, Object val2) {
        if(continueFlag && equal){
            if(val1 == val2){
                equal = true;
            }else if(val1 != null){
                equal = val1.equals(val2); 
            }else{
                equal = val2 == null;
            }
        }
        return this;
    }
    
    public ClassInstanceEqualer append(boolean[] val1, boolean[] val2) {
        if(continueFlag && equal){
            equal = Arrays.equals(val1, val2);
        }
        return this;
    }
    
    public ClassInstanceEqualer append(char[] val1, char[] val2) {
        if(continueFlag && equal){
            equal = Arrays.equals(val1, val2);
        }
        return this;
    }
    
    public ClassInstanceEqualer append(byte[] val1, byte[] val2) {
        if(continueFlag && equal){
            equal = Arrays.equals(val1, val2);
        }
        return this;
    }
    
    public ClassInstanceEqualer append(short[] val1, short[] val2) {
        if(continueFlag && equal){
            equal = Arrays.equals(val1, val2);
        }
        return this;
    }
    
    public ClassInstanceEqualer append(int[] val1, int[] val2) {
        if(continueFlag && equal){
            equal = Arrays.equals(val1, val2);
        }
        return this;
    }
    
    public ClassInstanceEqualer append(long[] val1, long[] val2) {
        if(continueFlag && equal){
            equal = Arrays.equals(val1, val2);
        }
        return this;
    }
    
    public ClassInstanceEqualer append(float[] val1, float[] val2) {
        if(continueFlag && equal){
            equal = Arrays.equals(val1, val2);
        }
        return this;
    }
    
    public ClassInstanceEqualer append(double[] val1, double[] val2) {
        if(continueFlag && equal){
            equal = Arrays.equals(val1, val2);
        }
        return this;
    }
    
    public ClassInstanceEqualer append(Object[] val1, Object[] val2) {
        if(continueFlag && equal){
            equal = Arrays.equals(val1, val2);
        }
        return this;
    }
    
    public boolean isEqual() {
        return equal;
    }

}
