package ui.pagesactions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.pages.LoginPage;

public class LoginPageActions extends ActionsBase {

    LoginPage loginPage;

    public LoginPageActions(WebDriver driver){
        super(driver);
        this.loginPage = new LoginPage(driver);
    }

    public void enterEmail(String email){
        loginPage.emailInput.clear();
        loginPage.emailInput.sendKeys(email);
    }

    public void enterPassword(String password){
        loginPage.passwordInput.clear();
        loginPage.passwordInput.sendKeys(password);
    }

    public AdminPageActions clickLoginButton(){
        loginPage.loginButton.click();
        AdminPageActions adminPageActions = new AdminPageActions(driver);
        adminPageActions.waitForPageToLoad();
        return adminPageActions;
    }

    public AdminPageActions login(String email,String password){
        enterEmail(email);
        enterPassword(password);
        return clickLoginButton();
    }

    public LoginPageActions navigate() {
        driver.get(loginPage.getUrl());
        waitForPageToLoad();
        return new LoginPageActions(driver);
    }

    @Override
    public void waitForPageToLoad() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(loginPage.loginButton));
    }


}
