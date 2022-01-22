package test.automation.pages.SeleniumEasy;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import test.automation.framework.Config;
import test.automation.framework.Page;
import test.automation.panels.SeleniumEasy.NavigationBar;

public class Home extends Page {

    public static final String URL = Config.getUrl() + "/test";
    public static final By VERIFY_BY = By.linkText("Start Practising");

    public NavigationBar navigationBar;

    @FindBy(linkText = "Start Practising")
    public Button startPractising;

}
