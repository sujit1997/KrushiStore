package StepDefinitions;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/Features",
        glue = {"StepDefinitions", "hooks"},
        monochrome = true,
        plugin = {"pretty", "html:target/cucumber-reports.html",
                "json:target/JSONReports/json-reports.json",
                "junit:target/JUnitReports/junit-reports.xml"
        }
        //tags = "@smoketest"
)
public class TestRunner {
}
