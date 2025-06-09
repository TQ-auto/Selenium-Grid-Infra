package ui.pages;

import api.endpoints.Routes;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ui.utils.DriverManager;

import java.time.Duration;

public abstract class BasePage {

    WebDriver driver;
    WebDriverWait webdriverWait;
    protected String baseUrl;

    protected BasePage(WebDriver driver){
        if(DriverManager.getUrl().contains("localhost")){// In case of running locally
            baseUrl = DriverManager.getUrl() + "/admin";
        }
        else{ // If running tests remotely use the routes IP.
            baseUrl = Routes.HOST + "/admin";
        }

        this.driver = driver;
        webdriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public abstract String getUrl();
}
