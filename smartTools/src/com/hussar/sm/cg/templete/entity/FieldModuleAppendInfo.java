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
    
    public FieldModuleAppendInfo(String destObjName, String origObjName, String origInstanceName, String fieldName) {
        super(origInstanceName, fieldName);
        this.origObjName = origObjName;
        this.destObjName = destObjName;  
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

    @Override
    public String toString() {
        return "FieldModuleAppendInfo [origObjName=" + origObjName + ", destObjName=" + destObjName + "] + super: " + super.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((destObjName == null) ? 0 : destObjName.hashCode());
        result = prime * result + ((origObjName == null) ? 0 : origObjName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        FieldModuleAppendInfo other = (FieldModuleAppendInfo) obj;
        if(!super.equals(obj)){
            return false;
        }
        if (destObjName == null) {
            if (other.destObjName != null) {
                return false;
            }
        } else if (!destObjName.equals(other.destObjName)) {
            return false;
        }
        if (origObjName == null) {
            if (other.origObjName != null) {
                return false;
            }
        } else if (!origObjName.equals(other.origObjName)) {
            return false;
        }
        return true;
    }


    
}
