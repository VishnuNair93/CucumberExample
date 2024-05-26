package org.example;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)

@CucumberOptions(
        monochrome = false,
        features = "src/test/java/resources/TC02_AmazonSearch.feature",
        glue = {"stepDefinition"},
        plugin = {"pretty", "html:test-output",
                "json:target/cucumber-report/cucumber.json"}
)


public class TestRunner {
}
