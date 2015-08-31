package com.hussar.sm.cg.core.common;

/**
 * @author yi.xiao
 *
 */
public class CommonCodeGenerator {
    
    public static final String TAB_SPACE = "    ";
    public static final String NEW_LINE = "\n";
    
    public String getTab(int size){
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < size; i++){
            builder.append(TAB_SPACE);
        }
        return builder.toString();
    }
}
