package test.automation.pages.SeleniumEasy;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextBlock;
import test.automation.framework.Config;
import test.automation.framework.Page;

public class DynamicData extends Page {

    public static final String URL = Config.getUrl() + "/test/dynamic-data-loading-demo.html";
    public static final By VERIFY_BY = By.xpath("//h3[text()='Loading the data Dynamically']");

    @FindBy(id = "save")
    public Button getNewUser;

    @FindBy(css = "div#loading>img[src^='https://randomuser.me/api/']")
    public TextBlock userImage;

}
