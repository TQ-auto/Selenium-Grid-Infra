package ui.pagesactions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.pages.bars.LeftSideBar;

public class LeftSideBarActions extends ActionsBase{

    LeftSideBar leftSideBar;

    public LeftSideBarActions(WebDriver driver){
        super(driver);
        this.leftSideBar = new LeftSideBar(driver);
    }

    public LeftSideBarActions openHappyFolder(){
        if(driver.findElements(By.xpath("//a[@href='/admin/resources/Post']")).isEmpty())
            //Open happy folder only if it's closed
            leftSideBar.happyFolderMenuButton.click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(leftSideBar.postButton));
        return this;
    }

    public PublisherPageActions clickPublisherButton(){
        leftSideBar.publisherButton.click();
        PublisherPageActions publisherPageActions = new PublisherPageActions(driver);
        publisherPageActions.waitForPageToLoad();
        return publisherPageActions;
    }

    public ProfilePageActions clickProfileButton(){
        leftSideBar.profileButton.click();
        ProfilePageActions profilePageActions = new ProfilePageActions(driver);
        profilePageActions.waitForPageToLoad();
        return profilePageActions;
    }

    public PostPageActions clickPostButton(){
        leftSideBar.postButton.click();
        PostPageActions postPageActions = new PostPageActions(driver);
        postPageActions.waitForPageToLoad();
        return postPageActions;
    }

    public void waitForPageToLoad() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(leftSideBar.happyFolderMenuButton));
    }

}
