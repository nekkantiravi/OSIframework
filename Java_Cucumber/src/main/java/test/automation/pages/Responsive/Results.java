package test.automation.pages.Responsive;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Timeout;
import ru.yandex.qatools.htmlelements.element.TextBlock;
import test.automation.framework.Config;
import test.automation.framework.Page;

import java.util.List;

public class Results extends Page {

    public static final String URL = Config.getUrl() + "/search";
    public static final By VERIFY_BY = By.cssSelector("span.QHTnWc");

    @Timeout(20)
    @FindBy(css = "span.QHTnWc")
    public List<TextBlock> results;

}
