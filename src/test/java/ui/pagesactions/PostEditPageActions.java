package ui.pagesactions;

import enums.PostStatus;
import ui.pages.PostEditPage;
import ui.utils.DriverManager;

public class PostEditPageActions extends NewPostCreationPageActions {

    PostEditPage postEditPage;

    public PostEditPageActions(){
        this.postEditPage = new PostEditPage(DriverManager.getDriver());
    }

    public PostPageActions changePostStatus(PostStatus postStatus){
        selectStatus(postStatus);
        return clickSaveButton();
    }

    public void navigate(String postId) {
        DriverManager.getDriver().get(postEditPage.getUrl().formatted(postId));
        waitForPageToLoad();
    }
}
