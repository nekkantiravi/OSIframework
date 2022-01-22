package test.automation.framework;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.*;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static test.automation.framework.Page.*;
import static test.automation.framework.Elements.*;
import static test.automation.framework.Actions.*;
import static test.automation.framework.Rest.response;
import static test.automation.framework.Rest.validatableResponse;
import static test.automation.framework.Runner.log;

public final class Steps {

    @Given("^I visit \"([^\"]*)\" page$")
    public void iVisitPage(String page) throws Throwable {
        visit(page);
    }

    @Then("^I should be on \"([^\"]*)\" page$")
    public void iShouldBeOnPage(String page) throws Throwable {
        onPage(page);
    }

    @Then("^I should see \"([^\"]*)\" (element|button|link|image|text box|dropdown|check box|radio button|form|text block|table)$")
    public void iShouldSeeElement(String element, String type) throws Throwable {
        Assert.assertTrue(element + " " + type + " not displayed on " + getCurrentPageName(), isDisplayed(element));
    }

    @Then("^I should see \"([^\"]*)\" (element|button|link|image|text box|dropdown|check box|radio button|form|text block|table) on \"([^\"]*)\" page$")
    public void iShouldSeeElementOnPage(String element, String type, String page) throws Throwable {
        iShouldBeOnPage(page);
        iShouldSeeElement(element, type);
    }

    @Then("^I should see following (elements|buttons|links|images|text blocks):$")
    public void iShouldSeeFollowingElements(String type, List<String> elements) throws Throwable {
        List<String> notDisplayed = elements.stream().filter(e -> !isDisplayed(e)).collect(Collectors.toList());
        Assert.assertEquals(type + " not displayed on " + getCurrentPageName() + ": " + notDisplayed, 0, notDisplayed.size());
    }

    @Then("^I should see following elements:$")
    public void iShouldSeeFollowingElements(List<String> elements) throws Throwable {
        List<String> displayed = elements.stream().filter(e -> isDisplayed(e)).collect(Collectors.toList());
        Assert.assertEquals("Elements not displayed on " + getCurrentPageName() + ": " + displayed, elements.size(), displayed.size());
        log().info("Elements are displayed successfully");
    }

    @Then("^I click on \"([^\"]*)\" element on \"([^\"]*)\" page$")
    public void iClickOnElementOnPage(String element, String page) throws Throwable {
        iShouldBeOnPage(page);
        iClickOnElement(element);
        log().info("Clicked on " + element + "successfully on " + page + "page");
    }

    @Then("^I click on \"([^\"]*)\" element$")
    public void iClickOnElement(String element) throws Throwable {
        click(element);
        log().info("Clicked on " + element + "successfully");
    }

    @Then("^I should see \"([^\"]*)\" panel$")
    public void iShouldSeePanel(String panel) throws Throwable {
        Assert.assertTrue(panel + " not displayed on " + getCurrentPageName(), isPanelDisplayed(panel));
    }

    @Then("^I should see following panels:$")
    public void iShouldSeeFollowingPanels(List<String> panels) throws Throwable {
        List<String> notDisplayed = panels.stream().filter(p -> !isPanelDisplayed(p)).collect(Collectors.toList());
        Assert.assertEquals("Panels not displayed on " + getCurrentPageName() + ": " + notDisplayed, 0, notDisplayed.size());
    }

    @Then("^I should see \"([^\"]*)\" panel on \"([^\"]*)\" page$")
    public void iShouldSeePanelOnPage(String panel, String page) throws Throwable {
        iShouldBeOnPage(page);
        iShouldSeePanel(panel);
    }

    @Then("^I should see following panels on \"([^\"]*)\" page:$")
    public void iShouldSeeFollowingPanelsOnPage(String page, List<String> panels) throws Throwable {
        iShouldBeOnPage(page);
        iShouldSeeFollowingPanels(panels);
    }

