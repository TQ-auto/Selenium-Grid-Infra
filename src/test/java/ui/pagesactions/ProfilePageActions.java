package ui.pagesactions;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.pages.ProfilePage;
import ui.utils.DriverManager;

public class ProfilePageActions extends ActionsBase{

    ProfilePage profilePage;

    public ProfilePageActions() {
        this.profilePage = new ProfilePage(DriverManager.getDriver());
    }

    @Override
    public void waitForPageToLoad() {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//a[@href='/admin/resources/Publisher/actions/new']")));
    }

    public void navigate() {
        DriverManager.getDriver().get(profilePage.getUrl());
        waitForPageToLoad();
    }
}
