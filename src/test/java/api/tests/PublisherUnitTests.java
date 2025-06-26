package api.tests;

import api.endpoints.PublisherEndPoints;
import api.payload.Publisher;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static api.utils.JsonActions.getEntityIdFromJson;
import static generalutils.TestUtils.getGeneratedPublisherDetails;

/**
 * Unit tests of publisher api requests tests POST and DELETE
 */
public class PublisherUnitTests extends TestApiBase{

    Publisher publisherObject;
    Response creatPublisherResponse;

    @Test(priority = 1)
    public void createPublisher() throws IOException {
        logger.info("**** Test post request of creating a new publisher ****");
        logger.info("Generating random publisher details...");
        publisherObject = getGeneratedPublisherDetails();
        logger.info("Sending create publisher request...");
        creatPublisherResponse = PublisherEndPoints.createPublisher(publisherObject);
        Assert.assertEquals(creatPublisherResponse.code(),200,"Publisher creation failed.");
        logger.info("Created publisher successfully.");
        publisherObject.setPublisherId(getEntityIdFromJson(creatPublisherResponse));
    }

    @Test(priority = 2)
    public void deletePublisher() throws IOException {
        logger.info("**** Test delete request of a publisher *****");
        logger.info("Send delete publisher request...");
        Response deletePublisherResponse = PublisherEndPoints.deletePublisher(publisherObject.getPublisherId());
        Assert.assertEquals(deletePublisherResponse.code(),200,"Failed to delete publisher");
        logger.info("Publisher deleted successfully.");
    }
}
