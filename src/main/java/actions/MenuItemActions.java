package actions;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.CreateMenuItemPage;
import utilities.ReportLog;

public class MenuItemActions extends CreateMenuItemPage {


    public MenuItemActions(WebDriver driver) {
        super(driver);
    }

    public void verifySuccessMessage() {
        String message = BasePage.SuccesMsg(getDriver(), "Menu Item  success message");
        Assert.assertTrue(message.equalsIgnoreCase("MenuItem Inserted Successfully"), "Success message is not matched");
        ReportLog.PASS("Menu Item inserted successfully message validated Successfully");
    }


}