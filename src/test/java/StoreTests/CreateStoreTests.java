package StoreTests;

import actions.DashboardActions;
import actions.LoginActions;
import actions.StoreListActions;
import core.BaseTest;
import org.testng.annotations.Test;
import utilities.Common;

public class CreateStoreTests extends BaseTest {

    @Test
    public void validateCreateStoreForm(){
        LoginActions loginActions = launchApplication(LoginActions.class);
        DashboardActions dashboardActions = loginActions.performUserLogin(Common.getEnvData("username"), Common.getEnvData("password"));
        StoreListActions storeListActions = dashboardActions.selectMenu(Common.menu.get("client"), Common.menuOption.get("store"), StoreListActions.class);
    }
}
