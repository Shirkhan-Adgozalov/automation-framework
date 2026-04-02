package com.shirkhan.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.shirkhan.stepDefinitions", "com.shirkhan.base"},
        plugin = {
                "pretty",
                "html:target/cucumber-report.html",
                "json:target/cucumber.json"
        },
        tags = "@saucedemo or @smoke or @regression",
        monochrome = true,
        dryRun = false
)

public class TestRunner extends AbstractTestNGCucumberTests {

}