package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.lang.reflect.Constructor;
import java.util.concurrent.TimeUnit;

public abstract class BasePage {

    private WebDriver driver = null;

    //property
    public  WebDriver getDriver(){
        return  driver;
    }

    //constructors
    public  BasePage(){ }

    public BasePage(WebDriver driver){
        this.driver = driver;
    }

    //abstract methods
    public abstract void verifyPageLoad();

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

        //add implicit wait and maximize browser
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
    }

    public <T extends BasePage>T launchApplication(Class cls, String applicationName){

        return getGenericObject(cls);
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
