package ui.pagesactions;


import api.payload.Profile;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.pages.NewProfileCreationPage;

public class NewProfileCreationPageActions extends ActionsBase {

    NewProfileCreationPage newProfileCreationPage;

    public NewProfileCreationPageActions(WebDriver driver){
        super(driver);
        newProfileCreationPage = new NewProfileCreationPage(driver);
    }

    public void enterBio(String bio){
        newProfileCreationPage.bioInput.sendKeys(bio);
        webDriverWait.until(ExpectedConditions.attributeContains(newProfileCreationPage.bioInput,"value",bio));
    }

    public void selectPublisher(String publisherEmail){
        newProfileCreationPage.publisherDropDown.click();
        driver.findElement(By.xpath("//div[contains(text(),'%s')]".formatted(publisherEmail))).click();
    }

    public ProfilePageActions clickSaveButton(){
        newProfileCreationPage.saveButton.click();
        ProfilePageActions profilePageActions = new ProfilePageActions(driver);
        profilePageActions.waitForPageToLoad();
        return profilePageActions;
    }

    public ProfilePageActions createNewProfile(Profile profile) {
        enterBio(profile.getBio());
        selectPublisher(profile.getPublisherEmail());
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
        driver.get(newProfileCreationPage.getUrl());
        waitForPageToLoad();
    }
}
