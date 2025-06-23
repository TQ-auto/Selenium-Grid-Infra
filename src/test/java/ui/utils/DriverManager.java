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

    private static volatile DriverManager instance;
    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>(); // Safe multi-thread running
    private static String url;
    private static String chromeDriverPath;
    private static String edgeDriverPath;

    private DriverManager(){}

    public void initializeDriver(String browser) throws Exception {
        if(getPropertyValueFromPropertiesFile("run_locally").equals("true")){
            chromeDriverPath = getResourcePath("chromedriver.exe");
            edgeDriverPath = getResourcePath("msedgedriver.exe");
            tlDriver.set(getLocalDriverObject(browser));
            url = "http://localhost:3000";
        }
        else{
            url =  "http://" + getPropertyValueFromPropertiesFile("selenium_hub_ip") + ":4444/wd/hub";
            tlDriver.set(getRemoteDriverObject(browser));
        }
    }

    public static DriverManager getInstance(String browser) throws Exception {
        if(instance == null){
            synchronized (DriverManager.class){
                if(instance == null){
                    instance = new DriverManager();
                }
            }
        }
        if(tlDriver.get() == null){
            instance.initializeDriver(browser);
        }
        return instance;
    }

    public WebDriver getDriver(){
        return tlDriver.get();
    }

    public static void quitBrowser(){
        if(tlDriver.get() != null){
            tlDriver.get().quit();
            tlDriver.remove();
        }
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
            default -> throw new IllegalArgumentException("Unsupported browser:" + browser);
        };
    }

    private static WebDriver getRemoteDriverObject(String browser) throws Exception {
        return switch (browser) {
            case "chrome" ->  new RemoteWebDriver(new URL(url), new ChromeOptions());
            case "firefox" -> new RemoteWebDriver(new URL(url), new FirefoxOptions());
            case "edge" -> new RemoteWebDriver(new URL(url), new EdgeOptions());
            default -> throw new IllegalArgumentException("Unsupported browser:" + browser);
        };
    }

}
