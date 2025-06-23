package ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class NewProfileCreationPage extends BasePage{

    private final String url = baseUrl + "/resources/Profile/actions/new";

    @FindBy(how = How.ID, using = "bio")
    public WebElement bioInput;

    @FindBy(how = How.XPATH, using = "//section[@data-testid='property-edit-publisher']//input")
    public WebElement publisherDropDown;

    @FindBy(how = How.XPATH, using = "//button[@data-testid='button-save']")
    public WebElement saveButton;

    public NewProfileCreationPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public String getUrl(){
        return url;
    }
}
