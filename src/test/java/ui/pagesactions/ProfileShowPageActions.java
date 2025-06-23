package ui.pagesactions;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.pages.ProfileShowPage;
import ui.utils.DriverManager;

public class ProfileShowPageActions extends ActionsBase{

    ProfileShowPage profileShowPage;

    public ProfileShowPageActions(){
        this.profileShowPage = new ProfileShowPage(DriverManager.getDriver());
    }

    public String getProfileId(){
        return profileShowPage.profileId.getText().replace("#","").strip();
    }

    public ProfileEditPageActions clickEditButton(){
        profileShowPage.editButton.click();
        ProfileEditPageActions profileEditPageActions = new ProfileEditPageActions();
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
        DriverManager.getDriver().get(profileShowPage.getUrl().formatted(profileId));
        waitForPageToLoad();
    }
}
