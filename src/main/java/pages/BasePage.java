package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.bars.LeftSideBar;
import pages.bars.TopBar;

import java.time.Duration;

public abstract class BasePage {

    WebDriver driver;
    WebDriverWait webdriverWait;
    protected final String baseUrl = "http://localhost:3000/admin";
    protected TopBar topBar;
    protected LeftSideBar leftSideBar;

    protected BasePage(WebDriver driver){
        this.driver = driver;
        topBar = new TopBar(driver);
        leftSideBar = new LeftSideBar(driver);
        webdriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public TopBar getTopBar(){
        topBar.waitForBarToLoad();
        return topBar;
    }

    public LeftSideBar getLeftSideBar(){
        leftSideBar.waitForBarToLoad();
        return leftSideBar;
    }

    public abstract String getUrl();

    public abstract void waitForPageToLoad();

}
