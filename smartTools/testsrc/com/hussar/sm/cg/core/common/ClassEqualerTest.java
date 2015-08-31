package com.hussar.sm.cg.core.common;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author yi.xiao
 *
 */
public class ClassEqualerTest {

    private ClassInstanceEqualer equaler;
    
    @Before
    public void setUp() throws Exception {
        equaler = ClassInstanceEqualer.getInstance("", "2");
    }

    @Test
    public void test_append() {
        equaler.append(1, 2).append("asd", new String("asd"));
        assertFalse(equaler.isEqual());
    }

}
