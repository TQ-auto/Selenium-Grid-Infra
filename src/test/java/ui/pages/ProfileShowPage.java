package ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ProfileShowPage extends BasePage{

    String url = baseUrl + "/resources/Profile/records/%s/show";

    @FindBy(how = How.CSS, using = "section[data-testid='property-show-id'] section")
    public WebElement profileId;

    @FindBy(how = How.CSS, using = "a[data-testid='action-edit']")
    public WebElement editButton;

    public ProfileShowPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    @Override
    public String getUrl() {
        return url;
    }
}
