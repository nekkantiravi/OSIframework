package test.automation.steps.SeleniumEasy;

import cucumber.api.java.en.Then;
import org.junit.Assert;

public class ReportDemo {

    private static int failCount = 0;

    @Then("^I will fail for the first time$")
    public void iWillFailForTheFirstTime() throws Throwable {
        Assert.assertTrue("Fail for the first time!",failCount++ > 0);
    }

    @Then("^I will always fail$")
    public void iWillAlwaysFail() throws Throwable {
        Assert.assertTrue("Always fail!",failCount < 0);
    }
}
