package api.tests;

import api.endpoints.PostEndPoints;
import api.endpoints.PublisherEndPoints;

import api.payload.Post;
import api.payload.Publisher;
import okhttp3.*;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ui.pages.NewPostCreationPage;

import java.io.IOException;

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

    @Test(priority = 1)
    @Parameters({"publisherName","publisherEmail"})
    public void testCreatePublisher(String publisherName,String publisherEmail) throws IOException {
        Publisher publisherObject = new Publisher();
        publisherObject.setPublisherEmail(publisherEmail);
        publisherObject.setPublisherName(publisherName);

        Response creatPublisherResponse = PublisherEndPoints.createPublisher(publisherObject, cookie);
        Assert.assertTrue(creatPublisherResponse.isSuccessful(),"Publisher creation failed.");

        JSONObject jsonResponse = getJsonObjectOfResponse(creatPublisherResponse);
        publisherId = jsonResponse.getJSONObject("record").getJSONObject("params").getInt("id");
    }

    @Test(priority = 2)
    @Parameters({"postTitle","postContent","postStatus","postPublished","jsonNumber","jsonString","jsonBoolean"})
    public void testCreatePost(String postTitle,String postContent,String postStatus,String postPublished,int jsonNumber,
                               String jsonString,String jsonBoolean) throws IOException {
        postObject = new Post();
        postObject.setStatus(postStatus);
        postObject.setPublisherId(publisherId);
        postObject.setContent(postContent);
        postObject.setJsonBoolean(jsonBoolean);
        postObject.setPublished(postPublished);
        postObject.setTitle(postTitle);
        postObject.setJsonNumber(jsonNumber);
        postObject.setJsonString(jsonString);

        Response createPostResponse = PostEndPoints.createPost(cookie,postObject);
        Assert.assertTrue(createPostResponse.isSuccessful(),"Post creation failed.");

        JSONObject jsonResponse = getJsonObjectOfResponse(createPostResponse);
        int postId = jsonResponse.getJSONObject("record").getJSONObject("params").getInt("id");
        postObject.setPostId(postId);
    }

    @Test(priority = 3)
    public void testChangePostStatusToRemoved() throws IOException {
        postObject.setStatus("REMOVED");
        Response editPostResponse = PostEndPoints.editPost(cookie,postObject);
        Assert.assertTrue(editPostResponse.isSuccessful(),"Post editing request failed.");

        JSONObject jsonResponse = getJsonObjectOfResponse(editPostResponse);
        String postStatus = jsonResponse.getJSONObject("record").getJSONObject("params").getString("status");
        Assert.assertEquals(
                postStatus,
                NewPostCreationPage.PostStatus.REMOVED.toString(),
                "Post status was not changed to Removed.");
    }

}

