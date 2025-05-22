package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class PublisherPage extends BasePage {

    private final String url = baseUrl + "/resources/Publisher";

    @FindBy(how = How.XPATH, using = "//a[@href='/admin/resources/Publisher/actions/new']")
    WebElement createFirstRecordButtonHeader;

    public PublisherPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public NewPublisherCreationPage clickCreateFirstRecordButton(){
        createFirstRecordButtonHeader.click();
        NewPublisherCreationPage newPublisherCreationPage = new NewPublisherCreationPage(driver);
        newPublisherCreationPage.waitForPageToLoad();
        return newPublisherCreationPage;
    }

    public boolean verifyEmailAppearsInTable(String email){
        By xpathEmailCell = By.xpath("//section[@data-testid='property-list-email']");
        webdriverWait.until(ExpectedConditions.presenceOfElementLocated(xpathEmailCell));

        String xpathEmailVerification = "//section[@data-testid='property-list-email' and contains(text(),'%s')]".formatted(email);
        return driver.findElements(By.xpath(xpathEmailVerification)).size() == 1;
    }

    public void waitUntilSuccessMessageDisappears(){
        webdriverWait.until(
                ExpectedConditions.invisibilityOfElementLocated(
                        By.xpath("//div[contains(text(),'Successfully')]")
                )
        );
    }

    @Override
    public String getUrl(){
        return url;
    }

    @Override
    public void waitForPageToLoad() {
        webdriverWait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//a[@href='/admin/resources/Publisher/actions/new']")));
    }

    public void navigate() {
        driver.get(url);
        waitForPageToLoad();
    }
}
