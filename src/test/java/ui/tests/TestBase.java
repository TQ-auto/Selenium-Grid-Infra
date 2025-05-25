package ui.tests;

import org.apache.commons.io.FileUtils;
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
import java.util.Arrays;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class TestBase {

    String url = "http://localhost:3000/admin/";
    String adminTestEmail = "admin@example.com";
    String adminPassword = "password";
    WebDriverWait webdriverWait;
    Logger logger;

    @BeforeTest
    @Parameters("browser")
    protected void setupTestBase(@Optional("chrome")String browser) throws Exception {
        setLogger();
        DriverManager.initializeDriverToRunLocally(browser);
        if(getDriver() == null){
            throw new Exception("Driver was not initialized");
        }
        webdriverWait = new WebDriverWait(getDriver(),Duration.ofSeconds(5));
        getDriver().get(url);
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

    private static void takeScreenshot(WebDriver driver, String testName) throws IOException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy,HH-mm-ss-SSS");
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile,
                new File(
                        String.format("%s\\screenshots\\%s-%s.png",
                                System.getProperty("user.dir"),
                                java.time.LocalDateTime.now().atZone(ZoneId.systemDefault()).format(dateTimeFormatter),
                                testName)
                ));
    }

    private void setLogger() throws IOException {
        logger = Logger.getLogger("");
        logger.setLevel(Level.INFO);
        Arrays.stream(logger.getHandlers()).forEach(handler -> handler.setLevel(Level.INFO));
        Handler handler = new FileHandler("selenium.xml");
        logger.addHandler(handler);
    }
}
