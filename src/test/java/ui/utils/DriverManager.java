package ui.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;

import static generalutils.TestUtils.getPropertyValueFromPropertiesFile;
import static generalutils.TestUtils.getResourcePath;

public class DriverManager {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>(); // Used for running UI tests on selenium grid
    private static final boolean DEBUG_LOCALLY_FLAG = false;
    private static String url; // If DEBUG_LOCALLY_FLAG is true this will be localhost else it's selenium-hub ip on shared-net
    private static String chromeDriverPath;
    private static String edgeDriverPath;

    public static void initializeDriver(String browser) throws Exception {

        if(DEBUG_LOCALLY_FLAG){
            chromeDriverPath = getResourcePath("chromedriver.exe");
            edgeDriverPath = getResourcePath("msedgedriver.exe");
            driver.set(getLocalDriverObject(browser));
            url = "http://localhost:3000";
        }
        else{
            url =  "http://" + getPropertyValueFromPropertiesFile("selenium_hub_ip") + ":4444/wd/hub";
            driver.set(getRemoteDriverObject(browser));
        }
    }

    public static WebDriver getDriver(){
        return driver.get();
    }

    public static String getUrl(){
        return url;
    }

    private static WebDriver getLocalDriverObject(String browser) throws Exception {
        return switch (browser) {
            case "chrome" -> {
                System.setProperty("webdriver.chrome.driver", chromeDriverPath);
                ChromeOptions chromeOptions = new ChromeOptions();
                yield new ChromeDriver(chromeOptions);
            }
            case "edge" -> {
                System.setProperty("webdriver.edge.driver", edgeDriverPath);
                EdgeOptions edgeOptions = new EdgeOptions();
                yield new EdgeDriver(edgeOptions);
            }
            default -> throw new Exception("Browser not supported for local run.");
        };
    }

    private static WebDriver getRemoteDriverObject(String browser) throws Exception {
        return switch (browser) {
            case "chrome" ->  new RemoteWebDriver(new URL(url), new ChromeOptions());
            case "firefox" -> new RemoteWebDriver(new URL(url), new FirefoxOptions());
            case "edge" -> new RemoteWebDriver(new URL(url), new EdgeOptions());
            default -> throw new Exception("Browser not supported for remote run.");
        };
    }

}
