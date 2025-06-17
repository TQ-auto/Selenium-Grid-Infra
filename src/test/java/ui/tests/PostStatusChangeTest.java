package ui.tests;

import api.payload.Post;
import api.payload.Publisher;
import enums.PostStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import ui.pagesactions.*;

import java.io.IOException;

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
    public void addPublisherAndPost_ChangePostStatusToRemoved_Verify() throws IOException {
        logger.info("****** Starting PostStatusChangeTest ******");

        String adminTestEmail = getPropertyValueFromPropertiesFile("adminTestEmail");
        String adminPassword = getPropertyValueFromPropertiesFile("adminPassword");

        // LOGIN TO ADMIN PAGE
        logger.info("Logging in to main page...");
        AdminPageActions adminPageActions = new LoginPageActions().navigate().login(adminTestEmail,adminPassword);

        // GO TO PUBLISHER PAGE
        logger.info("Going to publisher page...");
        PublisherPageActions publisherPageActions =
                adminPageActions.goToPublisherPage();

        // CREATE NEW PUBLISHER
        logger.info("Creating new publisher...");
        Publisher publisherObject = getGeneratedPublisherDetails();
        publisherPageActions = publisherPageActions.createNewPublisher(publisherObject);
        publisherPageActions.clickCloseSuccessMessage();

        Post postObject = getGeneratedPostDetails();
        // CREATE NEW POST AND LINK TO THE PUBLISHER
        logger.info("Going to post page...");
        PostPageActions postPageActions =
                publisherPageActions.goToPostPage()
                        .createNewPost(postObject,publisherObject.getPublisherEmail());

        // CHANGE POST STATUS TO REMOVED
        logger.info("Clicking on last added post in the table on posts page...");
        PostShowPageActions postShowPageActions = postPageActions.clickOnLastAddedPost();
        String postId = postShowPageActions.getPostId();
        PostEditPageActions postEditPageActions = postShowPageActions.clickEditButton();
        logger.info("Changing post status to removed...");
        postPageActions = postEditPageActions.changePostStatus(PostStatus.REMOVED);

        logger.info("Verifying post status equals to Removed in posts table...");
        //VALIDATE POST STATUS CHANGED IN POST PAGE
        Assert.assertEquals(
                postPageActions.getStatusOfPostFromTable(postId),
                PostStatus.REMOVED.toString(),
                "Post status was not changed to Removed.");
    }
}
