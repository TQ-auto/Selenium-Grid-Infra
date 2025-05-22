package GeneralUtils;

import api.endpoints.PostEndPoints;
import api.endpoints.ProfileEndPoints;
import api.endpoints.PublisherEndPoints;
import api.payload.Post;
import api.payload.Profile;
import api.payload.Publisher;
import java.io.IOException;
import java.util.Random;
import java.util.Stack;

public class TestUtils {

    // Used to generate random test strings as text inputs
    public static String generateString(){
        return new Random()
                .ints(97,123)
                .limit(10)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint,StringBuilder::append)
                .toString();
    }

    //Method used to clean up after test by deleting all created entities in the test using apis.
    public static void cleanup(Stack<Object> deletionStack) throws IOException {
        while(!deletionStack.empty()){
            if(deletionStack.peek() instanceof Post){
                int postId = ((Post)deletionStack.pop()).getPostId();
                PostEndPoints.deletePost(postId);
            }else if(deletionStack.peek() instanceof Profile){
                int profileId = ((Profile)deletionStack.pop()).getProfileId();
                ProfileEndPoints.deleteProfile(profileId);
            }
            else if(deletionStack.peek() instanceof Publisher){
                int publisherId = ((Publisher)deletionStack.pop()).getPublisherId();
                PublisherEndPoints.deletePublisher(publisherId);
            }
        }
    }
}
