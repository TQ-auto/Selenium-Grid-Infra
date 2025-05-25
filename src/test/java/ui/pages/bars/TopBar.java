package ui.pages.bars;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class TopBar {

    WebDriver driver;

    @FindBy(how = How.XPATH,using = "//section[@data-css='topbar']/section[1]")
    public WebElement burgerMenuButton; // Burger menu button appears only on minimized browser view

    public TopBar(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
}
