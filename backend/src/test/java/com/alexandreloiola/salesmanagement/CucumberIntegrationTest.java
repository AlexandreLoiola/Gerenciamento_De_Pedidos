package com.alexandreloiola.salesmanagement;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/feature",
        glue = "src/test/java/com/alexandreloiola/salesmanagement")
public class CucumberIntegrationTest {
}
