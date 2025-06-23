package ui.pagesactions;

import api.payload.Publisher;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.pages.NewPublisherCreationPage;

public class NewPublisherCreationPageActions extends ActionsBase{

    NewPublisherCreationPage newPublisherCreationPage;

    public NewPublisherCreationPageActions(WebDriver driver){
        super(driver);
        this.newPublisherCreationPage = new NewPublisherCreationPage(driver);
    }

    public void enterName(String name){
        newPublisherCreationPage.nameInput.sendKeys(name);
        webDriverWait.until(ExpectedConditions.attributeContains(newPublisherCreationPage.nameInput,"value",name));
    }

    public void enterEmail(String email){
        newPublisherCreationPage.emailInput.sendKeys(email);
        webDriverWait.until(ExpectedConditions.attributeContains(newPublisherCreationPage.emailInput,"value",email));
    }

    public PublisherPageActions clickSaveButton(){
        newPublisherCreationPage.saveButton.click();
        PublisherPageActions publisherPageActions = new PublisherPageActions(driver);
        publisherPageActions.waitForPageToLoad();
        return publisherPageActions;
    }

    public PublisherPageActions createNewPublisher(Publisher publisher) {
        enterName(publisher.getPublisherName());
        enterEmail(publisher.getPublisherEmail());
        return clickSaveButton();
    }

    @Override
    public void waitForPageToLoad() {
        webDriverWait.until(
                ExpectedConditions
                        .elementToBeClickable(
                                By.xpath("//button[@data-testid='button-save']")
                        )
        );
    }

    public void navigate() {
        driver.get(newPublisherCreationPage.getUrl());
        waitForPageToLoad();
    }
}
