package ui.pagesactions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.pages.AdminPage;

public class AdminPageActions extends ActionsBase {

    AdminPage adminPage;

    public AdminPageActions(WebDriver driver){
        super(driver);
        this.adminPage = new AdminPage(driver);
    }

    public PublisherPageActions goToPublisherPage(){
        TopBarActions topBarActions = new TopBarActions(driver);
        return topBarActions.openMenu().openHappyFolder().clickPublisherButton();
    }

    @Override
    public void waitForPageToLoad() {
        webDriverWait.until(ExpectedConditions.textToBePresentInElement(adminPage.messageTitle,"Welcome"));
    }

    public void navigate() {
        driver.get(adminPage.getUrl());
        waitForPageToLoad();
    }

}
