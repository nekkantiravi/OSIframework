package test.automation.pages.OpenCart;

import org.openqa.selenium.By;
import test.automation.framework.Config;
import test.automation.framework.Page;

public class AccountSuccess extends Page {

    public static final String URL = Config.getUrl() + "/index.php\\?route=account/success";
    public static final By VERIFY_BY = By.id("common-success");

}
