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

    @Override
    public String toString() {
        return "origInstanceName=" + origInstanceName + ", fieldName=" + fieldName + ", primitiveFlag=" + primitiveFlag;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((fieldName == null) ? 0 : fieldName.hashCode());
        result = prime * result + ((origInstanceName == null) ? 0 : origInstanceName.hashCode());
        result = prime * result + ((primitiveFlag == null) ? 0 : primitiveFlag.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        FieldModuleInfo other = (FieldModuleInfo) obj;
        if (fieldName == null) {
            if (other.fieldName != null)
                return false;
        } else if (!fieldName.equals(other.fieldName))
            return false;
        if (origInstanceName == null) {
            if (other.origInstanceName != null)
                return false;
        } else if (!origInstanceName.equals(other.origInstanceName))
            return false;
        if (primitiveFlag == null) {
            if (other.primitiveFlag != null)
                return false;
        } else if (!primitiveFlag.equals(other.primitiveFlag))
            return false;
        return true;
    }

}