    @Then("^I should see \"([^\"]*)\" (element|button|link|image|text box|dropdown|check box|radio button|form|text block|table) on \"([^\"]*)\" panel$")
    public void iShouldSeeElementOnPanel(String element, String type, String panel) throws Throwable {
        Assert.assertTrue(element + " " + type + " not displayed on " + getCurrentPageName(), isDisplayed(element, panel));
    }

    @Then("^I should see \"([^\"]*)\" (element|button|link|image|text box|dropdown|check box|radio button|form|text block|table) on \"([^\"]*)\" panel on \"([^\"]*)\" page$")
    public void iShouldSeeElementOnPanelOnPage(String element, String type, String panel, String page) throws Throwable {
        iShouldBeOnPage(page);
        iShouldSeeElementOnPanel(element, type, panel);
    }

    @Then("^I should see following (elements|buttons|links|images|text blocks) on \"([^\"]*)\" panel:$")
    public void iShouldSeeFollowingElementsOnPanel(String type, String panel, List<String> elements) throws Throwable {
        List<String> notDisplayed = elements.stream().filter(e -> !isDisplayed(e, panel)).collect(Collectors.toList());
        Assert.assertEquals(type + " not displayed on " + getCurrentPageName() + ": " + elements, 0, notDisplayed.size());
    }

    @Then("^I should see following (elements|buttons|links|images|text blocks) on \"([^\"]*)\" panel on \"([^\"]*)\" page:$")
    public void iShouldSeeFollowingElementsOnPanelOnPage(String type, String panel, String page, List<String> elements) throws Throwable {
        iShouldBeOnPage(page);
        iShouldSeeFollowingElementsOnPanel(type, panel, elements);
    }

    @Then("^I click on \"([^\"]*)\" (element|button|link|image|text box|dropdown|check box|radio button|form|text block|table) on \"([^\"]*)\" panel$")
    public void iClickOnElementOnPanel(String element, String type, String panel) throws Throwable {
        click(element, panel);
    }

    @Then("^I click on \"([^\"]*)\" (element|button|link|image|text box|dropdown|check box|radio button|form|text block|table) on \"([^\"]*)\" panel on \"([^\"]*)\" page$")
    public void iClickOnElementOnPanelOnPage(String element, String type, String panel, String page) throws Throwable {
        iShouldBeOnPage(page);
        iClickOnElementOnPanel(element, type, panel);
    }

    @When("^I \"([^\"]*)\" on current page$")
    public void iInvokeMethodOnCurrentPage(String method) throws Throwable {
        performAction(method);
    }

    @When("^I \"([^\"]*)\" on \"([^\"]*)\" page$")
    public void iInvokeMethodOnPage(String method, String page) throws Throwable {
        iShouldBeOnPage(page);
        iInvokeMethodOnCurrentPage(method);
    }

    @When("^I \"([^\"]*)\" on \"([^\"]*)\" panel$")
    public void iInvokeMethodOnPanel(String method, String panel) throws Throwable {
        performPanelAction(method, panel);
    }

    @When("^I \"([^\"]*)\" on \"([^\"]*)\" panel on \"([^\"]*)\" page$")
    public void iInvokeMethodOnPanelOnPage(String method, String panel, String page) throws Throwable {
        iShouldBeOnPage(page);
        iInvokeMethodOnPanel(method, panel);
    }

    @When("^I \"([^\"]*)\" with \"([^\"]*)\" on current page$")
    public void iInvokeMethodWithArgOnCurrentPage(String method, String args) throws Throwable {
        performAction(method, (Object[]) args.split(", "));
    }

    @When("^I \"([^\"]*)\" with \"([^\"]*)\" on \"([^\"]*)\" page$")
    public void iInvokeMethodWithArgOnPage(String method, String args, String page) throws Throwable {
        iShouldBeOnPage(page);
        iInvokeMethodWithArgOnCurrentPage(method, args);
    }

    @When("^I \"([^\"]*)\" with \"([^\"]*)\" on \"([^\"]*)\" panel$")
    public void iInvokeMethodWithArgOnPanel(String method, String args, String panel) throws Throwable {
        performPanelAction(method, panel, (Object[]) args.split(", "));
    }

