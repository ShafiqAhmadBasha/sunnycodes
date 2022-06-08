package core;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import utilities.ReportLog;

import java.lang.reflect.Constructor;
import java.util.Set;
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

    protected WebElement getElement(By locator, String controlName){
        WebElement element = driver.findElement(locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.border='2px solid red'", element);
        return  element;
    }

    protected  void enterText(By locator, String content, String controlName){
        WebElement element= getElement(locator, controlName);
        element.clear();
        element.sendKeys(content);
        ReportLog.LOG("Entered " + content + " into " + controlName);
    }

    protected void click(By locator, String controlName){
        WebElement element= getElement(locator, controlName);
        element.click();
        ReportLog.LOG("Clicked on " + controlName);
    }

    protected  void selectDropdownOptionByText(By locator, String option, String controlName){
        WebElement element= getElement(locator, controlName);
        Select select = new Select(element);
        select.selectByVisibleText(option);
        ReportLog.LOG("Selected "+ controlName + " option as " + option);
    }

    protected void acceptAlert(){
        driver.switchTo().alert().accept();
    }

    protected void switchToWindowByTitle(String title){
        Set<String> handles = driver.getWindowHandles();
        for(String eachWindow : handles){
            driver.switchTo().window(eachWindow);
            if(driver.getTitle().equals(title)){
                break;
            }
        }
        ReportLog.LOG("Switched to window : " + title);
    }
}
