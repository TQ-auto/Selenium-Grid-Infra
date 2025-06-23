package ui.pagesactions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.pages.PostShowPage;

public class PostShowPageActions extends ActionsBase{

    PostShowPage postShowPage;

    public PostShowPageActions(WebDriver driver){
        super(driver);
        this.postShowPage = new PostShowPage(driver);
    }

    public String getPostId(){
        return postShowPage.postId.getText().replace("#","").strip();
    }

    public PostEditPageActions clickEditButton(){
        postShowPage.editButton.click();
        PostEditPageActions postEditPageActions = new PostEditPageActions(driver);
        postEditPageActions.waitForPageToLoad();
        return postEditPageActions;
    }

    @Override
    public void waitForPageToLoad() {
        webDriverWait.until(
                ExpectedConditions
                        .presenceOfElementLocated(
                                By.cssSelector("a[data-testid='action-edit']")
                        )
        );
    }

    public void navigate(String postId) {
        driver.get(postShowPage.getUrl().formatted(postId));
        waitForPageToLoad();
    }
}
