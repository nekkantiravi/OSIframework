package test.automation.pages.OpenCart;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextInput;
import test.automation.framework.Config;
import test.automation.framework.Page;

public class AccountLogin extends Page {

    public static final String URL = Config.getUrl() + "/index.php\\?route=account/login";
    public static final By VERIFY_BY = By.id("account-login");

    @FindBy(name = "email")
    TextInput email;

    @FindBy(name = "password")
    TextInput password;

    @FindBy(css = "input.btn[value='LoginSteps']")
    Button loginBtn;

    public void login(String email, String password) {
        this.email.sendKeys(email);
        this.password.sendKeys(password);
        loginBtn.click();
    }
}
