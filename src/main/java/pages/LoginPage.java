package pages;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    //locators
    protected final By txtUserName = By.id("txtUserID");
    protected final By txtPassword = By.id("txtPassword");
    protected final By btnLogin = By.xpath("//input[@type='submit']");

    //constructors
    public LoginPage(WebDriver driver){
        super(driver);
    }

    //abstract method implementation
    public void verifyPageLoad(){
            //add code to verify login button visibility
    }


}
