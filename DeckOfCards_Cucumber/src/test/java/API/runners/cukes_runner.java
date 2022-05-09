package API.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin ={"pretty",
                "json:target/cucumber.json",
                "html:target/cucumber-report.html"
        },
        features = "src/test/resources/features",
        glue = "API/step_definitions",
        dryRun = false,
        tags = "@all"
)
public class cukes_runner {
}
