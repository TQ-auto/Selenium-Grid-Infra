package ui.pagesactions;

import api.payload.Post;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.pages.PostPage;
import ui.utils.DriverManager;

public class PostPageActions extends ActionsBase {

    PostPage postPage;

    public PostPageActions(){
        postPage = new PostPage(DriverManager.getDriver());
    }

    public PostPageActions createNewPost(Post postObject, String publisherEmail){
        return clickCreateFirstRecordButton()
                .createNewPost(postObject,publisherEmail);
    }

    public NewPostCreationPageActions clickCreateFirstRecordButton(){
        postPage.createFirstRecordButtonHeader.click();

        NewPostCreationPageActions newPostCreationPageActions = new NewPostCreationPageActions();
        newPostCreationPageActions.waitForPageToLoad();
        return newPostCreationPageActions;
    }

    public boolean verifyPostAppearsInTable(String title){
        String xpathPostVerification =
                "//section[@data-testid='property-list-title' and contains(text(),'%s')]".formatted(title);

        return DriverManager.getDriver().findElements(By.xpath(xpathPostVerification)).size() == 1;
    }

    public PostShowPageActions clickOnLastAddedPost(){
        orderPostsTableDesc();
        By lastRowClickableCellLocator = By.cssSelector("tbody tr:last-child td:nth-child(2)");
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(lastRowClickableCellLocator)).click();
        PostShowPageActions postShowPageActions = new PostShowPageActions();
        postShowPageActions.waitForPageToLoad();
        return postShowPageActions;
    }

    public void orderPostsTableDesc(){
        By createdAtBy = By.xpath("//a[contains(text(),'Created At')]");
        WebElement element = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(createdAtBy));
        element.click();
        webDriverWait.until(ExpectedConditions.attributeContains(element,"href","/admin/resources/Post?direction=desc&sortBy=createdAt"));
    }

    public String getStatusOfPostFromTable(String postId){
        By postRowLocator = By.cssSelector("tr[data-id='%s']".formatted(postId));
        WebElement postRow = DriverManager.getDriver().findElement(postRowLocator);
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
        DriverManager.getDriver().get(postPage.getUrl());
        waitForPageToLoad();
    }
}
