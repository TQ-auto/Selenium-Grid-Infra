package api.tests;

import api.endpoints.PostEndPoints;
import api.endpoints.PublisherEndPoints;
import api.payload.Post;
import api.payload.Publisher;
import api.utils.JsonActions;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import ui.pages.NewPostCreationPage;
import java.io.IOException;

import static GeneralUtils.TestUtils.generateString;
import static api.utils.JsonActions.getEntityIdFromJson;

/**
 * Api test
 * Step 1: Create a Publisher using API
 * Step 2: Create a Post using API
 * Step 3: Link to the Publisher created( Status= Active, Published= True)
 * Step 4: Edit Post - Change status to remove
 * Step 5: Save
 * Step 6: Validate post status was changed to Remove from the Post page
 */

public class PostStatusChangeApiTest extends TestApiBase{

    int publisherId;
    Post postObject;

    String publisherName = generateString();
    String publisherEmail = generateString() + "@" + generateString() + ".com";
    String postTitle = generateString();
    String postContent = generateString();
    NewPostCreationPage.PostStatus postStatus = NewPostCreationPage.PostStatus.ACTIVE;
    boolean postPublished = true;
    int jsonNumber = 4;
    String jsonString = generateString();
    boolean jsonBoolean = true;

    @Test
    public void testCreatePublisher() throws IOException {
        // CREATE PUBLISHER
        Publisher publisherObject = new Publisher(publisherName,publisherEmail);
        Response creatPublisherResponse = PublisherEndPoints.createPublisher(publisherObject, cookie);
        publisherId = getEntityIdFromJson(creatPublisherResponse);

        // CREATE POST
        postObject = new Post(postTitle,String.valueOf(postStatus),postContent,jsonNumber,jsonString,String.valueOf(jsonBoolean),publisherId,String.valueOf(postPublished));
        Response createPostResponse = PostEndPoints.createPost(cookie,postObject);
        postObject.setPostId(getEntityIdFromJson(createPostResponse));

        // CHANGE POST STATUS TO REMOVED
        postObject.setStatus(NewPostCreationPage.PostStatus.REMOVED.toString());
        Response editPostResponse = PostEndPoints.editPost(cookie,postObject);

        // VERIFY POST STATUS CHANGED TO REMOVED
        String postStatusAfterEdit = JsonActions.getStatusOfPostFromJson(editPostResponse);
        Assert.assertEquals(
                postStatusAfterEdit,
                NewPostCreationPage.PostStatus.REMOVED.toString(),
                "Post status was not changed to Removed.");
    }
}

