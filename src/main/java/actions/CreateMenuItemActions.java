package actions;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.CreateMenuItemPage;
import utilities.ReportLog;

public class CreateMenuItemActions extends CreateMenuItemPage {

    public CreateMenuItemActions(WebDriver driver) {
        super(driver);
    }

    public  void  fillDataAndSubmitForm(String type) {
        String title = getAlphaNumericString(6);
        selectDropdownOptionByText(ddlselectType, type, "selectType");
        enterText(txtName, getAlphaNumericString(6), "Name");
        enterText(txtDescription, getAlphaNumericString(10), "Description");
        enterText(txtNutrition, getAlphaNumericString(10), "Nutrition");
        enterText(txtAllergies, getAlphaNumericString(10), "Allergies");
        click(btnSubmit, "Submit button");
    }

}