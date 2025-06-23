package ui.pagesactions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public abstract class ActionsBase {

    WebDriverWait webDriverWait;
    WebDriver driver;

    protected ActionsBase(WebDriver driver){
        this.driver = driver;
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public PostPageActions goToPostPage(){
        TopBarActions topBarActions = new TopBarActions(driver);
        topBarActions.waitForPageToLoad();
        return topBarActions.openMenu().openHappyFolder().clickPostButton();
    }

    public ProfilePageActions goToProfilePage(){
        TopBarActions topBarActions = new TopBarActions(driver);
        topBarActions.waitForPageToLoad();
        return topBarActions.openMenu().openHappyFolder().clickProfileButton();
    }

    public abstract void waitForPageToLoad();
}
