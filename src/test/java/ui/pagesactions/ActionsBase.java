package ui.pagesactions;

import org.openqa.selenium.support.ui.WebDriverWait;
import ui.utils.DriverManager;
import java.time.Duration;

public abstract class ActionsBase {

    WebDriverWait webDriverWait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10));

    public abstract void waitForPageToLoad();
}
