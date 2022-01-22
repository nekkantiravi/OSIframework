package test.automation.pages.OpenCart;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Link;
import ru.yandex.qatools.htmlelements.element.TextBlock;
import test.automation.framework.Config;
import test.automation.framework.Page;

public class MyAccount extends Page {

    public static final String URL = Config.getUrl() + "/index.php\\?route=account/account";
    public static final By VERIFY_BY = By.id("account-account");


    @FindBy(linkText = "Edit Account")
    public Link editAccount;

    @FindBy(className = "alert-success")
    public TextBlock successAlert;

}
