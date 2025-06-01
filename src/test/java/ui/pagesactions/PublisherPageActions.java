package ui.pagesactions;

import api.payload.Publisher;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.pages.PublisherPage;
import ui.utils.DriverManager;

public class PublisherPageActions extends ActionsBase {

    PublisherPage publisherPage;

    public PublisherPageActions(){
        publisherPage = new PublisherPage(DriverManager.getDriver());
    }


    public NewPublisherCreationPageActions clickCreateFirstRecordButton(){
        publisherPage.createFirstRecordButtonHeader.click();
        NewPublisherCreationPageActions newPublisherCreationPageActions = new NewPublisherCreationPageActions();
        newPublisherCreationPageActions.waitForPageToLoad();
        return newPublisherCreationPageActions;
    }

    public PublisherPageActions createNewPublisher(Publisher publisherObject) {
        return clickCreateFirstRecordButton().createNewPublisher(publisherObject);
    }

    public boolean verifyEmailAppearsInTable(String email){
        By xpathEmailCell = By.xpath("//section[@data-testid='property-list-email']");
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(xpathEmailCell));

        String xpathEmailVerification = "//section[@data-testid='property-list-email' and contains(text(),'%s')]".formatted(email);
        return DriverManager.getDriver().findElements(By.xpath(xpathEmailVerification)).size() == 1;
    }

    public void waitUntilSuccessMessageDisappears(){
        webDriverWait.until(
                ExpectedConditions.invisibilityOfElementLocated(
                        By.xpath("//div[contains(text(),'Successfully')]")
                )
        );
    }

    public void clickCloseSuccessMessage(){
        By closeButton = By.xpath("//div[@data-testid='notice-wrapper']//button//span");
        webDriverWait.until(ExpectedConditions.elementToBeClickable(closeButton)).click();
        waitUntilSuccessMessageDisappears();
    }

    public PostPageActions goToPostPage(){
        TopBarActions topBarActions = new TopBarActions();
        return topBarActions.openMenu().openHappyFolder().clickPostButton();
    }

    @Override
    public void waitForPageToLoad() {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//a[@href='/admin/resources/Publisher/actions/new']")));
    }

    public void navigate() {
        DriverManager.getDriver().get(publisherPage.getUrl());
        waitForPageToLoad();
    }
}
