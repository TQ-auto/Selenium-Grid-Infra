package api.tests;

import api.endpoints.PostEndPoints;
import api.endpoints.PublisherEndPoints;
import api.payload.Post;
import api.payload.Publisher;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static api.utils.JsonActions.getEntityIdFromJson;
import static generalutils.TestUtils.getGeneratedPostDetails;
import static generalutils.TestUtils.getGeneratedPublisherDetails;

/**
 * Unit tests of publisher api requests tests POST,GET,DELETE
 */
public class PublisherUnitTests {

    Publisher publisherObject;
    Response creatPublisherResponse;

    @Test(priority = 1)
    public void createPublisher() throws IOException {
        publisherObject = getGeneratedPublisherDetails();

        creatPublisherResponse = PublisherEndPoints.createPublisher(publisherObject);
        Assert.assertEquals(creatPublisherResponse.code(),200,"Publisher creation failed.");
        publisherObject.setPublisherId(getEntityIdFromJson(creatPublisherResponse));
    }

    @Test(priority = 2)
    public void deletePublisher() throws IOException {
        Response deletePublisherResponse = PublisherEndPoints.deletePublisher(publisherObject.getPublisherId());
        Assert.assertEquals(deletePublisherResponse.code(),200,"Failed to delete publisher");
    }
}
