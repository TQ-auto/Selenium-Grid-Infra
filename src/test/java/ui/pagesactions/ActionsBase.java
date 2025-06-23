package ui.pagesactions;

import org.openqa.selenium.support.ui.WebDriverWait;
import ui.utils.DriverManager;
import java.time.Duration;

public abstract class ActionsBase {

    WebDriverWait webDriverWait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10));

    public PostPageActions goToPostPage(){
        TopBarActions topBarActions = new TopBarActions();
        topBarActions.waitForPageToLoad();
        return topBarActions.openMenu().openHappyFolder().clickPostButton();
    }

    public ProfilePageActions goToProfilePage(){
        TopBarActions topBarActions = new TopBarActions();
        topBarActions.waitForPageToLoad();
        return topBarActions.openMenu().openHappyFolder().clickProfileButton();
    }

    public abstract void waitForPageToLoad();
}
