package ui.pagesactions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.pages.ProfileShowPage;

public class ProfileShowPageActions extends ActionsBase{

    ProfileShowPage profileShowPage;

    public ProfileShowPageActions(WebDriver driver){
        super(driver);
        this.profileShowPage = new ProfileShowPage(driver);
    }

    public String getProfileId(){
        return profileShowPage.profileId.getText().replace("#","").strip();
    }

    public ProfileEditPageActions clickEditButton(){
        profileShowPage.editButton.click();
        ProfileEditPageActions profileEditPageActions = new ProfileEditPageActions(driver);
        profileEditPageActions.waitForPageToLoad();
        return profileEditPageActions;
    }

    @Override
    public void waitForPageToLoad() {
        webDriverWait.until(
                ExpectedConditions
                        .presenceOfElementLocated(
                                By.cssSelector("a[data-testid='action-edit']")
                        )
        );
    }

    public void navigate(String profileId) {
        driver.get(profileShowPage.getUrl().formatted(profileId));
        waitForPageToLoad();
    }
}
