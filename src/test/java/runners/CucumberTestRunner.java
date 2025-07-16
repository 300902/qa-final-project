package runners;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.FILTER_TAGS_PROPERTY_NAME;

import org.junit.platform.suite.api.*;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "api,web")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty,html:build/reports/cucumber.html")
@ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME, value = "${cucumber.filter.tags:@api or @web}")
public class CucumberTestRunner {
    // This class should be empty, it's just a runner
}
