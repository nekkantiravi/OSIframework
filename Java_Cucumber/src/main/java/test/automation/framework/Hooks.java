package test.automation.framework;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

import java.util.Collection;

import static test.automation.framework.Runner.log;

public final class Hooks {

    @Before
    public void beforeScenario(Scenario scenario) {
        log().info("Scenario Started: " + scenario.getName() );
    }

    @After
    public void afterScenario(Scenario scenario) {
        Collection<String> tags = scenario.getSourceTagNames();
        if (Browser.isStarted()) {
            if (scenario.isFailed() || tags.contains("@Screenshot")) {
                scenario.embed(Browser.getScreenShot(), "image/png");
            }
            if (!(Config.isKeepBrowser() || tags.contains("@KeepBrowser"))) {
                Browser.quit();
            }
        }
        DB.closeConnection();
        log().info("Scenario completed: " + scenario.getName());
        log().info("Scenario status: " + scenario.getStatus());
    }
}
