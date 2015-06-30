package com.hussar.sm.cg;

public class OrigDestFieldInfo {

    private FieldClassInfo dest;
    private FieldClassInfo orig;
    private boolean primitiveField;
    private String fieldName;

    public OrigDestFieldInfo(Class<?> destClass, String fieldName) {
        this(destClass, destClass, fieldName);
        this.primitiveField = true;
    }

    public OrigDestFieldInfo(Class<?> destClass, Class<?> origClass, String fieldName) {
        this(new FieldClassInfo(destClass), new FieldClassInfo(origClass), fieldName);
    }
    
    public OrigDestFieldInfo(FieldClassInfo destClass, FieldClassInfo origClass, String fieldName) {
        this.dest = destClass;
        this.orig = origClass;
        this.fieldName = fieldName;
        this.primitiveField = false;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public FieldClassInfo getDest() {
        return dest;
    }

    public void setDest(FieldClassInfo dest) {
        this.dest = dest;
    }

    public FieldClassInfo getOrig() {
        return orig;
    }

    public void setOrig(FieldClassInfo orig) {
        this.orig = orig;
    }

    public boolean isPrimitiveField() {
        return primitiveField;
    }

    public void setPrimitiveField(boolean primitiveField) {
        this.primitiveField = primitiveField;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((dest == null) ? 0 : dest.hashCode());
        result = prime * result + ((fieldName == null) ? 0 : fieldName.hashCode());
        result = prime * result + ((orig == null) ? 0 : orig.hashCode());
        result = prime * result + (primitiveField ? 1231 : 1237);
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
        OrigDestFieldInfo other = (OrigDestFieldInfo) obj;
        if (dest == null) {
            if (other.dest != null)
                return false;
        } else if (!dest.equals(other.dest))
            return false;
        if (fieldName == null) {
            if (other.fieldName != null)
                return false;
        } else if (!fieldName.equals(other.fieldName))
            return false;
        if (orig == null) {
            if (other.orig != null)
                return false;
        } else if (!orig.equals(other.orig))
            return false;
        if (primitiveField != other.primitiveField)
            return false;
        return true;
    }

}
