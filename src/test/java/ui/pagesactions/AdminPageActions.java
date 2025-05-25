package ui.pagesactions;

import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.pages.AdminPage;
import ui.utils.DriverManager;

public class AdminPageActions extends ActionsBase {

    AdminPage adminPage;

    public AdminPageActions(){
        this.adminPage = new AdminPage(DriverManager.getDriver());
    }

    public PublisherPageActions goToPublisherPage(){
        TopBarActions topBarActions = new TopBarActions();
        return topBarActions.openMenu().openHappyFolder().clickPublisherButton();
    }

    @Override
    public void waitForPageToLoad() {
        webDriverWait.until(ExpectedConditions.textToBePresentInElement(adminPage.messageTitle,"Welcome"));
    }

    public void navigate() {
        DriverManager.getDriver().get(adminPage.getUrl());
        waitForPageToLoad();
    }

}
