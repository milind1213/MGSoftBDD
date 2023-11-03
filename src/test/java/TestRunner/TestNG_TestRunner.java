package TestRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions( features = { "src/test/resources/Features" }, 
                  glue = { "StepDefinitions","AppHooks" }, 
                  plugin = { "pretty", "html:reports/Cucumber-Report.html",
                		    "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" }, 
                  dryRun = false, 
                  monochrome = true)
public class TestNG_TestRunner extends AbstractTestNGCucumberTests {

}
