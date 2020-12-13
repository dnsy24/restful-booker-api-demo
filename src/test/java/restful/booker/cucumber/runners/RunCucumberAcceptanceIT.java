package restful.booker.cucumber.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"html:target/cucumber-report.html",
                "json:target/cucumber.json",
                "rerun:target/rerun.txt"},
        features = "src/main/resources/features/restful-booker-post.feature",
        glue = "restful/booker/cucumber/step/definitions"
)
public class RunCucumberAcceptanceIT {
}
