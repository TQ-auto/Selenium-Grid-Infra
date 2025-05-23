package ui.pages.bars;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ui.pages.PostPage;
import ui.pages.ProfilePage;
import ui.pages.PublisherPage;

import java.time.Duration;

public class LeftSideBar {

    WebDriver driver;
    WebDriverWait webdriverWait;

    @FindBy(how = How.XPATH, using = "//a[@data-css='sidebar-logo']")
    WebElement unityLogoButton;

    @FindBy(how = How.XPATH, using = "//div[contains(text(),'Happy Folder')]/parent::a")
    WebElement happyFolderMenuButton;

    @FindBy(how = How.XPATH, using = "//a[@href='/admin/resources/Publisher']")
    WebElement publisherButton;

    @FindBy(how = How.XPATH, using = "//a[@href='/admin/resources/Profile']")
    WebElement profileButton;

    @FindBy(how = How.XPATH, using = "//a[@href='/admin/resources/Post']")
    WebElement postButton;

    public LeftSideBar(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
        webdriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public LeftSideBar openHappyFolder(){
        if(driver.findElements(By.xpath("//a[@href='/admin/resources/Post']")).isEmpty())
            //Open happy folder only if it's closed
            happyFolderMenuButton.click();
        webdriverWait.until(ExpectedConditions.elementToBeClickable(postButton));
        return this;
    }

    public PublisherPage clickPublisherButton(){
        publisherButton.click();
        PublisherPage publisherPage = new PublisherPage(driver);
        publisherPage.waitForPageToLoad();
        return publisherPage;
    }

    public ProfilePage clickProfileButton(){
        profileButton.click();
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.waitForPageToLoad();
        return profilePage;
    }

    public PostPage clickPostButton(){
        postButton.click();
        PostPage postPage = new PostPage(driver);
        postPage.waitForPageToLoad();
        return postPage;
    }

    public void waitForBarToLoad(){
        webdriverWait.until(ExpectedConditions.elementToBeClickable(happyFolderMenuButton));
    }


}
