package ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ProfileEditPage extends NewProfileCreationPage{

    private final String url = baseUrl + "/resources/Profile/records/%s/edit";

    public ProfileEditPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    @Override
    public String getUrl() {
        return url;
    }
}
