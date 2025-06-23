package ui.pagesactions;

import enums.PostStatus;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import ui.pages.PostEditPage;

public class PostEditPageActions extends NewPostCreationPageActions {

    PostEditPage postEditPage;

    public PostEditPageActions(WebDriver driver){
        super(driver);
        this.postEditPage = new PostEditPage(driver);
    }

    public PostPageActions changePostStatus(PostStatus postStatus){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);",postEditPage.statusDropdown);
        selectStatus(postStatus);
        return clickSaveButton();
    }

    public void navigate(String postId) {
        driver.get(postEditPage.getUrl().formatted(postId));
        waitForPageToLoad();
    }
}
