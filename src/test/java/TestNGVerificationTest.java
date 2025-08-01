import org.testng.annotations.Test;
import org.testng.annotations.Listeners;
import org.testng.Assert;

/**
 * TestNG specific verification test
 */
@Listeners({org.testng.reporters.TestHTMLReporter.class})
public class TestNGVerificationTest {
    
    @Test
    public void testTestNGFramework() {
        Assert.assertTrue(true, "TestNG is working correctly");
    }
    
    @Test
    public void testTestNGAssertions() {
        Assert.assertEquals("Hello", "Hello", "String equality check");
        Assert.assertNotNull(this, "Object should not be null");
    }
}
