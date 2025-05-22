package api.payload;

public class Publisher {

    int publisherId;
    String publisherName;
    String publisherEmail;

    public Publisher(String publisherName, String publisherEmail) {
        this.publisherName = publisherName;
        this.publisherEmail = publisherEmail;
    }

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getPublisherEmail() {
        return publisherEmail;
    }

    public void setPublisherEmail(String publisherEmail) {
        this.publisherEmail = publisherEmail;
    }
}
