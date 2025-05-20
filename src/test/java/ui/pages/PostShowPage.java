package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PostShowPage extends BasePage{

    String url = baseUrl + "/resources/Post/records/%s/show";

    @FindBy(how = How.CSS, using = "section[data-testid='property-show-id'] section")
    WebElement postId;

    @FindBy(how = How.CSS, using = "a[data-testid='action-edit']")
    WebElement editButton;

    public PostShowPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public String getPostId(){
        return postId.getText().replace("#","").strip();
    }

    public PostEditPage clickEditButton(){
        editButton.click();
        PostEditPage postEditPage = new PostEditPage(driver);
        postEditPage.waitForPageToLoad();
        return postEditPage;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public void waitForPageToLoad() {
        webdriverWait.until(
                ExpectedConditions
                        .presenceOfElementLocated(
                                By.cssSelector("a[data-testid='action-edit']")
                        )
        );
    }


    public void navigate(String postId) {
        driver.get(url.formatted(postId));
        waitForPageToLoad();
    }
}
