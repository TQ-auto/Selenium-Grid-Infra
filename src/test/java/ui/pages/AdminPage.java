package ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class AdminPage extends BasePage{

    String url = baseUrl;

    @FindBy(how = How.TAG_NAME, using = "h2")
    public WebElement messageTitle;

    public AdminPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);

    }

    @Override
    public String getUrl() {
        return url;
    }
}
