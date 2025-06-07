package generalutils;

import api.endpoints.PostEndPoints;
import api.endpoints.ProfileEndPoints;
import api.endpoints.PublisherEndPoints;
import api.payload.Post;
import api.payload.Profile;
import api.payload.Publisher;
import enums.PostStatus;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Properties;
import java.util.Random;
import java.util.Stack;

public class TestUtils {

    // Used to generate random test strings as text inputs
    public static long getEpochMili(){
        return Instant.now().toEpochMilli();
    }

    public static String generateRandomString(){
       return new Random()
                .ints(97,123)
                .limit(10)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint,StringBuilder::append)
                .toString();
    }

    public static Publisher getGeneratedPublisherDetails(){
        String publisherName = "test"+ getEpochMili();
        String publisherEmail = publisherName + "@email" + ".com";
        return new Publisher(publisherName,publisherEmail);
    }

    public static Post getGeneratedPostDetails(int publisherId){
        String postTitle = "test post"+ getEpochMili();
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

    public static String getPropertyValueFromPropertiesFile(String property) throws IOException {
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String propsConfigPath = rootPath + "config.properties";

        Properties props = new Properties();
        props.load(new FileInputStream(propsConfigPath));

        return props.getProperty(property);
    }

    public static String getResourcePath(String resource){
        return Thread.currentThread().getContextClassLoader().getResource("").getPath() + resource;
    }

}
