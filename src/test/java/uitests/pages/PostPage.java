package uitests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class PostPage extends BasePage {

    private final String url = baseUrl + "/resources/Post";

    // The button in the ActionHeader section
    @FindBy(how = How.XPATH, using = "//a[@href='/admin/resources/Post/actions/new']")
    WebElement createFirstRecordButtonHeader;

    By selectAllCheckBox = By.xpath("//td[@data-css='Publisher-checkbox-table-cell']//a");


    public PostPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public NewPostCreationPage clickCreateFirstRecordButton(){
        createFirstRecordButtonHeader.click();

        NewPostCreationPage newPostCreationPage = new NewPostCreationPage(driver);
        newPostCreationPage.waitForPageToLoad();
        return newPostCreationPage;
    }

    public boolean verifyPostAppearsInTable(String title){
        String xpathPostVerification =
                "//section[@data-testid='property-list-title' and contains(text(),'%s')]".formatted(title);

        return driver.findElements(By.xpath(xpathPostVerification)).size() == 1;
    }

    public void waitUntilSuccessMessageDisappears(){
        webdriverWait.until(
                ExpectedConditions.invisibilityOfElementLocated(
                        By.xpath("//div[contains(text(),'Successfully')]")
                )
        );
    }

    public PostShowPage clickOnLastAddedPost(){
        By lastRowClickableCellLocator = By.cssSelector("tbody tr:last-child td:nth-child(2)");
        webdriverWait.until(ExpectedConditions.presenceOfElementLocated(lastRowClickableCellLocator)).click();
        PostShowPage postShowPage = new PostShowPage(driver);
        postShowPage.waitForPageToLoad();
        return postShowPage;
    }

    public String getStatusOfPostFromTable(String postId){
        By postRowLocator = By.cssSelector("tr[data-id='%s']".formatted(postId));
        WebElement postRow = driver.findElement(postRowLocator);
        By statusLocator = By.cssSelector("section[data-testid='property-list-status'");
        WebElement statusElement = postRow.findElement(statusLocator);
        return statusElement.getText().strip();
    }

    @Override
    public String getUrl(){
        return url;
    }

    @Override
    public void waitForPageToLoad() {
        By createNewPostButtonLocator = By.xpath("//a[@href='/admin/resources/Post/actions/new']");
        webdriverWait.until(
                ExpectedConditions
                        .elementToBeClickable(
                                createNewPostButtonLocator));
    }

    public void navigate() {
        driver.get(url);
        waitForPageToLoad();
    }


}
