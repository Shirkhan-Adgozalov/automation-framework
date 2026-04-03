package com.shirkhan.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.shirkhan.stepDefinitions", "com.shirkhan.base"},
        plugin = {
                "pretty",
                "html:target/cucumber-report.html",
                "json:target/cucumber.json",
                "junit:target/surefire-reports/TEST-cucumber.xml"
        },
        tags = "@saucedemo or @smoke or @regression",
        monochrome = true,
        dryRun = false
)

public class TestRunner extends AbstractTestNGCucumberTests {

    /**
     * DataProvider disabled - using TestNG suite XML for thread pool management.
     * TestNG suite XML controls parallel execution with max 3 browser instances.
     */
    @DataProvider(parallel = false)
    @Override
    public Object[][] scenarios() {
        return super.scenarios();
    }

}