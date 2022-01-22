package test.automation.panels.OpenCart;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import ru.yandex.qatools.htmlelements.element.Link;
import static test.automation.framework.Actions.waitUntil;
//import test.automation.framework.Panel;

@FindBy(css = "nav#top")
public class TopNav  {

    @FindBy(css = "a[title='My Account']")
    public Link myAccount;

    public void select(String menu) {
        By menuBy = By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']/li/a[text()='" + menu + "']");
//        waitUntil(elementToBeClickable(findElement(menuBy)));
//        findElement(menuBy).click();
    }
}
