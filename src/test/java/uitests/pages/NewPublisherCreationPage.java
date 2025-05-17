package uitests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NewPublisherCreationPage extends BasePage {

    private final String url = baseUrl + "/resources/Publisher/actions/new";

    @FindBy(how = How.ID, using = "name")
    WebElement nameInput;

    @FindBy(how = How.ID, using = "email")
    WebElement emailInput;

    @FindBy(how = How.XPATH, using = "//button[@data-testid='button-save']")
    WebElement saveButton;

    public NewPublisherCreationPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public void enterName(String name){
        nameInput.sendKeys(name);
    }

    public void enterEmail(String email){
        emailInput.sendKeys(email);
    }

    public PublisherPage clickSaveButton(){
        saveButton.click();
        PublisherPage publisherPage = new PublisherPage(driver);
        publisherPage.waitForPageToLoad();
        return publisherPage;
    }

    public PublisherPage createNewPublisher(String name, String email){
        enterName(name);
        enterEmail(email);
        return clickSaveButton();
    }

    public String getUrl(){
        return url;
    }

    @Override
    public void waitForPageToLoad() {
        webdriverWait.until(
                ExpectedConditions
                        .elementToBeClickable(
                                By.xpath("//button[@data-testid='button-save']")
                        )
        );
    }

    public void navigate() {
        driver.get(url);
        waitForPageToLoad();
    }
}
