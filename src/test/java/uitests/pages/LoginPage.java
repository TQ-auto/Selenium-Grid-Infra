package uitests.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage{

    String url = baseUrl + "login";

    @FindBy(how = How.XPATH, using = "//input[@name='email']")
    WebElement emailInput;

    @FindBy(how = How.XPATH, using = "//input[@name='password']")
    WebElement passwordInput;

    @FindBy(how = How.XPATH, using = "//form//button")
    WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    @Override
    public String getUrl() {
        return url;
    }

    public void enterEmail(String email){
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    public void enterPassword(String password){
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public AdminPage clickLoginButton(){
        loginButton.click();
        AdminPage adminPage = new AdminPage(driver);
        adminPage.waitForPageToLoad();
        return adminPage;
    }

    public AdminPage login(String email, String password){
        enterEmail(email);
        enterPassword(password);
        return clickLoginButton();
    }

    @Override
    public void waitForPageToLoad() {
        webdriverWait.until(ExpectedConditions.elementToBeClickable(loginButton));
    }

    public void navigate() {
        driver.get(url);
        waitForPageToLoad();
    }
}
