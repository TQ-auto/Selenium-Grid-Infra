package ui.tests;

import api.payload.Post;
import api.payload.Publisher;
import enums.PostStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import ui.pagesactions.*;

import static generalutils.TestUtils.*;

/**
 * Test Scenario 1 - UI (POM)
 * Step 1: Create Publisher entity
 * Step 2: Create Post entity
 * Step 3: Link to the Publisher created( Status= Active, Published= True)
 * Step 4: Edit Post - Change status to remove
 * Step 5: Save
 * Step 6: Validate post status was changed to Remove from the Post page
 */
public class PostStatusChangeTest extends TestBase{

    @Test(description = "Add a publisher and a post with status active then change status to remove from post page")
    public void addPublisherAndPost_ChangePostStatusToRemoved_Verify(){

        // LOGIN TO ADMIN PAGE
        AdminPageActions adminPageActions = new LoginPageActions().login(adminTestEmail,adminPassword);

        // GO TO PUBLISHER PAGE
        PublisherPageActions publisherPageActions =
                adminPageActions.goToPublisherPage();

        // CREATE NEW PUBLISHER
        Publisher publisherObject = getGeneratedPublisherDetails();
        publisherPageActions = publisherPageActions.createNewPublisher(publisherObject);
        publisherPageActions.waitUntilSuccessMessageDisappears();

        Post postObject = getGeneratedPostDetails();
        // CREATE NEW POST AND LINK TO THE PUBLISHER
        PostPageActions postPageActions =
                publisherPageActions.goToPostPage()
                        .createNewPost(postObject,publisherObject.getPublisherEmail());

        // CHANGE POST STATUS TO REMOVED
        PostShowPageActions postShowPageActions = postPageActions.clickOnLastAddedPost();
        String postId = postShowPageActions.getPostId();
        PostEditPageActions postEditPageActions = postShowPageActions.clickEditButton();
        postPageActions = postEditPageActions.changePostStatus(PostStatus.REMOVED);

        //VALIDATE POST STATUS CHANGED IN POST PAGE
        Assert.assertEquals(
                postPageActions.getStatusOfPostFromTable(postId),
                PostStatus.REMOVED.toString(),
                "Post status was not changed to Removed.");
    }
}
