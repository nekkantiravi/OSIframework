package test.automation.framework;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
//import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.logging.Level;
import java.util.stream.Collectors;

import static test.automation.framework.Elements.getElement;
import static test.automation.framework.Elements.getPanel;
import static test.automation.framework.Page.getCurrentPage;
import static test.automation.framework.Page.getCurrentPageName;
import static test.automation.framework.Runner.log;

public final class Actions {

    static int waitSeconds = 30;

    public static synchronized Object execJavascript(String script, Object... args) {
        JavascriptExecutor scriptExe = ((JavascriptExecutor) Browser.getDriver());
        return scriptExe.executeScript(script, args);
    }

    public static synchronized Object tryJavascript(String script, Object... args) {
        try {
            return execJavascript(script, args);
        } catch (Exception ignore) {
            return "";
        }
    }

    public static void doubleClick(WebElement element) {
        org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(Browser.getDriver());
        actions.doubleClick(element).build().perform();
    }

    public static boolean isPageLoaded() {
        String state = (String) tryJavascript("return document.readyState;");
        return state.matches("complete|loaded|interactive");
    }

    public static boolean isJQueryDone() {
        Object jsResponse = tryJavascript("return jQuery.active;");
        if (jsResponse instanceof Long) {
            return ((Long) jsResponse) == 0;
        } else if (jsResponse instanceof String) {
            String response = (String) jsResponse;
            return (response.startsWith("{\"hCode\"") || response.isEmpty());
        } else {
            return true;
        }
    }

    public static boolean isAngularDone() {
        Object jsResponse = tryJavascript("return window.getAllAngularTestabilities().filter(x=>!x.isStable()).length;");
        if (jsResponse instanceof Long) {
            return ((Long) jsResponse) == 0;
        } else if (jsResponse instanceof String) {
            String response = (String) jsResponse;
            return response.isEmpty();
        } else {
            return true;
        }
    }

    public static void waitUntil(BooleanSupplier condition, int seconds) {
        new WebDriverWait(Browser.getDriver(), seconds).until((WebDriver driver) -> condition.getAsBoolean());
    }

    public static void waitUntil(BooleanSupplier condition) {
        waitUntil(condition, waitSeconds);
    }

    public static void waitUntil(ExpectedCondition<WebElement> condition, int seconds) {
        new WebDriverWait(Browser.getDriver(), seconds).until(condition);
    }

    public static void waitUntil(ExpectedCondition<WebElement> condition) {
        waitUntil(condition, waitSeconds);
    }

    public static void waitUntilElementPresent(TypifiedElement element, int seconds) {
        waitUntil(() -> element.exists() && element.isDisplayed(), seconds);
    }

    public static void waitUntilElementPresent(TypifiedElement element) {
        waitUntilElementPresent(element, waitSeconds);
    }

    public static void waitUntilElementNotPresent(TypifiedElement element, int seconds) {
        waitUntil(() -> !(element.exists() && element.isDisplayed()), seconds);
    }

    public static void waitUntilElementNotPresent(TypifiedElement element) {
        waitUntilElementNotPresent(element, waitSeconds);
    }

    public static void waitForJQuery(int seconds) {
        waitUntil(Actions::isJQueryDone, seconds);
    }

    public static void waitForJQuery() {
        waitForJQuery(waitSeconds);
    }

    public static void waitForAngular(int seconds) {
        waitUntil(Actions::isAngularDone, seconds);
    }

    public static void waitForAngular() {
        waitForAngular(waitSeconds);
    }

    public static void waitForLoadingMask(TypifiedElement loadingMask, int seconds) {
        try {
            waitUntilElementPresent(loadingMask);
        } catch (TimeoutException ignore) {
        }
        waitUntilElementNotPresent(loadingMask, seconds);
    }

    public static void waitForLoadingMask(TypifiedElement loadingMask) {
        waitForLoadingMask(loadingMask, waitSeconds);
    }

    public static void click(String element) {
        getElement(element).click();
    }

    public static void jsClick(String element) {
        execJavascript("arguments[0].click();", getElement(element).getWrappedElement());
    }

    public static void jsClick(TypifiedElement element) {
        execJavascript("arguments[0].click();", element.getWrappedElement());
    }

    public static boolean exists(String element) {
        return getElement(element).exists();
    }

    public static boolean isDisplayed(String element) {
        TypifiedElement htmlElement = getElement(element);
        return htmlElement.exists() && htmlElement.isDisplayed();
    }

    public static boolean isDisplayed(String element, int waitSeconds) {
        try {
            waitUntilElementPresent(getElement(element), waitSeconds);
            return true;
        } catch (TimeoutException ignore) {
            return false;
        }
    }

    public static boolean isEnabled(String element) {
        return getElement(element).isEnabled();
    }

    public static boolean isSelected(String element) {
        return getElement(element).isSelected();
    }

    public static void submit(String element) {
        getElement(element).submit();
    }

    public static void sendKeys(String element, String text) {
        getElement(element).sendKeys(text);
    }

    public static void clear(String element) {
        getElement(element).clear();
    }

    public static String getText(String element) {
        return getElement(element).getText();
    }

    public static String getTagName(String element) {
        return getElement(element).getTagName();
    }

