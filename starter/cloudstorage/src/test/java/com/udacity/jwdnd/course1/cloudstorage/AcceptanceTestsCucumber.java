package com.udacity.jwdnd.course1.cloudstorage;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        extraGlue = "MyStepdefs",
        features = "src/test/resources/features",
        publish = true,
        plugin = {"pretty"}
)
public class AcceptanceTestsCucumber {
}
