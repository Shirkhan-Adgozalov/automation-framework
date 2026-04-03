package com.shirkhan.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
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

public class CucumberTestRunner {
    // JUnit 4 style Cucumber runner for parallel execution
}
