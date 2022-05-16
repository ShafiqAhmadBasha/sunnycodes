package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utilities.ReportLog;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    private WebDriver driver = null;

    //property
    public  WebDriver getDriver(){
        return  driver;
    }

    //constructors
    public  BaseTest(){ }

    //testng annotations
    @BeforeSuite
    public void  beforeSuite(){
            launchApplication();
    }

    @BeforeMethod
    public void beforeMethod(){

    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        if (result.getStatus() == ITestResult.SUCCESS) { // Test passed with out any interruption
            ReportLog.PASS("Test Passed");
        } else if (result.getStatus() == ITestResult.FAILURE) {// test failed due to exception
            ReportLog.FAIL("Test Failed");
            ReportLog.FAIL(result.getThrowable().getLocalizedMessage());
        } else if (result.getStatus() == ITestResult.SKIP) { // test skipped for any reason
            ReportLog.WARNING("Test Skipped");
            ReportLog.FAIL(result.getSkipCausedBy().toString());
        }
        if (getDriver() != null) {
            ReportLog.PASS("Closing web driver");
            getDriver().close();
            getDriver().quit();
            ReportLog.PASS("Web driver is closed");
        }
        ReportLog.extentReport.endTest(ReportLog.extentTest);
        ReportLog.LOG("Test End");
    }

    @AfterSuite
    public void  afterSuite(){

    }

    //methods
    public void launchApplication(){
        String environment = System.getProperty("env");
        int implicitWait = Integer.valueOf(System.getProperty("implicitWait"));

        //initialise driver
        switch (environment.toUpperCase()){
            case "CHROME": driver = new ChromeDriver();
            break;
            case "FIREFOX":driver = new FirefoxDriver();
            break;
        }

        //add implicit wai and maximize browser
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
    }
}
