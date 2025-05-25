package ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class NewPostCreationPage extends BasePage{

    private final String url = baseUrl + "/resources/Post/actions/new";

    @FindBy(how = How.ID, using = "title")
    public WebElement titleInput;

    @FindBy(how = How.CSS, using = "button[data-testid='someJson-add']")
    public WebElement addNewJsonItem;

    @FindBy(how = How.XPATH, using = "//section[@data-testid='property-edit-status']//input")
    public WebElement statusDropdown;

    @FindBy(how = How.CLASS_NAME, using = "adminjs_Checkbox")
    public WebElement publishedCheckbox;

    @FindBy(how = How.XPATH, using = "//section[@data-testid='property-edit-publisher']//input")
    public WebElement publisherDropDown;

    @FindBy(how = How.XPATH, using = "//button[@data-testid='button-save']")
    public WebElement saveButton;

    public WebElement publisherMenu;

    public NewPostCreationPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public String getUrl(){
        return url;
    }
}