    @When("^I \"([^\"]*)\" with \"([^\"]*)\" on \"([^\"]*)\" panel on \"([^\"]*)\" page$")
    public void iInvokeMethodWithArgOnPanelOnPage(String method, String args, String panel, String page) throws Throwable {
        iShouldBeOnPage(page);
        iInvokeMethodWithArgOnPanel(method, panel, args);
    }

    @When("^I select random value in \"([^\"]*)\" dropdown$")
    public void iSelectRandomValueInDropdown(String element) throws Throwable {
        Select select = getSelect(element);
        Assert.assertTrue(element + " not displayed on " + getCurrentPageName(), select.exists() && select.isDisplayed());
        int optionsSize = select.getOptions().size();
        Assert.assertTrue(element + " is empty", optionsSize > 0);
        select.selectByIndex(new Random().nextInt(optionsSize));
    }

    @When("^I select random value in \"([^\"]*)\" dropdown on \"([^\"]*)\" page$")
    public void iSelectRandomValueInDropdownOnPage(String element, String page) throws Throwable {
        iShouldBeOnPage(page);
        iSelectRandomValueInDropdown(element);
    }

    @When("^I select random value in \"([^\"]*)\" dropdown on \"([^\"]*)\" panel$")
    public void iSelectRandomValueInDropdownOnPanel(String element, String panel) throws Throwable {
        Select select = getSelect(element, panel);
        Assert.assertTrue(element + " not displayed on " + panel + " panel", select.exists() && select.isDisplayed());
        int optionsSize = select.getOptions().size();
        Assert.assertTrue(element + " is empty", optionsSize > 0);
        select.selectByIndex(new Random().nextInt(optionsSize));
    }

    @When("^I select random value in \"([^\"]*)\" dropdown on \"([^\"]*)\" panel on \"([^\"]*)\" page$")
    public void iSelectRandomValueInDropdownOnPanelOnPage(String element, String panel, String page) throws Throwable {
        iShouldBeOnPage(page);
        iSelectRandomValueInDropdownOnPanel(element, panel);
    }

    @When("^I select \"([^\"]*)\" value in \"([^\"]*)\" dropdown$")
    public void iSelectValueInDropdown(String value, String element) throws Throwable {
        Select select = getSelect(element);
        Assert.assertTrue(element + " not displayed on " + getCurrentPageName(), select.exists() && select.isDisplayed());
        select.selectByValue(value);
    }

    @When("^I select \"([^\"]*)\" value in \"([^\"]*)\" dropdown on \"([^\"]*)\" page$")
    public void iSelectValueInDropdownOnPage(String value, String element, String page) throws Throwable {
        iShouldBeOnPage(page);
        iSelectValueInDropdown(value, element);
    }

    @When("^I select \"([^\"]*)\" value in \"([^\"]*)\" dropdown on \"([^\"]*)\" panel$")
    public void iSelectValueInDropdownOnPanel(String value, String element, String panel) throws Throwable {
        Select select = getSelect(element, panel);
        Assert.assertTrue(element + " not displayed on " + panel + " panel", select.exists() && select.isDisplayed());
        select.selectByValue(value);
    }

    @When("^I select \"([^\"]*)\" value in \"([^\"]*)\" dropdown on \"([^\"]*)\" panel on \"([^\"]*)\" page$")
    public void iSelectValueInDropdownOnPanelOnPage(String value, String element, String panel, String page) throws Throwable {
        iShouldBeOnPage(page);
        iSelectValueInDropdownOnPanel(value, element, panel);
    }

    @When("^I select \"([^\"]*)\" text in \"([^\"]*)\" dropdown$")
    public void iSelectTextInDropdown(String text, String element) throws Throwable {
        Select select = getSelect(element);
        Assert.assertTrue(element + " not displayed on " + getCurrentPageName(), select.exists() && select.isDisplayed());
        select.selectByVisibleText(text);
    }

