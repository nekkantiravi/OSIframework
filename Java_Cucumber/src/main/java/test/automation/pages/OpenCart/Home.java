package test.automation.pages.OpenCart;

import org.openqa.selenium.By;
import test.automation.framework.Config;
import test.automation.framework.Page;
import test.automation.framework.Panel;
import test.automation.panels.OpenCart.TopNav;

public class Home extends Page {

    public static final String URL = Config.getUrl();
    public static final By VERIFY_BY = By.id("common-home");

    public TopNav topNav;

}
