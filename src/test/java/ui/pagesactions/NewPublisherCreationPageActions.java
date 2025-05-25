package ui.pagesactions;

import api.payload.Publisher;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.pages.NewPublisherCreationPage;
import ui.utils.DriverManager;

public class NewPublisherCreationPageActions extends ActionsBase{

    NewPublisherCreationPage newPublisherCreationPage;

    public NewPublisherCreationPageActions(){
        this.newPublisherCreationPage = new NewPublisherCreationPage(DriverManager.getDriver());
    }

    public void enterName(String name){
        newPublisherCreationPage.nameInput.sendKeys(name);
    }

    public void enterEmail(String email){
        newPublisherCreationPage.emailInput.sendKeys(email);
    }

    public PublisherPageActions clickSaveButton(){
        newPublisherCreationPage.saveButton.click();
        PublisherPageActions publisherPageActions = new PublisherPageActions();
        publisherPageActions.waitForPageToLoad();
        return publisherPageActions;
    }

    public PublisherPageActions createNewPublisher(Publisher publisher){
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
        DriverManager.getDriver().get(newPublisherCreationPage.getUrl());
        waitForPageToLoad();
    }
}
