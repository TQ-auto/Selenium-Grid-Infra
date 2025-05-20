package ui.pages.bars;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TopBar {

    WebDriver driver;
    WebDriverWait webdriverWait;

    @FindBy(how = How.XPATH,using = "//section[@data-css='topbar']/section[1]")
    @CacheLookup
    WebElement burgerMenuButton; // Burger menu button appears only on minimized browser view

    public TopBar(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
        webdriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public LeftSideBar openMenu(){
        burgerMenuButton.click();
        LeftSideBar leftSideBar = new LeftSideBar(driver);
        leftSideBar.waitForBarToLoad();
        return leftSideBar;
    }

    public void waitForBarToLoad(){
        webdriverWait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//section[@data-css='topbar']/section[1]")));
    }

}
