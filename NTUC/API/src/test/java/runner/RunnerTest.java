package runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features={"src/test/java/features/NTUCAssignment.feature"},
glue= {"stepDefinitions"},
tags = "@Task1 or @Task2",
//tags = "@Task2",

stepNotifications = true,

plugin = { "pretty", "json:target/cucumber-reports/Cucumber.json",
		 "junit:target/cucumber-reports/Cucumber.xml",
		 "html:target/cucumber-reports",
		 "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
		 monochrome = true
)


public class RunnerTest {
}
