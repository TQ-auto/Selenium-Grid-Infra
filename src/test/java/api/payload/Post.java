package api.payload;

import ui.pages.NewPostCreationPage;

public class Post {

    int postId;
    String title;
    NewPostCreationPage.PostStatus status;
    String content;
    int jsonNumber;
    String jsonString;
    boolean jsonBoolean;
    int publisherId;
    boolean published;

    public Post(String title, NewPostCreationPage.PostStatus status, String content, int jsonNumber, String jsonString, boolean jsonBoolean, int publisherId, boolean published) {
        this.title = title;
        this.status = status;
        this.content = content;
        this.jsonNumber = jsonNumber;
        this.jsonString = jsonString;
        this.jsonBoolean = jsonBoolean;
        this.publisherId = publisherId;
        this.published = published;
    }

    public Post(String title, NewPostCreationPage.PostStatus status, String content, int jsonNumber, String jsonString, boolean jsonBoolean, boolean published) {
        this.title = title;
        this.status = status;
        this.content = content;
        this.jsonNumber = jsonNumber;
        this.jsonString = jsonString;
        this.jsonBoolean = jsonBoolean;
        this.published = published;
    }


    public Post(int postId, String title, String content, NewPostCreationPage.PostStatus status, String jsonString, int jsonNumber, boolean jsonBoolean, int publisherId, boolean published) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.status = status;
        this.jsonString = jsonString;
        this.jsonNumber = jsonNumber;
        this.jsonBoolean = jsonBoolean;
        this.publisherId = publisherId;
        this.published = published;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getJsonNumber() {
        return jsonNumber;
    }

    public void setJsonNumber(int jsonNumber) {
        this.jsonNumber = jsonNumber;
    }

    public String getJsonString() {
        return jsonString;
    }

    public void setJsonString(String jsonString) {
        this.jsonString = jsonString;
    }

    public NewPostCreationPage.PostStatus getStatus() {
        return status;
    }

    public void setStatus(NewPostCreationPage.PostStatus status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean getJsonBoolean() {
        return jsonBoolean;
    }

    public void setJsonBoolean(boolean jsonBoolean) {
        this.jsonBoolean = jsonBoolean;
    }

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    public boolean getPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }
}
