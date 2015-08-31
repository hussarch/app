package com.hussar.sm;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.hussar.sm.cg.ConstructorGeneratorTest;
import com.hussar.sm.cg.common.LocationLineFilterTest;
import com.hussar.sm.cg.core.AppendConstructorWriterTest;
import com.hussar.sm.cg.core.common.ClassEqualCodeGeneratorTest;
import com.hussar.sm.cg.core.common.ClassEqualerTest;
import com.hussar.sm.cg.core.pair.PairClassGetterTest;
import com.hussar.sm.cg.templete.FreeMarkerCodeGeneratorTest;
import com.hussar.sm.cg.templete.LocalTempletReaderTest;

@RunWith(Suite.class)
@SuiteClasses({
    LocationLineFilterTest.class,
    ClassEqualCodeGeneratorTest.class,
    ClassEqualerTest.class,
    PairClassGetterTest.class,
    AppendConstructorWriterTest.class,
    FreeMarkerCodeGeneratorTest.class,
    LocalTempletReaderTest.class,
    ConstructorGeneratorTest.class
})
public class AllTests {

}
