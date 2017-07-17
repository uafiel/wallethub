package pages;

import general.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//input[contains(@placeholder,'Email Address')]")
    @CacheLookup
    private WebElement usernameInput;

    @FindBy(xpath = "//input[@type = 'password']")
    @CacheLookup
    private WebElement passwordInput;

    @FindBy(xpath = "//button[@data-hm-tap= 'doLogin($event);']")
    @CacheLookup
    private WebElement loginBtn;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage enterUsername(String username) {
        usernameInput.click();
        usernameInput.clear();
        this.log.info("Username input cleared.");
        usernameInput.sendKeys(username);
        this.log.info(username + " entered for username.");
        return this;
    }

    public LoginPage enterPassword(String password) {
        passwordInput.click();
        passwordInput.clear();
        this.log.info("Password input cleared.");
        passwordInput.sendKeys(password);
        this.log.info(password + " entered for password.");
        return this;
    }

    public ProfilePage loginBtnClick(){
        loginBtn.click();
        this.log.info("Login button clicked.");
        return new ProfilePage(getDriver());
    }
}
