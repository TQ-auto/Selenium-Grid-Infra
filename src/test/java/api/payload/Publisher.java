package api.payload;

public class Publisher {

    String publisherName;
    String publisherEmail;

    public Publisher(String publisherName, String publisherEmail) {
        this.publisherName = publisherName;
        this.publisherEmail = publisherEmail;
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
