package com.hussar.sm.cg.templete.entity;

/**
 * @author yi.xiao
 *
 */
public class FieldModuleAppendInfo extends FieldModuleInfo {
    
    private String origObjName;
    private String destObjName;
    
    public FieldModuleAppendInfo(){
        super.setPrimitiveFlag(false);
    }
    
    public String getOrigObjName() {
        return origObjName;
    }
    public void setOrigObjName(String origObjName) {
        this.origObjName = origObjName;
    }
    public String getDestObjName() {
        return destObjName;
    }
    public void setDestObjName(String destObjName) {
        this.destObjName = destObjName;
    }
    
    
}
