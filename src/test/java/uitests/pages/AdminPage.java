package uitests.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AdminPage extends BasePage{

    String url = baseUrl;

    @FindBy(how = How.TAG_NAME, using = "h2")
    @CacheLookup
    WebElement messageTitle;

    public AdminPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);

    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public void waitForPageToLoad() {
        webdriverWait.until(ExpectedConditions.textToBePresentInElement(messageTitle,"Welcome"));
    }

    public void navigate() {
        driver.get(url);
        waitForPageToLoad();
    }
}
