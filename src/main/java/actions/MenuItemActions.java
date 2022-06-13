package actions;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pages.CreateMenuItemPage;
import pages.MenuItemPage;
import utilities.ReportLog;

import java.util.List;

public class MenuItemActions extends MenuItemPage {


    public MenuItemActions(WebDriver driver) {
        super(driver);
    }

    public void verifySuccessMessage() {
        String message = BasePage.SuccesMsg(getDriver(), "Menu Item  success message");
        Assert.assertTrue(message.equalsIgnoreCase("MenuItem Inserted Successfully"), "Success message is not matched");
        ReportLog.PASS("Menu Item inserted successfully message validated Successfully");
    }

    public void VerifyNameInTheList()
    {
        String locator = String.format(titleColumnData, CreateMenuItemActions.title);
        WebElement title = getElement(By.xpath(locator), "New Title");
        Assert.assertNotNull(title, "Title is available in the list");
        ReportLog.PASS("Title is verified in the list successfully");
    }


}