    @When("^I select \"([^\"]*)\" text in \"([^\"]*)\" dropdown on \"([^\"]*)\" page$")
    public void iSelectTextInDropdownonPage(String text, String element, String page) throws Throwable {
        iShouldBeOnPage(page);
        iSelectTextInDropdown(text, element);
    }

    @When("^I select \"([^\"]*)\" text in \"([^\"]*)\" dropdown on \"([^\"]*)\" panel$")
    public void iSelectTextInDropdownOnPanel(String text, String element, String panel) throws Throwable {
        Select select = getSelect(element, panel);
        Assert.assertTrue(element + " not displayed on " + panel + " panel", select.exists() && select.isDisplayed());
        select.selectByVisibleText(text);
    }

    @When("^I select \"([^\"]*)\" text in \"([^\"]*)\" dropdown on \"([^\"]*)\" panel on \"([^\"]*)\" page$")
    public void iSelectTextInDropdownOnPanelOnPage(String text, String element, String panel, String page) throws Throwable {
        iShouldBeOnPage(page);
        iSelectTextInDropdownOnPanel(text, element, panel);
    }

    @When("^I select random \"([^\"]*)\" radio button$")
    public void iSelectRandomRadioButton(String element) throws Throwable {
        Radio radio = getRadio(element);
        Assert.assertTrue(element + " not displayed on " + getCurrentPageName(), radio.exists() && radio.isDisplayed());
        int optionsSize = radio.getButtons().size();
        Assert.assertTrue(element + " is empty", optionsSize > 0);
        radio.selectByIndex(new Random().nextInt(optionsSize));
    }

    @When("^I select random \"([^\"]*)\" radio button on \"([^\"]*)\" page$")
    public void iSelectRandomRadioButtonOnPage(String element, String page) throws Throwable {
        iShouldBeOnPage(page);
        iSelectRandomRadioButton(element);
    }

    @When("^I select random \"([^\"]*)\" radio button on \"([^\"]*)\" panel$")
    public void iSelectRandomRadioButtonOnPanel(String element, String panel) throws Throwable {
        Radio radio = getRadio(element, panel);
        Assert.assertTrue(element + " not displayed on " + panel + " panel", radio.exists() && radio.isDisplayed());
        int optionsSize = radio.getButtons().size();
        Assert.assertTrue(element + " is empty", optionsSize > 0);
        radio.selectByIndex(new Random().nextInt(optionsSize));
    }

    @When("^I select random \"([^\"]*)\" radio button on \"([^\"]*)\" panel \"([^\"]*)\" on page$")
    public void iSelectRandomRadioButtonOnPanelOnPage(String element, String panel, String page) throws Throwable {
        iShouldBeOnPage(page);
        iSelectRandomRadioButtonOnPanel(element, panel);
    }

    @When("^I select \"([^\"]*)\" in \"([^\"]*)\" radio buttons$")
    public void iSelectInRadioButtons(String value, String element) throws Throwable {
        Radio radio = getRadio(element);
        Assert.assertTrue(element + " not displayed on " + getCurrentPageName(), radio.exists() && radio.isDisplayed());
        radio.selectByValue(value);
    }

    @When("^I select \"([^\"]*)\" in \"([^\"]*)\" radio buttons on \"([^\"]*)\" page$")
    public void iSelectInRadioButtonsOnPage(String value, String element, String page) throws Throwable {
        iShouldBeOnPage(page);
        iSelectInRadioButtons(value, element);
    }

    @When("^I select \"([^\"]*)\" in \"([^\"]*)\" radio buttons on \"([^\"]*)\" panel$")
    public void iSelectInRadioButtonsOnPanel(String value, String element, String panel) throws Throwable {
        Radio radio = getRadio(element, panel);
        Assert.assertTrue(element + " not displayed on " + panel + " panel", radio.exists() && radio.isDisplayed());
        radio.selectByValue(value);
    }

