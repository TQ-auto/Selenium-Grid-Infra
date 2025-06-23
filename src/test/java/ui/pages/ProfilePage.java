package ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ProfilePage extends BasePage {

    private final String url = baseUrl + "/resources/Profile";

    @FindBy(how = How.XPATH, using = "//a[@href='/admin/resources/Profile/actions/new']")
    public WebElement createFirstRecordButtonHeader;

    public ProfilePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    @Override
    public String getUrl() {
        return url;
    }
}
