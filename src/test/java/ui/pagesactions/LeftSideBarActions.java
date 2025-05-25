package ui.pagesactions;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.pages.bars.LeftSideBar;
import ui.utils.DriverManager;

public class LeftSideBarActions extends ActionsBase{

    LeftSideBar leftSideBar;

    public LeftSideBarActions(){
        this.leftSideBar = new LeftSideBar(DriverManager.getDriver());
    }

    public LeftSideBarActions openHappyFolder(){
        if(DriverManager.getDriver().findElements(By.xpath("//a[@href='/admin/resources/Post']")).isEmpty())
            //Open happy folder only if it's closed
            leftSideBar.happyFolderMenuButton.click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(leftSideBar.postButton));
        return this;
    }

    public PublisherPageActions clickPublisherButton(){
        leftSideBar.publisherButton.click();
        PublisherPageActions publisherPageActions = new PublisherPageActions();
        publisherPageActions.waitForPageToLoad();
        return publisherPageActions;
    }

    public ProfilePageActions clickProfileButton(){
        leftSideBar.profileButton.click();
        ProfilePageActions profilePageActions = new ProfilePageActions();
        profilePageActions.waitForPageToLoad();
        return profilePageActions;
    }

    public PostPageActions clickPostButton(){
        leftSideBar.postButton.click();
        PostPageActions postPageActions = new PostPageActions();
        postPageActions.waitForPageToLoad();
        return postPageActions;
    }

    public void waitForPageToLoad() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(leftSideBar.happyFolderMenuButton));
    }

}
