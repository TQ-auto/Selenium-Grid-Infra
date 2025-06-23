package ui.pagesactions;

import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.pages.ProfileEditPage;
import ui.utils.DriverManager;

public class ProfileEditPageActions extends NewProfileCreationPageActions{

    ProfileEditPage profileEditPage;

    public ProfileEditPageActions(){
        this.profileEditPage = new ProfileEditPage(DriverManager.getDriver());
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
        DriverManager.getDriver().get(profileEditPage.getUrl().formatted(profileId));
        waitForPageToLoad();
    }
}
