package ui.pages;

import api.endpoints.Routes;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public abstract class BasePage {

    WebDriver driver;
    WebDriverWait webdriverWait;
    protected final String baseUrl = Routes.HOST + "/admin";

    protected BasePage(WebDriver driver){
        this.driver = driver;
        webdriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public abstract String getUrl();
}
