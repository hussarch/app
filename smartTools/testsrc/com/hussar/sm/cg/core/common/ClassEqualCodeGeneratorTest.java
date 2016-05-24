package com.hussar.sm.cg.core.common;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


/**
 * @author yi.xiao
 *
 */
public class ClassEqualCodeGeneratorTest {

    private ClassInstanceEqualCodeGenerator genetator;
    
    @Before
    public void setUp() throws Exception {
        this.genetator = new ClassInstanceEqualCodeGenerator();
    }
    
    @Test
    public void test_createObject(){
        assertEquals(null, this.genetator.create(Object.class));
    }
    
    @Test
    public void test_create_EwtEntity(){
        assertEquals(getEwtEntityEquals(), this.genetator.create(EwtEntity.class));
    }
    
    private String getEwtEntityEquals(){
        return  "    @Override\n" + 
                "    public boolean equals(Object obj) {\n" + 
                "        if (this == obj) {\n" + 
                "            return true;\n" + 
                "        }\n" + 
                "        if (obj == null) {\n" + 
                "            return false;\n" + 
                "        }\n" + 
                "        EwtEntity other = (EwtEntity)obj;\n" + 
                "        return ClassInstanceEqualer.getInstance(this, other).append(this.size, other.size).append(this.name, other.name)\n" + 
                "                .append(this.array, other.array).isEqual();\n" + 
                "    }";
    }
    
    @Test
    public void test_create_SubEwtEntity(){
        assertEquals(getSubEwtEntityEquals(), this.genetator.create(SubEwtEntity.class));
    }
    
    private String getSubEwtEntityEquals(){
        return  "    @Override\n" + 
                "    public boolean equals(Object obj) {\n" + 
                "        if (this == obj) {\n" + 
                "            return true;\n" + 
                "        }\n" + 
                "        if (obj == null) {\n" + 
                "            return false;\n" + 
                "        }\n" +
                "        if (!super.equals(obj)) {\n" +
                "            return false;\n" + 
                "        }\n" + 
                "        SubEwtEntity other = (SubEwtEntity)obj;\n" + 
                "        return ClassInstanceEqualer.getInstance(this, other).append(this.name, other.name).append(this.account, other.account).isEqual();\n" + 
                "    }";
    }
    
    
}
