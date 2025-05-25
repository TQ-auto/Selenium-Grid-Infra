package ui.pagesactions;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.pages.bars.TopBar;
import ui.utils.DriverManager;

public class TopBarActions extends ActionsBase {

    TopBar topBar;

    public TopBarActions(){
        this.topBar = new TopBar(DriverManager.getDriver());
    }
    public LeftSideBarActions openMenu(){
        topBar.burgerMenuButton.click();
        LeftSideBarActions leftSideBarActions = new LeftSideBarActions();
        leftSideBarActions.waitForPageToLoad();
        return leftSideBarActions;
    }

    public void waitForPageToLoad() {
        webDriverWait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//section[@data-css='topbar']/section[1]")));
    }
}
