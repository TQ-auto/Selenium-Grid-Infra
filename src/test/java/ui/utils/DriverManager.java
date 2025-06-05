package ui.utils;

import api.endpoints.Routes;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class DriverManager {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    // Used for running UI tests on selenium grid
    private static final String URL =  "172.19.0.3:4444/wd/hub";
    private static final boolean DEBUG_LOCALLY_FLAG = false;
    private static final String CHROME_DRIVER_PATH = "\\src\\test\\resources\\chromedriver.exe";
    private static final String EDGE_DRIVER_PATH = "\\src\\test\\resources\\msedgedriver.exe";

    public static void initializeDriver(String browser) throws Exception {
        if(DEBUG_LOCALLY_FLAG)
            driver.set(getLocalDriverObject(browser));
        else
            driver.set(getRemoteDriverObject(browser));
    }

    public static WebDriver getDriver(){
        return driver.get();
    }

    private static WebDriver getLocalDriverObject(String browser) throws Exception {
        return switch (browser) {
            case "chrome" -> {
                String path = System.getProperty("user.dir") + CHROME_DRIVER_PATH;
                System.setProperty("webdriver.chrome.driver", path);
                ChromeOptions chromeOptions = new ChromeOptions();
                yield new ChromeDriver(chromeOptions);
            }
            case "edge" -> {
                String path = System.getProperty("user.dir") + EDGE_DRIVER_PATH;
                System.setProperty("webdriver.edge.driver", path);
                EdgeOptions edgeOptions = new EdgeOptions();
                yield new EdgeDriver(edgeOptions);
            }
            default -> throw new Exception("Browser not supported for local run.");
        };
    }

    private static WebDriver getRemoteDriverObject(String browser) throws Exception {
        return switch (browser) {
            case "chrome" ->  new RemoteWebDriver(new URL(URL), new ChromeOptions());
            case "firefox" -> new RemoteWebDriver(new URL(URL), new FirefoxOptions());
            case "edge" -> new RemoteWebDriver(new URL(URL), new EdgeOptions());
            default -> throw new Exception("Browser not supported for remote run.");
        };
    }

}
