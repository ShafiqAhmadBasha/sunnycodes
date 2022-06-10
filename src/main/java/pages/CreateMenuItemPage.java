package pages;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utilities.ReportLog;

public class CreateMenuItemPage extends BasePage {
    protected static final By ddlselectType=By.id("txtType");
    protected static final By txtName=By.id("txtName");
    protected static final By txtDescription=By.id("txtDescription");
    protected static final By txtNutrition=By.id("txtNutrition");
    protected final By txtIngredients=By.id("txtIngredients");
    protected static final By txtAllergies=By.id("txtAllergies");

    protected static final By btnSubmit=By.id("btnSubmit");

    protected final By messageDisplayed=By.xpath("//span[contains(@style,'green')]");



    //constructors
    public CreateMenuItemPage(WebDriver driver){
        super(driver);
    }

    //abstract method implementation
    public void verifyPageLoad() {



    }
}
