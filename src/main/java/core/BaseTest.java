package core;

import com.github.dockerjava.api.model.Driver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utilities.Common;
import utilities.ReportLog;


import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
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
        Common.laodEnvData();
        ReportLog.initializeReport();
    }

    @BeforeMethod
    public void beforeMethod(Method method){
        ReportLog.startTest(method.getName());
        initializeDriver();
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
      ReportLog.closeResport();
    }

    //methods
    //initialize driver to open browser
    public void initializeDriver(){
        String browser = System.getProperty("browserName");
        int implicitWait = Integer.valueOf(System.getProperty("implicitWait"));

        //initialise driver
        switch (browser.toUpperCase()){
            case "CHROME":
                ChromeOptions chromeOptions = new ChromeOptions();
                WebDriverManager.chromedriver().browserVersion(System.getProperty("browserVersion")).setup();
                driver = new ChromeDriver(chromeOptions);
            break;
            case "FIREFOX":driver = new FirefoxDriver();
            break;
        }

        //add implicit wai and maximize browser
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
        ReportLog.PASS(browser + " browser is intialized successfully");
    }

    //launch application and return generic object
    public <T extends BasePage>T launchApplication(Class cls){
        driver.get(Common.getEnvData("appurl"));
        return  getGenericObject(cls);
    }

    //generic method
    public <T extends BasePage>T getGenericObject(Class cls){
        T t = null;
        try{
            Constructor<?> con = cls.getDeclaredConstructor(WebDriver.class);
            Object obj = con.newInstance(driver);
            t = (T)obj;

        }catch (Exception ex){
            System.out.println("Unable to initialize Object " + cls.getTypeName());
        }
        return t;
    }
}
