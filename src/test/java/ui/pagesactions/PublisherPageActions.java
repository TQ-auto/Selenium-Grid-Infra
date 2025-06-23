package ui.pagesactions;

import api.payload.Publisher;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.pages.PublisherPage;

public class PublisherPageActions extends ActionsBase {

    PublisherPage publisherPage;

    public PublisherPageActions(WebDriver driver){
        super(driver);
        publisherPage = new PublisherPage(driver);
    }


    public NewPublisherCreationPageActions clickCreateFirstRecordButton(){
        publisherPage.createFirstRecordButtonHeader.click();
        NewPublisherCreationPageActions newPublisherCreationPageActions = new NewPublisherCreationPageActions(driver);
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
        return driver.findElements(By.xpath(xpathEmailVerification)).size() == 1;
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

    @Override
    public void waitForPageToLoad() {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//a[@href='/admin/resources/Publisher/actions/new']")));
    }

    public void navigate() {
        driver.get(publisherPage.getUrl());
        waitForPageToLoad();
    }
}
