package ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class PublisherPage extends BasePage {

    private final String url = baseUrl + "/resources/Publisher";

    @FindBy(how = How.XPATH, using = "//a[@href='/admin/resources/Publisher/actions/new']")
    public WebElement createFirstRecordButtonHeader;

    public PublisherPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    @Override
    public String getUrl(){
        return url;
    }
}
