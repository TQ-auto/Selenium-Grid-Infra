package ui.pagesactions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.pages.bars.TopBar;

public class TopBarActions extends ActionsBase {

    TopBar topBar;

    public TopBarActions(WebDriver driver){
        super(driver);
        this.topBar = new TopBar(driver);
    }
    public LeftSideBarActions openMenu(){
        topBar.burgerMenuButton.click();
        LeftSideBarActions leftSideBarActions = new LeftSideBarActions(driver);
        leftSideBarActions.waitForPageToLoad();
        return leftSideBarActions;
    }

    public void waitForPageToLoad() {
        By burgerButtonLocator= By.xpath("//section[@data-css='topbar']/section[1]");
        webDriverWait.until(
                ExpectedConditions.elementToBeClickable(burgerButtonLocator)
        );
    }
}
