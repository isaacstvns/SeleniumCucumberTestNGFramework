package Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/Cucumber", glue = "StepDefinition", monochrome = true, tags = "@Smoke", plugin = {
		"html:target/cucumber.html" })
public class TestNGRunner extends AbstractTestNGCucumberTests {

}
