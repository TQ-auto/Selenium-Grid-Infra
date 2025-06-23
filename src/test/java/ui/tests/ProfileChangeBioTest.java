package ui.tests;

import api.payload.Profile;
import api.payload.Publisher;
import generalutils.TestUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import ui.pagesactions.*;

import java.io.IOException;

import static generalutils.TestUtils.*;

/**
 * Test Scenario 1 - UI (POM)
 * Step 1: Create Publisher entity
 * Step 2: Create Profile entity
 * Step 3: Link to the Publisher created
 * Step 4: Edit Profile - Change bio
 * Step 5: Save
 * Step 6: Validate profile bio was changed
 */

public class ProfileChangeBioTest extends TestBase {

    @Test
    public void profileChangeBioTest() throws IOException {

        logger.info("****** Starting ProfileChangeBioTest ******");

        String adminTestEmail = getPropertyValueFromPropertiesFile("adminTestEmail");
        String adminPassword = getPropertyValueFromPropertiesFile("adminPassword");

        logger.info("Login to admin page...");
        AdminPageActions adminPageActions = new LoginPageActions(driver).navigate().login(adminTestEmail,adminPassword);

        logger.info("Navigate to publisher page...");
        PublisherPageActions publisherPageActions =
                adminPageActions.goToPublisherPage();

        logger.info("Create new publisher...");
        Publisher publisherObject = getGeneratedPublisherDetails();
        publisherPageActions.createNewPublisher(publisherObject);
        publisherPageActions.clickCloseSuccessMessage();

        logger.info("Navigate to profile page...");
        ProfilePageActions profilePageActions =
                publisherPageActions.goToProfilePage();

        logger.info("Create new profile...");
        Profile profileObject = getGeneratedProfileDetails(publisherObject.getPublisherEmail());
        profilePageActions = profilePageActions.createNewProfile(profileObject);

        logger.info("Choose last added profile from table...");
        ProfileShowPageActions profileShowPageActions = profilePageActions.clickOnLastAddedProfile();
        logger.info("Change bio from profile page...");
        profileObject.setProfileId(Integer.parseInt(profileShowPageActions.getProfileId()));
        String newBio = "test" + TestUtils.getEpochMili();
        profilePageActions = profileShowPageActions.clickEditButton().changeProfileBio(newBio);
        profilePageActions.orderProfileTableAsc();

        //VALIDATE PROFILE BIO CHANGED IN PROFILE PAGE
        Assert.assertEquals(
                profilePageActions.getBioOfProfileFromTable(profileObject.getProfileId()),
                newBio,
                "Profile bio was not changed.");
    }
}
