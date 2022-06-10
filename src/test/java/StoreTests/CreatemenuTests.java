package StoreTests;

import actions.*;
import core.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import utilities.Common;

public class CreatemenuTests extends BaseTest {

    @Test
    public void ValidateCreateMenuItemForVEG()
    {
        LoginActions loginActions = launchApplication(LoginActions.class);
        DashboardActions dashboardActions = loginActions.performUserLogin(Common.getEnvData("username"), Common.getEnvData("password"));
        MenuItemActions menuItemActions= dashboardActions.selectMenu(Common.menu.get("management"), Common.menuOption.get("menuitem"), MenuItemActions.class);
        CreateMenuItemActions createMenuItemActions = menuItemActions.clickAddNew(CreateMenuItemActions.class);
        createMenuItemActions.fillDataAndSubmitForm("VEG");
        menuItemActions.verifySuccessMessage();
    }

}