    @When("^I select \"([^\"]*)\" in \"([^\"]*)\" radio buttons on \"([^\"]*)\" panel on \"([^\"]*)\" page$")
    public void iSelectInRadioButtonsOnPanel(String value, String element, String panel, String page) throws Throwable {
        iShouldBeOnPage(page);
        iSelectInRadioButtonsOnPanel(value, element, panel);
    }

    @When("^I select \"([^\"]*)\" check box$")
    public void iSelectCheckBox(String element) throws Throwable {
        CheckBox checkBox = getCheckBox(element);
        Assert.assertTrue(element + " check box is not displayed on " + getCurrentPageName(), checkBox.exists() && checkBox.isDisplayed());
        checkBox.select();
    }

    @When("^I select \"([^\"]*)\" check box on \"([^\"]*)\" page$")
    public void iSelectCheckBoxOnPage(String element, String page) throws Throwable {
        iShouldBeOnPage(page);
        iSelectCheckBox(element);
    }

    @When("^I select \"([^\"]*)\" check box on \"([^\"]*)\" panel$")
    public void iSelectCheckBoxOnPanel(String element, String panel) throws Throwable {
        CheckBox checkBox = getCheckBox(element, panel);
        Assert.assertTrue(element + " check box is not displayed on " + panel + " panel", checkBox.exists() && checkBox.isDisplayed());
        checkBox.select();
    }

    @When("^I select \"([^\"]*)\" check box on \"([^\"]*)\" panel on \"([^\"]*)\" Page$")
    public void iSelectCheckBoxOnPanelOnPage(String element, String panel, String page) throws Throwable {
        iShouldBeOnPage(page);
        iSelectCheckBoxOnPanel(element, panel);
    }

    @When("^I type \"([^\"]*)\" in \"([^\"]*)\" text box$")
    public void iTypeInTextBox(String text, String element) throws Throwable {
        TextInput textInput = getTextInput(element);
        Assert.assertTrue(element + " text box is not displayed on " + getCurrentPageName(), textInput.exists() && textInput.isDisplayed());
        textInput.sendKeys(text);
    }

    @When("^I type \"([^\"]*)\" in \"([^\"]*)\" text box on \"([^\"]*)\" page$")
    public void iTypeInTextBoxOnPage(String text, String element, String page) throws Throwable {
        iShouldBeOnPage(page);
        iTypeInTextBox(text, element);
    }

    @When("^I type \"([^\"]*)\" in \"([^\"]*)\" text box on \"([^\"]*)\" panel$")
    public void iTypeInTextBoxOnPanel(String text, String element, String panel) throws Throwable {
        TextInput textInput = getTextInput(element, panel);
        Assert.assertTrue(element + " text box is not displayed on " + panel + " panel", textInput.exists() && textInput.isDisplayed());
        textInput.sendKeys(text);
    }

    @When("^I type \"([^\"]*)\" in \"([^\"]*)\" text box on \"([^\"]*)\" panel on \"([^\"]*)\" page$")
    public void iTypeInTextBoxOnPanelOnPage(String text, String element, String panel, String page) throws Throwable {
        iShouldBeOnPage(page);
        iTypeInTextBoxOnPanel(text, element, panel);
    }

    @Then("^I should see \"([^\"]*)\" in \"([^\"]*)\" (element|text block)$")
    public void iShouldSeeInElement(String text, String element, String type) throws Throwable {
        TypifiedElement elementO = getElement(element);
        Assert.assertTrue(element + " " + type + " not displayed on " + getCurrentPageName(), elementO.exists() && elementO.isDisplayed());
        String elementText = elementO.getText();
        Assert.assertTrue(text + " is not displayed in " + element + " " + type + " ( " + elementText + " )", elementText.contains(text));
    }

    @Then("^I should see \"([^\"]*)\" in \"([^\"]*)\" (element|text block) on \"([^\"]*)\" page$")
    public void iShouldSeeInElementOnPage(String text, String element, String type, String page) throws Throwable {
        iShouldBeOnPage(page);
        iShouldSeeInElement(text, element, type);
    }

