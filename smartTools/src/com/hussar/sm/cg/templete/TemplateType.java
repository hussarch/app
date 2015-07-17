package com.hussar.sm.cg.templete;

/**
 * @author yi.xiao
 *
 */
public enum TemplateType {

    COMMON("common"), ENTITY("entity"), LIST_ENTITY("List<entity>"),
    CONSTRUCTOR("contructor<entity>");
    
    private String tag;

    TemplateType(String tag){
        this.tag = tag;
    }
    
    public String getTag(){
        return this.tag;
    }
    
}
