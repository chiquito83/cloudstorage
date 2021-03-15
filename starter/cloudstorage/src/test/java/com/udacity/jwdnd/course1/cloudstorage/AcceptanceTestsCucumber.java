package com.udacity.jwdnd.course1.cloudstorage;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Cucumber.class)
@CucumberOptions(
        extraGlue = "MyStepdefs",
        features = "src/test/resources/features"
)
public class AcceptanceTestsCucumber {
}
