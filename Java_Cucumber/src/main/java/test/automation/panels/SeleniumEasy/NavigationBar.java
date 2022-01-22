package test.automation.panels.SeleniumEasy;

import org.junit.Assert;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.annotations.Timeout;
import ru.yandex.qatools.htmlelements.element.Link;
import test.automation.framework.Panel;

import java.util.List;
import java.util.Optional;

@Name("Navigation Bar")
@FindBy(css = "nav.navbar")
public class NavigationBar extends Panel {


    @FindBy(css = "ul.nav.navbar-nav>li>a")
    private List<Link> mainMenus;

    @Timeout(10)
    @FindBy(css = "li.dropdown.open>ul.dropdown-menu>li>a")
    private List<Link> subMenus;

    public void selectMenu(String mainMenuName, String subMenuName) {
        Optional<Link> mainMenu = mainMenus.stream().filter(m -> m.getText().trim().equals(mainMenuName)).findFirst();
        Assert.assertTrue(mainMenuName + " is not present", mainMenu.isPresent());
        mainMenu.get().click();
        Optional<Link> subMenu = subMenus.stream().filter(m -> m.getText().trim().equals(subMenuName)).findFirst();
        Assert.assertTrue(subMenuName + " is not present", subMenu.isPresent());
        subMenu.get().click();
    }
}
