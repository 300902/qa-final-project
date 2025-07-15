package api;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

@Suite
@SelectClasspathResource("features/api")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "api")
public class RunCucumberTest {
}
