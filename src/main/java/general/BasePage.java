package general;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileReader;
import java.util.concurrent.TimeUnit;

public class BasePage {

    protected Logger log = LogManager.getLogger(BasePage.class.getName());
  	private WebDriverWait waitDriver;
    private WebDriver driver;

    public BasePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected void waitImplicitly(int secondsToWait) {
        driver.manage().timeouts().implicitlyWait(secondsToWait, TimeUnit.SECONDS);
    }
    protected WebDriver getDriver() {
        return  driver;
    }

}
