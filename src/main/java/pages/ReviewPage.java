package pages;

import general.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ReviewPage extends BasePage {

    @FindBy(className = "drop-arrow")
    @CacheLookup
    private WebElement dropDownArrow;

    @FindBy(xpath = "//li/a[@href=\"#\"]")
    @CacheLookup
    private List<WebElement> dropDownItems;

    @FindBy(xpath = "//textarea[@name=\"review\"]")
    @CacheLookup
    private WebElement reviewTextarea;

    @FindBy(xpath = "//input[@type='submit']")
    @CacheLookup
    private WebElement submitBtn;

    @FindBy(xpath = "//a[contains(@class,'star')]")
    @CacheLookup
    private List<WebElement> reviewStars;

    @FindBy(xpath = "//h1")
    @CacheLookup
    private WebElement successfulReviewMsg;

    public ReviewPage(WebDriver driver) {
        super(driver);
    }

    public ReviewPage expandDropDown() {
        dropDownArrow.click();
        this.log.info("Drop-down expanded.");
        return this;
    }

    public ReviewPage selectDropDownItem(String item) {
        Boolean itemExists = false;
        for (int i = 0; i < dropDownItems.size(); i++) {
            if (dropDownItems.get(i).getText().toLowerCase().equals(item.toLowerCase())) {
                dropDownItems.get(i).click();
                this.log.info(dropDownItems.get(i).getText() + " selected.");
                itemExists = true;
                break;
            }
        }
        if (!itemExists) {
            throw new IllegalArgumentException("No such item: " + item);
        }
        return this;
    }

    public ReviewPage enterReviewText(String text) {
        reviewTextarea.sendKeys(text);
        this.log.info(text + " entered as review text.");
        return this;
    }

    public ReviewPage submitBtnClick() {
        submitBtn.click();
        this.log.info("'sumbit' button clicked");
        return this;
    }

    public ReviewPage clickReviewStars(String number) {
        int i = Integer.parseInt(number);
        reviewStars.get(i-1).click();
        this.log.info(number + " star clicked.");
        return this;
    }

    public String getSuccMsg() {
        String msg = successfulReviewMsg.getText();
        return msg;
    }


}
