package com.hussar.sm.cg.templete.entity;

public class FieldModuleInfo {

    private String origInstanceName;
    private String fieldName;
    private Boolean primitiveFlag;
    
    public FieldModuleInfo(){
    }
    
    public FieldModuleInfo(String origInstanceName, String fieldName){
        this.origInstanceName = origInstanceName;
        this.fieldName = fieldName;
        this.primitiveFlag = Boolean.TRUE;
    }

    public String getOrigInstanceName() {
        return origInstanceName;
    }

    public void setOrigInstanceName(String origInstanceName) {
        this.origInstanceName = origInstanceName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Boolean getPrimitiveFlag() {
        return primitiveFlag;
    }

    public void setPrimitiveFlag(Boolean primitiveFlag) {
        this.primitiveFlag = primitiveFlag;
    }

}
