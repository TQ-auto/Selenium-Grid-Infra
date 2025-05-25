package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class PostPage extends BasePage {

    private final String url = baseUrl + "/resources/Post";

    // The button in the ActionHeader section
    @FindBy(how = How.XPATH, using = "//a[@href='/admin/resources/Post/actions/new']")
    public WebElement createFirstRecordButtonHeader;

    public By selectAllCheckBox = By.xpath("//td[@data-css='Publisher-checkbox-table-cell']//a");


    public PostPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    @Override
    public String getUrl(){
        return url;
    }
}
