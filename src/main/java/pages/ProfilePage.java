package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ProfilePage extends BasePage {

    private final String url = baseUrl + "/resources/Profile";

    public ProfilePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public void waitForPageToLoad() {
        //@todo
    }

    public void navigate() {
        driver.get(url);
        waitForPageToLoad();
    }
}
