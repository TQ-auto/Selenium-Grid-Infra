package ui.pagesactions;

import api.payload.Profile;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.pages.ProfilePage;

public class ProfilePageActions extends ActionsBase{

    ProfilePage profilePage;

    public ProfilePageActions(WebDriver driver) {
        super(driver);
        this.profilePage = new ProfilePage(driver);
    }

    public ProfilePageActions createNewProfile(Profile profileObject) {
        return clickCreateFirstRecordButton().createNewProfile(profileObject);
    }

    public NewProfileCreationPageActions clickCreateFirstRecordButton(){
        profilePage.createFirstRecordButtonHeader.click();
        NewProfileCreationPageActions newProfileCreationPageActions = new NewProfileCreationPageActions(driver);
        newProfileCreationPageActions.waitForPageToLoad();
        return newProfileCreationPageActions;
    }

    public ProfileShowPageActions clickOnLastAddedProfile(){
        orderProfileTableAsc();
        By lastRowClickableCellLocator = By.cssSelector("tbody tr:first-child td:nth-child(2)");
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(lastRowClickableCellLocator)).click();
        ProfileShowPageActions profileShowPageActions = new ProfileShowPageActions(driver);
        profileShowPageActions.waitForPageToLoad();
        return profileShowPageActions;
    }

    public void orderProfileTableAsc(){
        By orderByIdLocator = By.xpath(
                "//a[@href='/admin/resources/Profile?direction=desc&sortBy=id']");
        WebElement orderByIdElement = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(orderByIdLocator));
        orderByIdElement.click();
        webDriverWait.until(ExpectedConditions.attributeContains(
                orderByIdElement,"href","/admin/resources/Profile?direction=asc&sortBy=id"));
    }

    public String getBioOfProfileFromTable(int profileId){
        By profileRowLocator = By.cssSelector("tr[data-id='%s']".formatted(profileId));
        WebElement profileRow = driver.findElement(profileRowLocator);
        By bioLocator = By.cssSelector("section[data-testid='property-list-bio'");
        WebElement statusElement = profileRow.findElement(bioLocator);
        return statusElement.getText().strip();
    }

    @Override
    public void waitForPageToLoad() {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//a[@href='/admin/resources/Profile/actions/new']")));
    }

    public void navigate() {
        driver.get(profilePage.getUrl());
        waitForPageToLoad();
    }
}
