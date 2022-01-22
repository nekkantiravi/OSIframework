package test.automation.pages.SeleniumEasy;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.*;
import test.automation.framework.Config;
import test.automation.framework.Page;
import test.automation.models.User;
import test.automation.panels.SeleniumEasy.NavigationBar;

import java.util.Map;

public class InputForm extends Page {

    public static final String URL = Config.getUrl() + "/test/input-form-demo.html";
    public static final By VERIFY_BY = By.id("contact_form");

    public NavigationBar navigationBar;

    @FindBy(id = "contact_form")
    public Form input;

    @FindBy(name = "first_name")
    public TextInput firstName;

    @FindBy(name = "last_name")
    public TextInput lastName;

    @FindBy(name = "email")
    public TextInput email;

    @FindBy(name = "phone")
    public TextInput phone;

    @FindBy(name = "address")
    public TextInput address;

    @FindBy(name = "city")
    public TextInput city;

    @FindBy(name = "state")
    public Select state;

    @FindBy(name = "zip")
    public TextInput zipCode;

    @FindBy(name = "website")
    public TextInput website;

    @FindBy(name = "hosting")
    public Radio haveHosting;

    @FindBy(name = "comment")
    public TextInput projectDescription;

    @FindBy(xpath = "//button[contains(text(), 'Send')]")
    public Button sendButton;

    public void fillInputForm(Map<String, Object> data) {
        String state = (String) data.remove("state");
        input.fill(data);
        this.state.selectByVisibleText(state);
    }

    public void fillInputForm(User user) {
        firstName.sendKeys(user.getFirstName());
        lastName.sendKeys(user.getLastName());
        email.sendKeys(user.getEmail());
        phone.sendKeys(user.getPhone());
        address.sendKeys(user.getAddress());
        city.sendKeys(user.getCity());
        state.selectByVisibleText(user.getState());
        zipCode.sendKeys(user.getZipCode());
        website.sendKeys(user.getWebsite());
        haveHosting.selectByValue(user.getHaveHosting());
        projectDescription.sendKeys(user.getProjectDescription());
    }
}
