package ui.pagesactions;

import api.payload.Post;
import enums.PostStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.pages.NewPostCreationPage;
import ui.utils.DriverManager;

public class NewPostCreationPageActions extends ActionsBase{

    NewPostCreationPage newPostCreationPage;

    public NewPostCreationPageActions(){
        this.newPostCreationPage = new NewPostCreationPage(DriverManager.getDriver());
    }

    public void enterTitle(String title){
        newPostCreationPage.titleInput.sendKeys(title);
        webDriverWait.until(ExpectedConditions.attributeToBe(By.id("title"),"value",title));
    }

    public void selectStatus(PostStatus postStatus){
        webDriverWait.until(ExpectedConditions.elementToBeClickable(newPostCreationPage.statusDropdown)).click();
        switch (postStatus){
            case ACTIVE:
                DriverManager.getDriver().findElement(By.xpath("//div[contains(text(),'ACTIVE')]")).click();
                break;
            case REMOVED:
                DriverManager.getDriver().findElement(By.xpath("//div[contains(text(),'REMOVED')]")).click();
                break;
        }
    }

    public void clickCheckBox(){
        newPostCreationPage.publishedCheckbox.click();
    }

    public void selectPublisher(String publisherEmail){
        newPostCreationPage.publisherDropDown.click();
        DriverManager.getDriver().findElement(By.xpath("//div[contains(text(),'%s')]".formatted(publisherEmail))).click();
    }
    public PostPageActions clickSaveButton(){
        newPostCreationPage.saveButton.click();
        PostPageActions postPageActions = new PostPageActions();
        postPageActions.waitForPageToLoad();
        return postPageActions;
    }

    public void fillJsonNumber(int jsonNumber){
        By jsonNumberInputLocator = By.id("someJson.0.number");
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(jsonNumberInputLocator))
                .sendKeys(""+jsonNumber);
    }

    public void fillJsonString(String jsonString){
        By jsonStringLocator = By.id("someJson.0.string");
        try {
            webDriverWait.until(ExpectedConditions.presenceOfElementLocated(jsonStringLocator)).sendKeys(jsonString);
        }catch (StaleElementReferenceException e){
            webDriverWait.until(ExpectedConditions.presenceOfElementLocated(jsonStringLocator)).sendKeys(jsonString);
        }
    }

    public void clickJsonCheckbox(){
        By checkBoxLocator = By.cssSelector("section[data-testid='property-edit-someJson.0.boolean'] a");
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(checkBoxLocator)).click();
    }

    public void addJson(int jsonNumber,String jsonString,boolean jsonBoolean){
        newPostCreationPage.addNewJsonItem.click();
        fillJsonNumber(jsonNumber);
        fillJsonString(jsonString);
        if(jsonBoolean)
            clickJsonCheckbox();
    }

    public PostPageActions createNewPost(
            String title, PostStatus postStatus, boolean published, String publisherEmail){
        if(published)
            clickCheckBox();
        enterTitle(title);
        selectStatus(postStatus);
        selectPublisher(publisherEmail);
        return clickSaveButton();
    }

    public PostPageActions createNewPost(
            Post post, String publisherEmail){
        if(post.getPublished())
            clickCheckBox();
        enterTitle(post.getTitle());
        addJson(post.getJsonNumber(),post.getJsonString(),post.getJsonBoolean());
        selectStatus(post.getStatus());
        selectPublisher(publisherEmail);
        return clickSaveButton();
    }

    @Override
    public void waitForPageToLoad() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(newPostCreationPage.saveButton));
    }

    public void navigate() {
        DriverManager.getDriver().get(newPostCreationPage.getUrl());
        waitForPageToLoad();
    }
}
