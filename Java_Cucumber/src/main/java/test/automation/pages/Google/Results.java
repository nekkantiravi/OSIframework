package test.automation.pages.Google;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Timeout;
import ru.yandex.qatools.htmlelements.element.TextBlock;
import test.automation.framework.Config;
import test.automation.framework.Page;
import test.automation.panels.Google.Footer;

import java.util.List;

public class Results extends Page {

    public static final String URL = Config.getUrl() + "/search";
    public static final By VERIFY_BY = By.tagName("cite");

    Footer footer;


    @Timeout(20)
    @FindBy(tagName = "cite")
    public List<TextBlock> results;
}
