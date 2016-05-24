package com.hussar.sm.cg;

/**
 * @author yi.xiao
 * Created on 2016年5月23日 下午4:27:06
 * © 2015 Fanli.com
 * 
 */
public class HCode {
    
    public String s = "";
    
    
    public static void main(String[] args) {
        HCode code = new HCode();
        System.out.println(code.hashCode());
        System.out.println(code.hashCode());
        code.s = "123";
        System.out.println(code.hashCode());
        code.s = "abc";
        System.out.println(code.hashCode());
    }
    
}
