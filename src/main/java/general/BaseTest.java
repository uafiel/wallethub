package general;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    protected static WebDriver driver;
    public Logger log = LogManager.getLogger(BaseTest.class.getName());

    @BeforeClass
    public static WebDriver setUp() {
        String browserName = "";
        String baseURL = "";
        int waitTimeOut = 0;
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("src/main/resources/config.json"));
            JSONObject pageInfo = (JSONObject) jsonObject.get("pageInfo");
            browserName = pageInfo.get("browserName").toString();
            baseURL = pageInfo.get("baseURL").toString();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }

        driver = BrowserFactory.getDriver(browserName);
        driver.get(baseURL);
        return driver;

    }

    protected BasePage loadDirectLink(String URL, BasePage p) {
        waitImplicitly(7);
        driver.get(URL);
        this.log.info(URL + " loaded.");
        return p;
    }

    protected void waitImplicitly(int secondsToWait) {
        driver.manage().timeouts().implicitlyWait(secondsToWait, TimeUnit.SECONDS);
    }

    @BeforeMethod
    public void beforeTest(Method method) {
        String testName = method.getName();
        log.info("Start: " + testName);
    }

    @AfterClass
    public void quitDriver() {
        driver.quit();
    }

}


