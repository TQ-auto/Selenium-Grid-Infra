package api.payload;

public class Profile {

    int profileId;
    String bio;
    String publisherEmail;
    String publisherId;

    public Profile(int profileId, String bio, String publisherEmail, String publisherId){
        this.profileId = profileId;
        this.bio = bio;
        this.publisherEmail = publisherEmail;
        this.publisherId = publisherId;
    }

    public Profile(String bio,String publisherEmail){
        this.bio = bio;
        this.publisherEmail = publisherEmail;
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getPublisherEmail() {
        return publisherEmail;
    }

    public void setPublisherEmail(String publisherEmail) {
        this.publisherEmail = publisherEmail;
    }

    public String getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(String publisherId) {
        this.publisherId = publisherId;
    }
}