    @Then("^I should see \"([^\"]*)\" in \"([^\"]*)\" (element|text block) on \"([^\"]*)\" panel$")
    public void iShouldSeeInElementOnPanel(String text, String element, String type, String panel) throws Throwable {
        TypifiedElement elementO = getElement(element, panel);
        Assert.assertTrue(element + " " + type + " not displayed on " + panel + " panel", elementO.exists() && elementO.isDisplayed());
        String elementText = elementO.getText();
        Assert.assertTrue(text + " is not displayed in " + element + " " + type + " ( " + elementText + " )", elementText.contains(text));
    }

    @Then("^I should see \"([^\"]*)\" in \"([^\"]*)\" (element|text block) on \"([^\"]*)\" panel on \"([^\"]*)\" page$")
    public void iShouldSeeInElementOnPanelOnPage(String text, String element, String type, String panel, String page) throws Throwable {
        iShouldBeOnPage(page);
        iShouldSeeInElementOnPanel(text, element, type, panel);
    }

    @Then("^I should see \"([^\"]*)\" in \"([^\"]*)\" list$")
    public void iShouldSeeInList(String text, String element) throws Throwable {
        Assert.assertTrue(text + " is not displayed in " + element, getWebElements(element).stream().anyMatch(e -> e.getText().contains(text)));
    }

    @Then("^I should see \"([^\"]*)\" in \"([^\"]*)\" list on \"([^\"]*)\" page$")
    public void iShouldSeeInListOnPage(String text, String element, String page) throws Throwable {
        iShouldBeOnPage(page);
        iShouldSeeInList(text, element);
    }

    @Then("^I should see \"([^\"]*)\" in \"([^\"]*)\" list on \"([^\"]*)\" panel$")
    public void iShouldSeeInListOnPanel(String text, String element, String panel) throws Throwable {
        Assert.assertTrue(text + " is not displayed in " + element, getWebElements(element, panel).stream().anyMatch(e -> e.getText().contains(text)));
    }

    @Then("^I should see \"([^\"]*)\" in \"([^\"]*)\" list on \"([^\"]*)\" panel on \"([^\"]*)\" page$")
    public void iShouldSeeInListOnPanel(String text, String element, String panel, String page) throws Throwable {
        iShouldBeOnPage(page);
        iShouldSeeInListOnPanel(text, element, panel);
    }

    @Then("^I should see \"([^\"]*)\" is enabled$")
    public void iShouldSeeIsEnabled(String element) throws Throwable {
        TypifiedElement elementO = getElement(element);
        Assert.assertTrue(element + " not displayed on " + getCurrentPageName(), elementO.exists() && elementO.isDisplayed());
        Assert.assertTrue(element + " is not enabled on " + getCurrentPageName(), elementO.isEnabled());
    }

    @Then("^I should see \"([^\"]*)\" is enabled on \"([^\"]*)\" page$")
    public void iShouldSeeIsEnabledOnPage(String element, String page) throws Throwable {
        iShouldBeOnPage(page);
        iShouldSeeIsEnabled(element);
    }

    @Then("^I should see \"([^\"]*)\" is enabled on \"([^\"]*)\" panel$")
    public void iShouldSeeIsEnabledOnPanel(String element, String panel) throws Throwable {
        TypifiedElement elementO = getElement(element, panel);
        Assert.assertTrue(element + " not displayed on " + panel + " panel", elementO.exists() && elementO.isDisplayed());
        Assert.assertTrue(element + " is not enabled on " + panel + " panel", elementO.isEnabled());
    }

    @Then("^I should see \"([^\"]*)\" is enabled on \"([^\"]*)\" panel on \"([^\"]*)\" page$")
    public void iShouldSeeIsEnabledOnPanelOnPage(String element, String panel, String page) throws Throwable {
        iShouldBeOnPage(page);
        iShouldSeeIsEnabledOnPanel(element, panel);
    }

