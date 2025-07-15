package com.qa.test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Sample test class to verify JUnit setup
 */
public class SampleTest {
    
    @Test
    public void testSetup() {
        assertTrue(true, "Sample test should pass");
    }
    
    @Test
    public void testAddition() {
        int result = 2 + 2;
        assertEquals(4, result, "2 + 2 should equal 4");
    }
}
