package core;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import utilities.ReportLog;

import java.lang.reflect.Constructor;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public abstract class BasePage {

    private WebDriver driver = null;
    private String byMenu = "//a[text()='%s']";
    private By btnAddNew = By.xpath("//a[text()='ADD NEW']");

    private static By successmessage= By.xpath("//div[contains(@style,'green')]");

    //property
    public WebDriver getDriver() {
        return driver;
    }

    //constructors
    public BasePage() {
    }

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    //abstract methods
    public abstract void verifyPageLoad();

    //methods

    //generic method
    public <T extends BasePage> T getGenericObject(Class cls) {
        T t = null;
        try {
            Constructor<?> con = cls.getDeclaredConstructor(WebDriver.class);
            Object obj = con.newInstance(driver);
            t = (T) obj;

        } catch (Exception ex) {
            System.out.println("Unable to initialize Object " + cls.getTypeName());
        }
        return t;
    }

    protected WebElement getElement(By locator, String controlName) {
        WebElement element = driver.findElement(locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.border='2px solid red'", element);
        return element;
    }

    protected void enterText(By locator, String content, String controlName) {
        WebElement element = getElement(locator, controlName);
        element.clear();
        element.sendKeys(content);
        ReportLog.LOG("Entered " + content + " into " + controlName);
    }

    protected void click(By locator, String controlName) {
        WebElement element = getElement(locator, controlName);
        element.click();
        ReportLog.LOG("Clicked on " + controlName);
    }

    protected void selectDropdownOptionByText(By locator, String option, String controlName) {
        WebElement element = getElement(locator, controlName);
        Select select = new Select(element);
        select.selectByVisibleText(option);
        ReportLog.LOG("Selected " + controlName + " option as " + option);
    }

    protected void acceptAlert() {
        driver.switchTo().alert().accept();
    }

    protected void switchToWindowByTitle(String title) {
        Set<String> handles = driver.getWindowHandles();
        for (String eachWindow : handles) {
            driver.switchTo().window(eachWindow);
            if (driver.getTitle().equals(title)) {
                break;
            }
        }
        ReportLog.LOG("Switched to window : " + title);
    }

    public <T extends BasePage> T selectMenu(String menu, String option, Class cls) {
        By by = By.xpath(String.format(byMenu, menu));
        click(by, menu);

        by = By.xpath(String.format(byMenu, option));
        click(by, option);

        return getGenericObject(cls);
    }

    public <T extends BasePage> T clickAddNew(Class cls) {
        click(btnAddNew, "Add New");

        return getGenericObject(cls);
    }



        // function to generate a random string of length n
        public String getAlphaNumericString(int n) {

            // chose a Character random from this String
            String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                    + "0123456789"
                    + "abcdefghijklmnopqrstuvxyz";

            // create StringBuffer size of AlphaNumericString
            StringBuilder sb = new StringBuilder(n);

            for (int i = 0; i < n; i++) {

                // generate a random number between
                // 0 to AlphaNumericString variable length
                int index
                        = (int) (AlphaNumericString.length()
                        * Math.random());

                // add Character one by one in end of sb
                sb.append(AlphaNumericString
                        .charAt(index));
            }
            return sb.toString();
        }
    public static String SuccesMsg(WebDriver driver, String title){
        WebElement successmsg = driver.findElement(successmessage);
        Assert.assertNotNull(successmsg, "Success element is ot displayed");
        return successmsg.getText();

    }

}


