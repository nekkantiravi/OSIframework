package test.automation.steps.OpenCart;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import test.automation.models.User;
import test.automation.utils.UserUtils;

import static test.automation.framework.Actions.*;
import static test.automation.framework.Page.onPage;
import static test.automation.framework.Page.verifyPage;
import static test.automation.framework.Page.visit;
import static test.automation.framework.Random.getRandomString;

public class AccountSteps {

    @And("^I navigate to (my|create|edit) account page$")
    public void iNavigateToCustomerRegistrationPage(String page) throws Throwable {
        click("myAccount", "topNav");
        performPanelAction("select", "topNav", page.equals("create") ? "Register" : "My Account");
        onPage("OpenCart " + (page.equals("create") ? "Create" : "My") + "Account");
        if (page.equals("edit")) {
            click("editAccount");
            onPage("OpenCart EditAccount");
        }
    }

    @When("^I submit registration form with valid details$")
    public void iSubmitRegistrationFormWithValidDetails() throws Throwable {
        performAction("createAccount", UserUtils.getNewOCUser());
    }

    @Then("^my open cart account should be created$")
    public void myOpenCartAccountShouldBeCreated() throws Throwable {
        verifyPage("OpenCart AccountSuccess");
        Assert.assertNotNull("User not created in DB",
                UserUtils.getOCUserFromDB(UserUtils.getOCUser().getEmail()).getEmail());
    }

    @Given("^I visit OpenCart as a registered user$")
    public void iVisitOpenCartAsARegisteredUser() throws Throwable {
        User user = UserUtils.createNewOCUserAccount();
        visit("OpenCart Home");
        click("myAccount", "topNav");
        performPanelAction("select", "topNav","LoginSteps");
        onPage("OpenCart AccountLogin");
        performAction("login", user.getEmail(), user.getPassword());
        onPage("OpenCart MyAccount");
        visit("OpenCart Home");
    }

    @When("^I update my account details$")
    public void iUpdateMyAccountDetails() throws Throwable {
        User user = UserUtils.getOCUser();
        user.setFirstName(getRandomString(7));
        user.setLastName(getRandomString(7));
        clear("firstName");
        sendKeys("firstName", user.getFirstName());
        clear("lastName");
        sendKeys("lastName", user.getLastName());
        click("continueBtn");
    }

    @Then("^my open cart account should be updated$")
    public void myOpenCartAccountShouldBeUpdated() throws Throwable {
        onPage("OpenCart MyAccount");
        Assert.assertEquals("Account not Updated!", "Success: Your account has been successfully updated.",
                getText("successAlert"));
        User eUser = UserUtils.getOCUser();
        User aUser = UserUtils.getOCUserFromDB(eUser.getEmail());
        Assert.assertEquals("First name not updated in DB", eUser.getFirstName(), aUser.getFirstName());
        Assert.assertEquals("Last name not updated in DB", eUser.getLastName(), aUser.getLastName());
    }
}
