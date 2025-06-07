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

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    // Used for running UI tests on selenium grid
    private static String seleniumGridUrl;
    private static final boolean DEBUG_LOCALLY_FLAG = false;
    private static String ChromeDriverPath;
    private static String EdgeDriverPath;

    public static void initializeDriver(String browser) throws Exception {


        if(DEBUG_LOCALLY_FLAG){
            ChromeDriverPath = getResourcePath("chromedriver.exe");
            EdgeDriverPath = getResourcePath("msedgedriver.exe");
            driver.set(getLocalDriverObject(browser));
        }
        else{
            seleniumGridUrl =  "http://" + getPropertyValueFromPropertiesFile("selenium_hub_ip") + ":4444/wd/hub";
            driver.set(getRemoteDriverObject(browser));
        }
    }

    public static WebDriver getDriver(){
        return driver.get();
    }

    private static WebDriver getLocalDriverObject(String browser) throws Exception {
        return switch (browser) {
            case "chrome" -> {
                String path = System.getProperty("user.dir") + ChromeDriverPath;
                System.setProperty("webdriver.chrome.driver", path);
                ChromeOptions chromeOptions = new ChromeOptions();
                yield new ChromeDriver(chromeOptions);
            }
            case "edge" -> {
                String path = System.getProperty("user.dir") + EdgeDriverPath;
                System.setProperty("webdriver.edge.driver", path);
                EdgeOptions edgeOptions = new EdgeOptions();
                yield new EdgeDriver(edgeOptions);
            }
            default -> throw new Exception("Browser not supported for local run.");
        };
    }

    private static WebDriver getRemoteDriverObject(String browser) throws Exception {
        return switch (browser) {
            case "chrome" ->  new RemoteWebDriver(new URL(seleniumGridUrl), new ChromeOptions());
            case "firefox" -> new RemoteWebDriver(new URL(seleniumGridUrl), new FirefoxOptions());
            case "edge" -> new RemoteWebDriver(new URL(seleniumGridUrl), new EdgeOptions());
            default -> throw new Exception("Browser not supported for remote run.");
        };
    }

}
