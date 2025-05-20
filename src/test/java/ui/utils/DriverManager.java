package ui.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverManager {

    private static WebDriver driver;
    private static final String CHROME_DRIVER_PATH = "\\src\\test\\resources\\chromedriver.exe";
    private static final String EDGE_DRIVER_PATH = "\\src\\test\\resources\\msedgedriver.exe";

    public static void initializeDriverToRunLocally(String browser) throws Exception {
        driver = getDriverObject(browser);
    }

    public static WebDriver getDriver(){
        return driver;
    }

    private static WebDriver getDriverObject(String browser) throws Exception {
        return switch (browser) {
            case "chrome" -> {
                String path = System.getProperty("user.dir") + CHROME_DRIVER_PATH;
                System.setProperty("webdriver.chrome.driver", path);
                ChromeOptions chromeOptions = new ChromeOptions();
                yield new ChromeDriver(chromeOptions);
            }
            case "firefox" -> {

                FirefoxOptions firefoxOptions = new FirefoxOptions();
                yield new FirefoxDriver(firefoxOptions);
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

}
