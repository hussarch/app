package com.hussar.sm.cg.common;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * @author yi.xiao
 *
 */
public class LocationLineFilterTest {
 
    private LocationLineFilter filter;
    
    @Before
    public void setUp() throws Exception {
        filter = new LocationLineFilter(new String[]{""});
    }

    @Test
    public void test() {
        assertEquals(true, filter.match("public void setName{", "p.*[(][)]{"));
    }
    
    @Test
    public void test_import_location(){
        
        
        
    }
    
    private List<String> getSimpleClasContentList(){
        List<String> list = new ArrayList<String>();
        list.add("package com.hussar.sm.cg.core;");
        list.add("public class MooVO {");
        return list;
    }
}
