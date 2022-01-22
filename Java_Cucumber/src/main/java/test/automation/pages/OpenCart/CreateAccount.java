package test.automation.pages.OpenCart;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.CheckBox;
import ru.yandex.qatools.htmlelements.element.Radio;
import ru.yandex.qatools.htmlelements.element.TextInput;
import test.automation.framework.Config;
import test.automation.framework.Page;
import test.automation.models.User;
import test.automation.panels.OpenCart.TopNav;

public class CreateAccount extends Page {

    public static final String URL = Config.getUrl() + "/index.php\\?route=account/register";
    public static final By VERIFY_BY = By.id("account-register");

    public TopNav topNav;

    @FindBy(name = "firstname")
    TextInput firstName;

    @FindBy(name = "lastname")
    TextInput lastName;

    @FindBy(name = "email")
    TextInput email;

    @FindBy(name = "telephone")
    TextInput telephone;

    @FindBy(name = "password")
    TextInput password;

    @FindBy(name = "confirm")
    TextInput confirmPassword;

    @FindBy(name = "newsletter")
    Radio newsletter;

    @FindBy(name = "agree")
    CheckBox agree;

    @FindBy(css = "input.btn[value='Continue']")
    Button continueBtn;

    public void createAccount(User user) {
        firstName.sendKeys(user.getFirstName());
        lastName.sendKeys(user.getLastName());
        email.sendKeys(user.getEmail());
        telephone.sendKeys(user.getPhone());
        password.sendKeys(user.getPassword());
        confirmPassword.sendKeys(user.getPassword());
        agree.select();
        continueBtn.click();
    }
}
