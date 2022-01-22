package test.automation.pages.OpenCart;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextInput;
import test.automation.framework.Config;
import test.automation.framework.Page;

public class EditAccount extends Page {

    public static final String URL = Config.getUrl() + "/index.php\\?route=account/edit";
    public static final By VERIFY_BY = By.id("account-edit");

    @FindBy(name = "firstname")
    public TextInput firstName;

    @FindBy(name = "lastname")
    public TextInput lastName;

    @FindBy(name = "email")
    public TextInput email;

    @FindBy(name = "telephone")
    public TextInput telephone;

    @FindBy(css = "input.btn[value='Continue']")
    public Button continueBtn;

}
