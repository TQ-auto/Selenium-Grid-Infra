package ui.tests;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;
import ui.utils.DriverManager;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public abstract class TestBase {

    WebDriverWait webdriverWait;
    public Logger logger; //Log4j

    @BeforeTest
    @Parameters("browser")
    protected void setupTestBase(@Optional("chrome")String browser) throws Exception {
        logger = LogManager.getLogger(this.getClass());
        logger.info("****** Running test on browser: %s ******".formatted(browser));
        DriverManager.initializeDriver(browser);
        if(getDriver() == null){
            throw new Exception("Driver was not initialized");
        }
        webdriverWait = new WebDriverWait(getDriver(),Duration.ofSeconds(5));
    }

    @AfterMethod
    protected void takeScreenshotsOfFailedMethod(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE){
            takeScreenshot(getDriver(), result.getName());
        }
    }

    @AfterTest
    protected void wrapUp(){
        getDriver().quit();
    }

    public WebDriver getDriver(){
        return DriverManager.getDriver();
    }

    private void takeScreenshot(WebDriver driver, String testName) throws IOException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy,HH-mm-ss-SSS");
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String fileName =
                String.format("%s\\screenshots\\%s-%s.png",
                System.getProperty("user.dir"),
                java.time.LocalDateTime.now().atZone(ZoneId.systemDefault()).format(dateTimeFormatter),
                testName);
        FileUtils.copyFile(scrFile,new File(fileName));
    }
}
