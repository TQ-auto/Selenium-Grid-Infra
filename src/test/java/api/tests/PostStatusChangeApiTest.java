package api.tests;

import api.endpoints.LoginEndPoints;
import api.endpoints.PostEndPoints;
import api.endpoints.PublisherEndPoints;
import api.payload.Post;
import api.payload.Publisher;
import api.utils.JsonActions;
import enums.PostStatus;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;

import static api.utils.JsonActions.getEntityIdFromJson;
import static generalutils.TestUtils.*;

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

    final String adminEmail = "admin@example.com";
    final String adminPassword = "password";

    @Test
    public void addPublisherAndPost_ChangePostStatusToRemoved_Verify() throws IOException {
        logger.info("**** Starting PostStatusChangeApiTest ****");
        logger.info("Signing in...");
        Response response = LoginEndPoints.login(adminEmail,adminPassword);
        Assert.assertEquals(response.code(),200);
        String responseBodyString = response.body().string();
        Assert.assertTrue(responseBodyString.contains("root.render(AdminJS.Application)"));
        logger.info("Logged in successfully.");

        logger.info("Generating random publisher details...");

        // CREATE PUBLISHER
        Publisher publisherObject = getGeneratedPublisherDetails();
        logger.info("Sending create publisher request...");
        Response creatPublisherResponse = PublisherEndPoints.createPublisher(publisherObject);
        publisherObject.setPublisherId(getEntityIdFromJson(creatPublisherResponse));
        deletionStack.push(publisherObject);
        logger.info("Publisher created successfully.");

        // CREATE POST
        logger.info("Sending create post request...");
        Post postObject = getGeneratedPostDetails(publisherObject.getPublisherId());
        Response createPostResponse = PostEndPoints.createPost(postObject);
        postObject.setPostId(getEntityIdFromJson(createPostResponse));
        deletionStack.push(postObject);
        logger.info("Post created successfully.");

        // CHANGE POST STATUS TO REMOVED
        logger.info("Editing post status from %s to %s...".formatted(postObject.getStatus(),PostStatus.REMOVED));
        postObject.setStatus(PostStatus.REMOVED);
        Response editPostResponse = PostEndPoints.editPost(postObject);

        // VERIFY POST STATUS CHANGED TO REMOVED
        String postStatusAfterEdit = JsonActions.getStatusOfPostFromJson(editPostResponse);
        Assert.assertEquals(
                postStatusAfterEdit,
                PostStatus.REMOVED.toString(),
                "Post status was not changed to Removed.");

        logger.info("Post status successfully changed from %s to %s".formatted(PostStatus.ACTIVE,PostStatus.REMOVED));
    }
}

