package test.automation.pages.Responsive;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.TextInput;
import test.automation.framework.Config;
import test.automation.framework.Page;
import test.automation.panels.Google.Footer;

public class Search extends Page {

    public static final String URL = Config.getUrl();
    public static final By VERIFY_BY = By.name("q");

    public Footer footer;

    @FindBy(name = "q")
    public TextInput searchBox;

    public void search(String keyWord) {
        searchBox.sendKeys(keyWord);
        searchBox.submit();
    }
}
