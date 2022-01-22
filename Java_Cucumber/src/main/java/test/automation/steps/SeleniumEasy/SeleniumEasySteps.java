package test.automation.steps.SeleniumEasy;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import test.automation.pages.SeleniumEasy.BootstrapModalPage;
import test.automation.pages.SeleniumEasy.DynamicData;
import test.automation.pages.SeleniumEasy.Home;
import test.automation.pages.SeleniumEasy.InputForm;
import test.automation.utils.UserUtils;

import static test.automation.framework.Actions.*;
import static test.automation.framework.Page.*;

public class SeleniumEasySteps {

    @Given("^I visit Selenium Easy Home page$")
    public void iVisitSeleniumEasyHomePage() throws Throwable {
        visit(Home.class);
    }

    @When("^I navigate to Bootstrap Modal page$")
    public void iNavigateToBootstrapModalPage() throws Throwable {
        Home homePage = (Home) getCurrentPage();
        homePage.navigationBar.selectMenu("Alerts & Modals", "Bootstrap Modals");
        onPage(BootstrapModalPage.class);
    }

    @And("^I click on Modal Launch Button$")
    public void iClickOnModalLaunchButton() throws Throwable {
        BootstrapModalPage bootstrapModalPage = (BootstrapModalPage) getCurrentPage();
        bootstrapModalPage.clickLaunchModalBtn();
    }

    @Then("^I should see Bootstrap Modal$")
    public void iShouldSeeBootstrapModal() throws Throwable {
        BootstrapModalPage bootstrapModalPage = (BootstrapModalPage) getCurrentPage();
        Assert.assertTrue("Error:: Modal Content not displayed!", bootstrapModalPage.isModalContentDisplayed());
    }

    @When("^I fill the selenium easy input form$")
    public void iFillTheSeleniumEasyInputForm() throws Throwable {
        InputForm inputFormPage = (InputForm) getCurrentPage();
        inputFormPage.fillInputForm(UserUtils.getUserMap());
        performAction("fillInputForm", UserUtils.getUser());
    }

    @Then("^I should see user image is loaded$")
    public void iShouldSeeUserImageIsLoaded() throws Throwable {
        DynamicData dynamicDataPage = (DynamicData) getCurrentPage();
        waitUntilElementPresent(dynamicDataPage.userImage);
    }
}
