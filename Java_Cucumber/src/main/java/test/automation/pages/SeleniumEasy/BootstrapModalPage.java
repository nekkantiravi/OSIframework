package test.automation.pages.SeleniumEasy;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextBlock;
import test.automation.framework.Actions;
import test.automation.framework.Config;
import test.automation.framework.Page;

public class BootstrapModalPage extends Page {

    public static final String URL = Config.getUrl() + "/test/bootstrap-modal-demo.html";
    public static final By VERIFY_BY = By.xpath("//h2[text()='Bootstrap Modal Example for Automation']");

    @FindBy(css = "a[href='#myModal0'")
    private Button launchMyModalBtn;

    @FindBy(id = "myModal0")
    private TextBlock myModal;

    public void clickLaunchModalBtn() {
        launchMyModalBtn.click();
    }

    public boolean isModalContentDisplayed() {
        Actions.waitUntilElementPresent(myModal);
        return myModal.isDisplayed();
    }
}
