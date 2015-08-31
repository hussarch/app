package com.hussar.sm.cg.core.common;


public class EwtEntity {

    private int size;
    private String name;
    private int[] array;
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if(!super.equals(obj)){
            return false;
        }
        EwtEntity other = (EwtEntity)obj;
        return ClassInstanceEqualer.getInstance(this, other).append(this.size, other.size).append(this.name, other.name)
                .append(this.array, other.array).isEqual();
    }
    
    
}
