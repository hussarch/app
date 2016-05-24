package com.hussar.sm.cg.core.common;

import java.lang.reflect.Field;

public class ClassInstanceEqualCodeGenerator extends CommonCodeGenerator{
    
    private static final int CODE_LINE_MAX_LEN = 112;
    private StringBuilder builder;
    
    public ClassInstanceEqualCodeGenerator(){
        builder = new StringBuilder();
    }
    
    /**
     * return ClassInstanceEqualer.getInstance(this, other).append(this.size, other.size).append(this.name, other.name)
                .append(this.array, other.array).isEqual();
     */
    public String create(Class<?> clazz) {
        if(Object.class.equals(clazz)){
            return null;
        }
        appendln("@Override");
        appendln("public boolean equals(Object obj) {");
        appendln("    if (this == obj) {");
        appendln("        return true;");
        appendln("    }");
        appendln("    if (obj == null) {");
        appendln("        return false;");
        appendln("    }");
        if(hasSuperClass(clazz)){
            appendln("    if (!super.equals(obj)) {");
            appendln("        return false;");
            appendln("    }");
        }
        
        append(   "    ").append(clazz.getSimpleName()).append(" other = (").append(clazz.getSimpleName()).append(")obj;").append(NEW_LINE);
        StringBuilder inner = new StringBuilder();
        inner.append(getTab(1)).append("return ClassInstanceEqualer.getInstance(this, other)");
        Field[] fields = clazz.getDeclaredFields();
        int size = fields.length;
        Field field;
        for(int i = 0; i < size; i++) {
            field = fields[i];
            if(inner.length()  > CODE_LINE_MAX_LEN){
                appendln(inner.toString());
                inner.setLength(0);
                inner.append(getTab(3));
            }
            inner.append(".append(this.").append(field.getName()).append(", other.").append(field.getName()).append(")");
            if(i == size - 1){
                inner.append(".isEqual();");
                appendln(inner.toString());
            }
        }
        append("}");
        return builder.toString();
    }
    
    private StringBuilder append(String line){
        append(1, line);
        return this.builder;
    }
    
    private StringBuilder append(int tabSize, String line) {
        builder.append(getTab(tabSize)).append(line);
        return this.builder;
    }

    private void appendln(String line){
        appendln(1, line);
    }
    
    private void appendln(int tabSize, String line){
        builder.append(getTab(tabSize)).append(line).append(NEW_LINE);
    }
    
    private boolean hasSuperClass(Class<?> clazz){
        return !Object.class.equals(clazz.getSuperclass());
    }
    
}
