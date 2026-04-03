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
     * DataProvider with parallel=true for faster test execution.
     * Each scenario runs in parallel with its own browser instance.
     * Note: Ensure sufficient system resources for parallel execution.
     */
    @DataProvider(parallel = true)
    @Override
    public Object[][] scenarios() {
        return super.scenarios();
    }

}