    public static String getAttribute(String element, String attributeName) {
        return getElement(element).getAttribute(attributeName);
    }

    public static String getCssValue(String element, String propertyName) {
        return getElement(element).getCssValue(propertyName);
    }

    public static boolean isPanelDisplayed(String panel) {
        HtmlElement htmlElement = getPanel(panel);
        return htmlElement.exists() && htmlElement.isDisplayed();
    }

    public static void click(String element, String panel) {
        getElement(element, panel).click();
    }

    public static void jsClick(String element, String panel) {
        execJavascript("arguments[0].click();", getElement(element, panel).getWrappedElement());
    }

    public static boolean exists(String element, String panel) {
        return getElement(element, panel).exists();
    }

    public static boolean isDisplayed(String element, String panel) {
        TypifiedElement htmlElement = getElement(element, panel);
        return htmlElement.exists() && htmlElement.isDisplayed();
    }

    public static boolean isDisplayed(String element, String panel, int waitSeconds) {
        try {
            waitUntilElementPresent(getElement(element, panel), waitSeconds);
            return true;
        } catch (TimeoutException ignore) {
            return false;
        }
    }

    public static boolean isEnabled(String element, String panel) {
        return getElement(element, panel).isEnabled();
    }

    public static boolean isSelected(String element, String panel) {
        return getElement(element, panel).isSelected();
    }

    public static void submit(String element, String panel) {
        getElement(element, panel).submit();
    }

    public static void sendKeys(String element, String text, String panel) {
        getElement(element, panel).sendKeys(text);
    }

    public static void clear(String element, String panel) {
        getElement(element, panel).clear();
    }

    public static String getText(String element, String panel) {
        return getElement(element, panel).getText();
    }

    public static String getTagName(String element, String panel) {
        return getElement(element, panel).getTagName();
    }

    public static String getAttribute(String element, String attributeName, String panel) {
        return getElement(element, panel).getAttribute(attributeName);
    }

    public static String getCssValue(String element, String propertyName, String panel) {
        return getElement(element, panel).getCssValue(propertyName);
    }

    public static Object performAction(String method, Object... args) {
        if (getCurrentPage() == null) {
            throw new RuntimeException("Not on valid page!");
        }
        List<Class<?>> argsClass = Arrays.stream(args).map(Object::getClass).collect(Collectors.toList());
        String methodSignature = method + argsClass.stream().map(Class::getSimpleName).collect(Collectors.toList()).toString().replace("[", "(").replace("]", ")");
        String pageClass = getCurrentPageName();
        try {
            return getCurrentPage().getClass().getDeclaredMethod(method, argsClass.toArray(new Class<?>[args.length])).invoke(getCurrentPage(), args);
        } catch (NoSuchMethodException e) {
            log().log(Level.WARNING, e.getMessage());
            throw new RuntimeException(methodSignature + " is not declared in " + pageClass);
        } catch (IllegalArgumentException e) {
            log().log(Level.WARNING, e.getMessage());
            throw new RuntimeException("Invalid arguments for " + methodSignature + " of " + pageClass);
        } catch (IllegalAccessException e) {
            log().log(Level.WARNING, e.getMessage());
            throw new RuntimeException(methodSignature + " is not accessible from " + pageClass);
        } catch (InvocationTargetException e) {
            log().log(Level.WARNING, e.getMessage());
            throw new RuntimeException(e.getCause());
        }
    }

    public static Object performPanelAction(String method, String panel, Object... args) {
        HtmlElement panelE = getPanel(panel);
        String panelClass = panelE.getClass().getSimpleName();
        List<Class<?>> argsClass = Arrays.stream(args).map(Object::getClass).collect(Collectors.toList());
        String methodSignature = method + argsClass.stream().map(Class::getSimpleName).collect(Collectors.toList()).toString().replace("[", "(").replace("]", ")");
        try {
            return panelE.getClass().getDeclaredMethod(method, argsClass.toArray(new Class<?>[args.length])).invoke(panelE, args);
        } catch (NoSuchMethodException e) {
            log().log(Level.WARNING, e.getMessage());
            throw new RuntimeException(methodSignature + " is not declared in " + panelClass);
        } catch (IllegalArgumentException e) {
            log().log(Level.WARNING, e.getMessage());
            throw new RuntimeException("Invalid arguments for " + methodSignature + " of " + panelClass);
        } catch (IllegalAccessException e) {
            log().log(Level.WARNING, e.getMessage());
            throw new RuntimeException(methodSignature + " is not accessible from " + panelClass);
        } catch (InvocationTargetException e) {
            log().log(Level.WARNING, e.getMessage());
            throw new RuntimeException(e.getCause());
        }
    }

    public static String getDocumentsPath(String pageFileName) {
        String path = new File("").getAbsolutePath().replace('/','\\') +
                "\\src\\main\\resources\\test\\automation\\uploaddocs\\" + pageFileName;
        return path;
    }

    public static void uploadFile(WebElement uploadButton, String fileName) throws AWTException, InterruptedException {
        uploadButton.click();
        String fileUploadPathType = getDocumentsPath(fileName);
        StringSelection stringSelection = new StringSelection(fileUploadPathType);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
        Robot robot = new Robot();
        robot.delay(250);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.delay(50);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }
}

