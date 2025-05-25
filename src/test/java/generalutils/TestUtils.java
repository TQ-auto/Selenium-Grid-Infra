package generalutils;

import api.endpoints.PostEndPoints;
import api.endpoints.ProfileEndPoints;
import api.endpoints.PublisherEndPoints;
import api.payload.Post;
import api.payload.Profile;
import api.payload.Publisher;
import enums.PostStatus;
import java.io.IOException;
import java.util.Random;
import java.util.Stack;

public class TestUtils {

    // Used to generate random test strings as text inputs
    public static String generateRandomString(){
        return new Random()
                .ints(97,123)
                .limit(10)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint,StringBuilder::append)
                .toString();
    }

    public static Publisher getGeneratedPublisherDetails(){
        String publisherName = generateRandomString();
        String publisherEmail = generateRandomString() + "@" + generateRandomString() + ".com";
        return new Publisher(publisherName,publisherEmail);
    }

    public static Post getGeneratedPostDetails(int publisherId){
        String postTitle = generateRandomString();
        String postContent = generateRandomString();
        PostStatus postStatus = PostStatus.ACTIVE;
        int jsonNumber = 4;
        String jsonString = generateRandomString();
        boolean jsonBoolean = true;
        boolean postPublished = true;
        return new Post(postTitle,postStatus,postContent,jsonNumber,jsonString,jsonBoolean,publisherId,postPublished);
    }

    public static Post getGeneratedPostDetails(){
        String postTitle = generateRandomString();
        String postContent = generateRandomString();
        PostStatus postStatus = PostStatus.ACTIVE;
        int jsonNumber = 4;
        String jsonString = generateRandomString();
        boolean jsonBoolean = true;
        boolean postPublished = true;
        return new Post(postTitle,postStatus,postContent,jsonNumber,jsonString,jsonBoolean,postPublished);
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
