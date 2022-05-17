package PlanTests;

import actions.LoginActions;
import core.BaseTest;
import org.testng.annotations.Test;

public class PlanList extends BaseTest {

    @Test
    public void validatePlanListColumnTitles(){
        LoginActions loginActions = launchApplication(LoginActions.class);
        loginActions.performUserLogin("","");
    }

    @Test
    public void validateDateInPlanList(){

    }

    @Test
    public void validatePageTitleInPlanList(){

    }
}
