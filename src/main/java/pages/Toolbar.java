package pages;

import general.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class Toolbar extends BasePage {

    @FindBy(xpath = "//a[@class='user']")
    @CacheLookup
    private WebElement userBtn;

    @FindBy(xpath = "//a[text()='Profile']")
    @CacheLookup
    private WebElement profileBtn;


    public Toolbar(WebDriver driver) {
        super(driver);
    }

    public ProfilePage navigateToProfilePage() throws InterruptedException {
        performHoverBetween2Elements(userBtn, profileBtn);
        return new ProfilePage(getDriver());
    }

    private void performHoverBetween2Elements(WebElement we1, WebElement we2) throws InterruptedException {
        Actions action = new Actions(getDriver());
        action.moveToElement(we1).moveToElement(we2).click().perform();
        Thread.sleep(2000);
    }
}
