package pages;

import core.BasePage;
import org.openqa.selenium.WebDriver;

public class MenuItemPage extends BasePage {

    public MenuItemPage(WebDriver driver)
    {
        super(driver);
    }

    @Override
    public void verifyPageLoad() {

    }

    //locators
    protected String titleColumnData= "//table[@id='tbldata']//tr/td[3][text()='%s']";
}
