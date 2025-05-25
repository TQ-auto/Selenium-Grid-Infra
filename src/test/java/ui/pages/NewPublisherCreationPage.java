package ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class NewPublisherCreationPage extends BasePage {

    private final String url = baseUrl + "/resources/Publisher/actions/new";

    @FindBy(how = How.ID, using = "name")
    public WebElement nameInput;

    @FindBy(how = How.ID, using = "email")
    public WebElement emailInput;

    @FindBy(how = How.XPATH, using = "//button[@data-testid='button-save']")
    public WebElement saveButton;

    public NewPublisherCreationPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public String getUrl(){
        return url;
    }
}
