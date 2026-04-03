package com.shirkhan.runner;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.*;

@Suite
@SelectClasspathResource("features")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.shirkhan.stepDefinitions, com.shirkhan.base")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty, html:target/cucumber-report.html, json:target/cucumber.json, junit:target/surefire-reports/TEST-cucumber.xml")
@ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME, value = "@saucedemo or @smoke or @regression")
@ConfigurationParameter(key = EXECUTION_DRY_RUN_PROPERTY_NAME, value = "false")
public class TestRunner {
    // JUnit 5 Cucumber Suite Runner with parallel execution support
    // Parallelism controlled via junit-platform.properties
}