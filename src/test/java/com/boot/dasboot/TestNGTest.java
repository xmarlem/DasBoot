package com.boot.dasboot;


import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;

@SpringBootTest(classes = TestNGTest.class)
public class TestNGTest extends AbstractTestNGSpringContextTests{
    @Test
    public void simpleTest(){
        Assert.assertEquals(2*2, 4, "2*2 should be 4" );
    }
}

