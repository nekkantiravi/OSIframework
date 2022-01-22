package test.automation.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

import java.util.logging.Level;

import static test.automation.framework.Actions.*;
import static test.automation.framework.Runner.log;

public class Page {

    private static Page currentPage;

    public Page() {
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(Browser.getDriver())), this);
        currentPage = this;
        log().info("Page Initialized: " + currentPage.getClass().getName());
    }

    public static Page getCurrentPage() {
        return currentPage;
    }
    
    public static String getCurrentPageName() {
        return currentPage.getClass().getSimpleName();
    }

    public static String getPageClassName(String pageClassName) {
        return Page.class.getName().replace("framework.Page", "pages." + pageClassName.replace(" ", "."));
    }

    public static void verifyPage(String pageClassName) {
        pageClassName = getPageClassName(pageClassName);
        try {
            verifyPage(Class.forName(pageClassName));
        } catch (ClassNotFoundException e) {
            log().log(Level.WARNING, e.getMessage());
            throw new RuntimeException(pageClassName + " not found!");
        }
    }

    public static void verifyPage(Class pageClass) {
        if (!pageClass.getSuperclass().getSimpleName().equals("Page")) {
            throw new RuntimeException("Invalid page " + pageClass.getSimpleName());
        }
        String url = null;
        try {
            url = (String) pageClass.getDeclaredField("URL").get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            log().log(Level.WARNING, "URL not defined for " + pageClass.getSimpleName());
        }
        if (url != null) {
            String finalUrl = url;
            try {
                waitUntil(() -> Browser.getDriver().getCurrentUrl().matches(finalUrl + ".*"));
            } catch (TimeoutException e) {
                log().log(Level.WARNING, e.getMessage());
                throw new RuntimeException("Not on page " + pageClass.getSimpleName() + " (" + url + ") ("
                        + Browser.getDriver().getCurrentUrl() + ")");
            }
        }

        try {
            waitUntil(Actions::isPageLoaded);
        } catch (TimeoutException e) {
            log().log(Level.WARNING, e.getMessage());
            throw new RuntimeException(pageClass.getSimpleName() + "page (" + url + ") is taking too long to load." );
        }

        By verifyBy = null;
        try {
            verifyBy = (By) pageClass.getDeclaredField("VERIFY_BY").get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            log().log(Level.WARNING, "VERIFY_BY not defined for " + pageClass.getSimpleName());
        }
        if (verifyBy != null) {
            try {
                waitUntil(ExpectedConditions.visibilityOfElementLocated(verifyBy));
            } catch (TimeoutException e) {
                log().log(Level.WARNING, e.getMessage());
                throw new RuntimeException(pageClass.getSimpleName() + "page VERIFY_BY (" + verifyBy.toString() + ") is not displayed.");
            }
        }
        log().info("On Page: " + pageClass.getSimpleName());
    }

    public static Page onPage(String pageClassName) {
        pageClassName = getPageClassName(pageClassName);
        try {
            return onPage(Class.forName(pageClassName));
        } catch (ClassNotFoundException e) {
            log().log(Level.WARNING, e.getMessage());
            throw new RuntimeException(pageClassName + " not found!");
        }
    }

    public static Page onPage(Class pageClass) {
        verifyPage(pageClass);
        try {
            pageClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            log().log(Level.WARNING, e.getMessage());
            throw new RuntimeException("Instantiation failed for " + pageClass.getName());
        }
        return getCurrentPage();
    }

    public static boolean onPageNew(Class pageClass) {
        verifyPage(pageClass);
        try {
            Object o = pageClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            log().log(Level.WARNING, e.getMessage());
            throw new RuntimeException("Instantiation failed for " + pageClass.getName());
        }
        return true;
    }

    public static Page visit(String pageClassName) {
        pageClassName = getPageClassName(pageClassName);
        try {
            return visit(Class.forName(pageClassName));
        } catch (ClassNotFoundException e) {
            log().log(Level.WARNING, e.getMessage());
            throw new RuntimeException(pageClassName + " not found!");
        }
    }

    public static Page visit(Class pageClass) {
        try {
            Browser.getDriver().navigate().to((String) pageClass.getDeclaredField("URL").get(null));
        } catch (NoSuchFieldException e) {
            log().log(Level.WARNING, e.getMessage());
            throw new RuntimeException("URL not found for " + pageClass.getSimpleName());
        } catch (IllegalAccessException e) {
            log().log(Level.WARNING, e.getMessage());
            throw new RuntimeException(pageClass.getName() + " is not accessible.");
        }
        return onPage(pageClass);
    }

}
