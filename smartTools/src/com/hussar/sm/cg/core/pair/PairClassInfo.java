package com.hussar.sm.cg.core.pair;

/**
 * @author yi.xiao
 */
public class PairClassInfo {
    
    private Class<?> destClass;
    private Class<?> orginClass;
    private boolean hasSuperClass; 
    
    public PairClassInfo(Class<?> destClass, Class<?> orginClass){
        this.destClass = destClass;
        this.orginClass = orginClass;
    }

    public Class<?> getDestClass() {
        return destClass;
    }

    public void setDestClass(Class<?> destClass) {
        this.destClass = destClass;
    }

    public Class<?> getOrginClass() {
        return orginClass;
    }

    public void setOrginClass(Class<?> orginClass) {
        this.orginClass = orginClass;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((destClass == null) ? 0 : destClass.hashCode());
        result = prime * result + ((orginClass == null) ? 0 : orginClass.hashCode());
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
        PairClassInfo other = (PairClassInfo) obj;
        if (destClass == null) {
            if (other.destClass != null) {
                return false;
            }
        } else if (!destClass.equals(other.destClass)) {
            return false;
        }
        if (orginClass == null) {
            if (other.orginClass != null) {
                return false;
            }
        } else if (!orginClass.equals(other.orginClass)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PairClassInfo [destClass=" + destClass.getSimpleName() + ", orginClass=" + orginClass.getSimpleName() + "]";
    }

    public void setHasSuperClass(boolean hasSuperClass) {
        this.hasSuperClass = hasSuperClass;
    }

    public boolean hasSuperClass() {
        return hasSuperClass;
    }
    
}
