package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PostEditPage extends NewPostCreationPage{

    private final String url = baseUrl + "/resources/Post/records/%s/edit";

    protected PostEditPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public void waitForPageToLoad() {

    }

    public void navigate(String postId) {
        driver.get(url.formatted(postId));
        waitForPageToLoad();
    }
}
