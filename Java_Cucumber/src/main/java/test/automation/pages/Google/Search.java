package test.automation.pages.Google;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextInput;
import test.automation.framework.Config;
import test.automation.framework.Page;
import test.automation.panels.Google.Footer;

public abstract class Search extends Page {

    public static final String URL = Config.getUrl();
    public static final By VERIFY_BY = By.name("q");

    public Footer footer;

    @FindBy(name = "q")
    public TextInput searchBox;

    @FindBy(name = "btnK")
    public Button searchButton;

    public void search(String keyWord) {
        searchBox.sendKeys(keyWord);
        searchButton.submit();
    }

}
