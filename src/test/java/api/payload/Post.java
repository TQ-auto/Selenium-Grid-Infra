package api.payload;

public class Post {

    int postId;
    String title;
    String status;
    String content;
    int jsonNumber;
    String jsonString;
    String jsonBoolean;
    int publisherId;
    String published;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getJsonBoolean() {
        return jsonBoolean;
    }

    public void setJsonBoolean(String jsonBoolean) {
        this.jsonBoolean = jsonBoolean;
    }

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }
}
