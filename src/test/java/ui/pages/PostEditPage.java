package ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PostEditPage extends NewPostCreationPage{

    private final String url = baseUrl + "/resources/Post/records/%s/edit";

    public PostEditPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    @Override
    public String getUrl() {
        return url;
    }
}
