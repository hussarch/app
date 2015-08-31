package com.hussar.sm.cg.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/**
 * @author yi.xiao
 *
 */
public class LocationLineFilter {

    private int line;
    private String[] conditions;
    private boolean find;
    
    public LocationLineFilter(String[] conditions){
        this.conditions = conditions;
    }
    
    public boolean match(String line, int currentLine){
        if(this.find){
            return true;
        }
        if(StringUtils.isNotBlank(line)){
            line = line.trim();
            for(String con : conditions){
                if(line.indexOf(con) >= 0){
                    this.find = true;
                    this.line = currentLine;
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean patternMatches(String regx, String content){
        try{
            Pattern pattern = Pattern.compile(regx);
            return pattern.matcher(content).matches();
        }catch(Exception e){
            return false;
        }
        
    }
    
    public int getLine() {
        return line;
    }
    public void setLine(int line) {
        this.line = line;
    }
    public String[] getConditions() {
        return conditions;
    }
    public void setConditions(String[] conditions) {
        this.conditions = conditions;
    }
    public boolean isFind() {
        return find;
    }
    public void setFind(boolean find) {
        this.find = find;
    }

    public boolean match(String line, String filter) {
        return line.matches(filter);
    }
    
}
