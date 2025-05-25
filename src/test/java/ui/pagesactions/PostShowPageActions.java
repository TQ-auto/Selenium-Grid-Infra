package ui.pagesactions;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.pages.PostShowPage;
import ui.utils.DriverManager;

public class PostShowPageActions extends ActionsBase{

    PostShowPage postShowPage;

    public PostShowPageActions(){
        this.postShowPage = new PostShowPage(DriverManager.getDriver());
    }

    public String getPostId(){
        return postShowPage.postId.getText().replace("#","").strip();
    }

    public PostEditPageActions clickEditButton(){
        postShowPage.editButton.click();
        PostEditPageActions postEditPageActions = new PostEditPageActions();
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
        DriverManager.getDriver().get(postShowPage.getUrl().formatted(postId));
        waitForPageToLoad();
    }
}