    @Then("^I should see following in \"([^\"]*)\" list:$")
    public void iShouldSeeFollowingInList(String element, List<String> texts) throws Throwable {
        List<String> displayed = getWebElements(element).stream().map(WebElement::getText).collect(Collectors.toList());
        List<String> notDisplayed = texts.stream().filter(text -> displayed.stream().noneMatch(d -> d.contains(text))).collect(Collectors.toList());
        Assert.assertEquals("Not displayed in " + element + " on " + getCurrentPageName() + ": " + notDisplayed, 0, notDisplayed.size());
    }

    @Then("^I should see following in \"([^\"]*)\" list on \"([^\"]*)\" page:$")
    public void iShouldSeeFollowingInListOnPage(String element, String page, List<String> texts) throws Throwable {
        iShouldBeOnPage(page);
        iShouldSeeFollowingInList(element, texts);
    }

    @Then("^I should see following in \"([^\"]*)\" list on \"([^\"]*)\" panel:$")
    public void iShouldSeeFollowingInListOnPanel(String element, String panel, List<String> texts) throws Throwable {
        List<String> displayed = getWebElements(element, panel).stream().map(WebElement::getText).collect(Collectors.toList());
        List<String> notDisplayed = texts.stream().filter(text -> displayed.stream().noneMatch(d -> d.contains(text))).collect(Collectors.toList());
        Assert.assertEquals("Not displayed in " + element + " on " + panel + " panel: " + notDisplayed, 0, notDisplayed.size());
    }

    @Then("^I should see following in \"([^\"]*)\" list on \"([^\"]*)\" panel on \"([^\"]*)\" page:$")
    public void iShouldSeeFollowingInListOnPanelOnPage(String element, String panel, String page, List<String> texts) throws Throwable {
        iShouldBeOnPage(page);
        iShouldSeeFollowingInListOnPanel(element, panel, texts);
    }

    @When("^I fill \"([^\"]*)\" form with following:$")
    public void iFillFormWithFollowing(String element, Map<String, Object> data) throws Throwable {
        getForm(element).fill(data);
    }

    @When("^I fill \"([^\"]*)\" form on \"([^\"]*)\" page with following:$")
    public void iFillFormWithFollowingOnPage(String element, String page, Map<String, Object> data) throws Throwable {
        iShouldBeOnPage(page);
        iFillFormWithFollowing(element, data);
    }

    @When("^I fill \"([^\"]*)\" form on \"([^\"]*)\" panel with following:$")
    public void iFillFormWithFollowingOnPanel(String element, String panel, Map<String, Object> data) throws Throwable {
        getForm(element, panel).fill(data);
    }

    @When("^I fill \"([^\"]*)\" form on \"([^\"]*)\" panel on \"([^\"]*)\" page with following:$")
    public void iFillFormWithFollowingOnPanelOnPage(String element, String panel, String page, Map<String, Object> data) throws Throwable {
        iShouldBeOnPage(page);
        iFillFormWithFollowingOnPanel(element, panel, data);
    }

    @Then("^the status code is (\\d+)$")
    public void theStatusCodeIs(int statusCode) {
        validatableResponse = response.then().statusCode(statusCode);
    }

    @And("^response includes the following:$")
    public void responseIncludeTheFollowing(Map<String, String> responseFields) {
        for (Map.Entry<String, String> field : responseFields.entrySet()) {
            if (StringUtils.isNumeric(field.getValue())) {
                validatableResponse.body(field.getKey(), equalTo(Integer.parseInt(field.getValue())));
            } else {
                validatableResponse.body(field.getKey(), equalTo(field.getValue()));
            }
        }
    }

    @And("^response includes the following in any order:$")
    public void responseIncludeTheFollowingInAnyOrder(Map<String, String> responseFields) {
        for (Map.Entry<String, String> field : responseFields.entrySet()) {
            if (StringUtils.isNumeric(field.getValue())) {
                validatableResponse.body(field.getKey(), containsInAnyOrder(Integer.parseInt(field.getValue())));
            } else {
                validatableResponse.body(field.getKey(), containsInAnyOrder(field.getValue()));
            }
        }
    }


}
