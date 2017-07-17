package pages;

import general.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage  {

    @FindBy(className = "login")
    private WebElement loginBtn;

    public HomePage(WebDriver driver) {
        super(driver);
    }


    public LoginPage clickLoginBtn() {
        loginBtn.click();
        this.log.info("Login button clicked.");
        return new LoginPage(getDriver());
    }

}
