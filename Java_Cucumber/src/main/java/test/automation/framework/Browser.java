package test.automation.framework;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import static test.automation.framework.Config.*;
import static test.automation.framework.Runner.log;

public final class Browser {

    private static WebDriver driver;

    private static void start() {

        try {
            if (getRemoteUrl() != null) {
                DesiredCapabilities capabilities = new DesiredCapabilities();
                if (getBrowser() != null) {
                    capabilities.setCapability(CapabilityType.BROWSER_NAME, getBrowser());
                }
                if (getBrowserVersion() != null) {
                    capabilities.setCapability(CapabilityType.BROWSER_VERSION, getBrowserVersion());
//                    capabilities.setCapability(CapabilityType.VERSION, getBrowserVersion());
                }
                if (getPlatform() != null) {
                    capabilities.setCapability(CapabilityType.PLATFORM_NAME, getPlatform());
                }
                if (getPlatformVersion() != null) {
                    capabilities.setCapability("platformVersion", getPlatformVersion());
                }
                if (getDevice() != null) {
                    capabilities.setCapability("deviceName", getDevice());
                    if (getAppiumVersion() != null) {
                        capabilities.setCapability("appiumVersion", getAppiumVersion());
                    }
                    if (getPlatform() != null && getPlatform().toLowerCase().contains("ios")) {
                        driver = new IOSDriver(new URL(getRemoteUrl()), capabilities);
                    } else {
                        driver = new AndroidDriver(new URL(getRemoteUrl()), capabilities);
                    }
                    log().info(getDevice() + " started in cloud.");
                    return;
                }
                driver = new RemoteWebDriver(new URL(getRemoteUrl()), capabilities);
                log().info(getBrowser() + " started in cloud.");
                return;
            }

            if (getDevice() != null) {
                WebDriverManager.chromedriver().setup();
                Map<String, String> emulationOptions = new HashMap<>();
                emulationOptions.put("deviceName", getDevice());
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setExperimentalOption("mobileEmulation", emulationOptions);
                driver = new ChromeDriver(chromeOptions);
                log().info(getDevice() + "Chrome Emulation started.");
                return;
            }

            switch (getBrowser().toLowerCase()) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case "chrome_headless":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions headlessOptions = new ChromeOptions();
                    headlessOptions.addArguments("--headless");
                    driver = new ChromeDriver(headlessOptions);
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                case "safari":
                    driver = new SafariDriver();
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    break;
                case "ie":
                case "internet explorer":
                    WebDriverManager.iedriver().setup();
                    driver = new InternetExplorerDriver();
                    break;
                case "opera":
                    WebDriverManager.operadriver().setup();
                    driver = new OperaDriver();
            }
            Browser.getDriver().manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
            log().info(getBrowser() + " browser started.");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void maximize() {
        getDriver().manage().window().maximize();
    }

    public static boolean isStarted() {
        return driver != null;
    }

    public static void reStart() {
        if (isStarted()) {
            quit();
        }
        start();
        log().info(getBrowser() + " browser re-started.");
    }

    public static void quit() {
        if (isStarted()) {
            driver.quit();
            driver = null;
        }
        log().info(getBrowser() + " browser closed.");
    }

    public static WebDriver getDriver() {
        if (!isStarted()) {
            start();
            maximize();
//            if (getDriver() == null) {
//                maximize();
//            }
        }
        return driver;
    }

    public static AndroidDriver getAndroidDriver() {
        if (getDriver() instanceof AndroidDriver) {
            return (AndroidDriver) getDriver();
        }
        return null;
    }

    public static IOSDriver getIOSDriver() {
        if (getDriver() instanceof IOSDriver) {
            return (IOSDriver) getDriver();
        }
        return null;
    }

    public static byte[] getScreenShot() {
        try {
            return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
        } catch (Exception e) {
            log().log(Level.WARNING, e.getMessage());
            return null;
        }
    }
}
