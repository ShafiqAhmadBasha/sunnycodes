package actions;

import org.openqa.selenium.WebDriver;
import pages.LoginPage;

public class LoginActions extends LoginPage {
    //constructors
    public LoginActions(WebDriver driver){
        super(driver);
    }

    //method
    public void performUserLogin(String userName, String password){
        //code to log into application
    }
}
