package ui.tests;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ui.pages.*;


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
    @Parameters({"publisherName","publisherEmail","postTitle","postStatus","postPublished",
    "jsonNumber","jsonString","jsonBoolean"})
    public void addPublisherAndPost_ChangePostStatusToRemoved_Verify(String publisherName,String publisherEmail,String postTitle,
                                     NewPostCreationPage.PostStatus postStatus,boolean postPublished,int jsonNumber,
                                     String jsonString,boolean jsonBoolean){

        // LOGIN TO ADMIN PAGE
        LoginPage loginPage = new LoginPage(getDriver());
        AdminPage adminPage = loginPage.login(adminTestEmail,adminPassword);
        Assert.assertNotNull(adminPage,"Admin page object was not initialized.");

        // GO TO PUBLISHER PAGE
        PublisherPage publisherPage =
                adminPage.getTopBar().openMenu().openHappyFolder().clickPublisherButton();

        // CREATE NEW PUBLISHER
        NewPublisherCreationPage newPublisherCreationPage = publisherPage.clickCreateFirstRecordButton();
        publisherPage = newPublisherCreationPage.createNewPublisher(publisherName,publisherEmail);
        Assert.assertTrue(publisherPage.verifyEmailAppearsInTable(publisherEmail),
                "Publisher was not added to publishers' page table.");

        // **After creating a publisher, success message appears and hides burger menu, wait until it disappears.
        publisherPage.waitUntilSuccessMessageDisappears();

        // CREATE NEW POST AND LINK TO THE PUBLISHER
        PostPage postPage =
                publisherPage
                        .getTopBar()
                        .openMenu()
                        .openHappyFolder()
                        .clickPostButton()
                        .clickCreateFirstRecordButton()
                        .createNewPost(postTitle, postStatus,postPublished,publisherEmail,jsonNumber,jsonString,jsonBoolean);

        // CHANGE POST STATUS TO REMOVED
        PostShowPage postShowPage = postPage.clickOnLastAddedPost();
        String postId = postShowPage.getPostId();
        PostEditPage postEditPage = postShowPage.clickEditButton();
        postEditPage.selectStatus(NewPostCreationPage.PostStatus.REMOVED);
        postPage = postEditPage.clickSaveButton();

        //VALIDATE POST STATUS CHANGED IN POST PAGE
        Assert.assertEquals(
                postPage.getStatusOfPostFromTable(postId),
                NewPostCreationPage.PostStatus.REMOVED.toString(),
                "Post status was not changed to Removed.");
    }
}
