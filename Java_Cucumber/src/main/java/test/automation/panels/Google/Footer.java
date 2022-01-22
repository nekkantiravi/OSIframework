package test.automation.panels.Google;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Link;
import test.automation.framework.Panel;

@FindBy(id = "fbar")
public class Footer {

    @FindBy(linkText = "About")
    public Link about;
}
