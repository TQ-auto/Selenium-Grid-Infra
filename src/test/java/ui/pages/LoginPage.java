package ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage{

    String url = baseUrl + "login";

    @FindBy(how = How.XPATH, using = "//input[@name='email']")
    public WebElement emailInput;

    @FindBy(how = How.XPATH, using = "//input[@name='password']")
    public WebElement passwordInput;

    @FindBy(how = How.XPATH, using = "//form//button")
    public WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    @Override
    public String getUrl() {
        return url;
    }
}
