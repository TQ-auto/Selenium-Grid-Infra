package ui.pages;

import api.payload.Post;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class NewPostCreationPage extends BasePage{

    private final String url = baseUrl + "/resources/Post/actions/new";

    @FindBy(how = How.ID, using = "title")
    WebElement titleInput;

    @FindBy(how = How.CSS, using = "button[data-testid='someJson-add']")
    WebElement addNewJsonItem;

    @FindBy(how = How.XPATH, using = "//section[@data-testid='property-edit-status']//input")
    WebElement statusDropdown;

    @FindBy(how = How.CLASS_NAME, using = "adminjs_Checkbox")
    WebElement publishedCheckbox;

    @FindBy(how = How.XPATH, using = "//section[@data-testid='property-edit-publisher']//input")
    WebElement publisherDropDown;

    @FindBy(how = How.XPATH, using = "//button[@data-testid='button-save']")
    WebElement saveButton;

    WebElement publisherMenu;

    public NewPostCreationPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
    }


    public void enterTitle(String title){
        titleInput.sendKeys(title);
        webdriverWait.until(ExpectedConditions.attributeToBe(By.id("title"),"value",title));
    }

    public void selectStatus(PostStatus postStatus){
        statusDropdown.click();
        switch (postStatus){
            case ACTIVE:
                driver.findElement(By.xpath("//div[contains(text(),'ACTIVE')]")).click();
                break;
            case REMOVED:
                driver.findElement(By.xpath("//div[contains(text(),'REMOVED')]")).click();
                break;
        }
    }

    public void clickCheckBox(){
        publishedCheckbox.click();
    }

    public void selectPublisher(String publisherEmail){
        publisherDropDown.click();
        driver.findElement(By.xpath("//div[contains(text(),'%s')]".formatted(publisherEmail))).click();
    }
    public PostPage clickSaveButton(){
        saveButton.click();
        PostPage postPage = new PostPage(driver);
        postPage.waitForPageToLoad();
        return postPage;
    }

    public void fillJsonNumber(int jsonNumber){
        By jsonNumberInputLocator = By.id("someJson.0.number");
        webdriverWait.until(ExpectedConditions.presenceOfElementLocated(jsonNumberInputLocator))
                .sendKeys(""+jsonNumber);
    }

    public void fillJsonString(String jsonString){
        By jsonStringLocator = By.id("someJson.0.string");
        try {
            webdriverWait.until(ExpectedConditions.presenceOfElementLocated(jsonStringLocator)).sendKeys(jsonString);
        }catch (StaleElementReferenceException e){
            webdriverWait.until(ExpectedConditions.presenceOfElementLocated(jsonStringLocator)).sendKeys(jsonString);
        }
    }

    public void clickJsonCheckbox(){
        By checkBoxLocator = By.cssSelector("section[data-testid='property-edit-someJson.0.boolean'] a");
        webdriverWait.until(ExpectedConditions.presenceOfElementLocated(checkBoxLocator)).click();
    }

    public void addJson(int jsonNumber,String jsonString,boolean jsonBoolean){
        addNewJsonItem.click();
        fillJsonNumber(jsonNumber);
        fillJsonString(jsonString);
        if(jsonBoolean)
            clickJsonCheckbox();
    }

    public PostPage createNewPost(
            String title, PostStatus postStatus, boolean published, String publisherEmail){
        if(published)
            clickCheckBox();
        enterTitle(title);
        selectStatus(postStatus);
        selectPublisher(publisherEmail);
        return clickSaveButton();
    }

    public PostPage createNewPost(
            Post post, String publisherEmail){
        if(post.getPublished())
            clickCheckBox();
        enterTitle(post.getTitle());
        addJson(post.getJsonNumber(),post.getJsonString(),post.getJsonBoolean());
        selectStatus(post.getStatus());
        selectPublisher(publisherEmail);
        return clickSaveButton();
    }

    public String getUrl(){
        return url;
    }

    @Override
    public void waitForPageToLoad() {
        webdriverWait.until(ExpectedConditions.elementToBeClickable(saveButton));
    }

    public void navigate() {
        driver.get(url);
        waitForPageToLoad();
    }

    public enum PostStatus {
        ACTIVE,
        REMOVED
    }
}


