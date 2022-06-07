package PlanTests;

import actions.LoginActions;
import core.BaseTest;
import org.testng.annotations.Test;
import utilities.Common;

public class PlanList extends BaseTest {

    @Test
    public void validatePlanListColumnTitles(){
        LoginActions loginActions = launchApplication(LoginActions.class);
        loginActions.performUserLogin(Common.getEnvData("useranme"),Common.getEnvData("password"));
    }

    @Test
    public void validateDateInPlanList(){

    }

    @Test
    public void validatePageTitleInPlanList(){

    }
}
