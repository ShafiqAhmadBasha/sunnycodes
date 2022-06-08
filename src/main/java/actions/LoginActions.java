package actions;

import org.openqa.selenium.WebDriver;
import pages.LoginPage;

public class LoginActions extends LoginPage {
    //constructors
    public LoginActions(WebDriver driver){
        super(driver);
    }

    //method
    public DashboardActions performUserLogin(String userName, String password){
       enterText(txtUserName, userName, "UserName textbox");
       enterText(txtPassword, password, "Password textbox");
       click(btnLogin, "Loign button");
        return new DashboardActions(getDriver());
    }
}
