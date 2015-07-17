package com.hussar.sm.cg.templete;

import com.hussar.sm.cg.templete.entity.FieldModuleInfo;

/**
 * @author yi.xiao
 *
 */
public class FieldModuleTypeInfo {

    private TemplateType type;
    private FieldModuleInfo fieldModuleInfo;
    
    public FieldModuleTypeInfo(TemplateType type, FieldModuleInfo fieldModuleInfo){
        this.type = type;
        this.fieldModuleInfo = fieldModuleInfo;
    }
    
    public FieldModuleTypeInfo(FieldModuleInfo fieldModuleInfo){
        this(TemplateType.COMMON, fieldModuleInfo);
    }

    public TemplateType getType() {
        return type;
    }

    public void setType(TemplateType type) {
        this.type = type;
    }

    public FieldModuleInfo getFieldModuleInfo() {
        return fieldModuleInfo;
    }

    public void setFieldModuleInfo(FieldModuleInfo fieldModuleInfo) {
        this.fieldModuleInfo = fieldModuleInfo;
    }

    @Override
    public String toString() {
        return "FieldModuleTypeInfo [type=" + type + ", fieldModuleInfo=" + fieldModuleInfo + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((fieldModuleInfo == null) ? 0 : fieldModuleInfo.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        FieldModuleTypeInfo other = (FieldModuleTypeInfo) obj;
        if (fieldModuleInfo == null) {
            if (other.fieldModuleInfo != null) {
                return false;
            }
        } else if (!fieldModuleInfo.equals(other.fieldModuleInfo)) {
            return false;
        }
        if (type != other.type) {
            return false;
        }
        return true;
    }

}
