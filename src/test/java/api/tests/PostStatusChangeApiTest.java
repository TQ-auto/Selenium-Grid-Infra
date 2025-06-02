package api.tests;

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

    @Test
    public void addPublisherAndPost_ChangePostStatusToRemoved_Verify() throws IOException {
        // CREATE PUBLISHER
        Publisher publisherObject = getGeneratedPublisherDetails();

        Response creatPublisherResponse = PublisherEndPoints.createPublisher(publisherObject);
        publisherObject.setPublisherId(getEntityIdFromJson(creatPublisherResponse));
        deletionStack.push(publisherObject);

        // CREATE POST
        Post postObject = getGeneratedPostDetails(publisherObject.getPublisherId());
        Response createPostResponse = PostEndPoints.createPost(postObject);
        postObject.setPostId(getEntityIdFromJson(createPostResponse));
        deletionStack.push(postObject);

        // CHANGE POST STATUS TO REMOVED
        postObject.setStatus(PostStatus.REMOVED);
        Response editPostResponse = PostEndPoints.editPost(postObject);

        // VERIFY POST STATUS CHANGED TO REMOVED
        String postStatusAfterEdit = JsonActions.getStatusOfPostFromJson(editPostResponse);
        Assert.assertEquals(
                postStatusAfterEdit,
                PostStatus.REMOVED.toString(),
                "Post status was not changed to Removed.");
    }
}

