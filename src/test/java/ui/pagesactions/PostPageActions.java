package ui.pagesactions;

import api.payload.Post;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.pages.PostPage;

public class PostPageActions extends ActionsBase {

    PostPage postPage;

    public PostPageActions(WebDriver driver){
        super(driver);
        postPage = new PostPage(driver);
    }

    public PostPageActions createNewPost(Post postObject, String publisherEmail){
        return clickCreateFirstRecordButton()
                .createNewPost(postObject,publisherEmail);
    }

    public NewPostCreationPageActions clickCreateFirstRecordButton(){
        postPage.createFirstRecordButtonHeader.click();

        NewPostCreationPageActions newPostCreationPageActions = new NewPostCreationPageActions(driver);
        newPostCreationPageActions.waitForPageToLoad();
        return newPostCreationPageActions;
    }

    public PostShowPageActions clickOnLastAddedPost(){
        orderPostsTableAsc();
        By lastRowClickableCellLocator = By.cssSelector("tbody tr:first-child td:nth-child(2)");
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(lastRowClickableCellLocator)).click();
        PostShowPageActions postShowPageActions = new PostShowPageActions(driver);
        postShowPageActions.waitForPageToLoad();
        return postShowPageActions;
    }

    public void orderPostsTableAsc(){
        By createdAtBy = By.xpath("//a[contains(text(),'Created At')]");
        WebElement element = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(createdAtBy));
        element.click();
        webDriverWait.until(ExpectedConditions.attributeContains(element,"href","/admin/resources/Post?direction=desc&sortBy=createdAt"));
        element.click();
        webDriverWait.until(ExpectedConditions.attributeContains(element,"href","/admin/resources/Post?direction=asc&sortBy=createdAt"));
    }

    public String getStatusOfPostFromTable(String postId){
        By postRowLocator = By.cssSelector("tr[data-id='%s']".formatted(postId));
        WebElement postRow = driver.findElement(postRowLocator);
        By statusLocator = By.cssSelector("section[data-testid='property-list-status'");
        WebElement statusElement = postRow.findElement(statusLocator);
        return statusElement.getText().strip();
    }

    @Override
    public void waitForPageToLoad() {
        By createNewPostButtonLocator = By.xpath("//a[@href='/admin/resources/Post/actions/new']");
        webDriverWait.until(
                ExpectedConditions
                        .elementToBeClickable(
                                createNewPostButtonLocator));
    }

    public void navigate() {
        driver.get(postPage.getUrl());
        waitForPageToLoad();
    }
}
