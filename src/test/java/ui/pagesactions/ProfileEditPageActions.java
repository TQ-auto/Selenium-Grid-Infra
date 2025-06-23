package ui.pagesactions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.pages.ProfileEditPage;

public class ProfileEditPageActions extends NewProfileCreationPageActions{

    ProfileEditPage profileEditPage;

    public ProfileEditPageActions(WebDriver driver){
        super(driver);
        this.profileEditPage = new ProfileEditPage(driver);
    }

    public ProfilePageActions changeProfileBio(String bio){
        profileEditPage.bioInput.clear();
        profileEditPage.bioInput.sendKeys(bio);
        webDriverWait.until(ExpectedConditions.attributeContains(newProfileCreationPage.bioInput,"value",bio));
        return clickSaveButton();
    }

    public ProfilePageActions changeProfilePublisher(String publisherEmail){
        selectPublisher(publisherEmail);
        return clickSaveButton();
    }

    public void navigate(String profileId) {
        driver.get(profileEditPage.getUrl().formatted(profileId));
        waitForPageToLoad();
    }
}
