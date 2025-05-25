package ui.pages.bars;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LeftSideBar {

    WebDriver driver;
    WebDriverWait webdriverWait;

    @FindBy(how = How.XPATH, using = "//a[@data-css='sidebar-logo']")
    public WebElement unityLogoButton;

    @FindBy(how = How.XPATH, using = "//div[contains(text(),'Happy Folder')]/parent::a")
    public WebElement happyFolderMenuButton;

    @FindBy(how = How.XPATH, using = "//a[@href='/admin/resources/Publisher']")
    public WebElement publisherButton;

    @FindBy(how = How.XPATH, using = "//a[@href='/admin/resources/Profile']")
    public WebElement profileButton;

    @FindBy(how = How.XPATH, using = "//a[@href='/admin/resources/Post']")
    public WebElement postButton;

    public LeftSideBar(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
        webdriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }
}
