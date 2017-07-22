package pages;

import general.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class ProfilePage extends BasePage {

    @FindBy(xpath = "//span[contains(@class,'wh-rating')]")
    @CacheLookup
    private WebElement profileRating;

    @FindBy(xpath = "//div[@class = 'wh-rating-choices']")
    @CacheLookup
    private WebElement yourRatingPopUp;

    @FindBy(xpath = "//a[@href='#' and text()=1]")
    @CacheLookup
    private WebElement firstStar;

    @FindBy(xpath = "//a[@href='#' and text()=2]")
    @CacheLookup
    private WebElement secondStar;

    @FindBy(xpath = "//a[@href='#' and text()=3]")
    @CacheLookup
    private WebElement thirdStar;

    @FindBy(xpath = "//a[@href='#' and text()=4]")
    @CacheLookup
    private WebElement fourthStar;

    @FindBy(xpath = "//a[@href='#' and text()=5]")
    @CacheLookup
    private WebElement fifthStar;

    @FindBy(xpath = "//a[@class='hover']")
    private List<WebElement> highlightedStars;

    @FindBy(xpath = "//p[@class='feeddesc']")
    private WebElement latestActivity;

    @FindBy(xpath = "//a[@href='/profile/wallethub_test/reviews/']")
    @CacheLookup
    private WebElement reviewsTab;

    @FindBy(xpath = "//div[contains(@class,'review-first')]/p")
    private WebElement latestReview;

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    public ProfilePage hoverProfileRating() throws InterruptedException {
        Actions action = new Actions(getDriver());
        action.moveToElement(profileRating).build().perform();
        return this;
    }


    public Boolean isYourRatingPopUpDisplayed() {
        if (yourRatingPopUp.getAttribute("style").contains("display: block;")) {
            this.log.info("Style is:" + yourRatingPopUp.getAttribute("style"));
            this.log.info("'your rating' pop-up is displayed.");
            return true;
        }
        this.log.info("Style is:" + yourRatingPopUp.getAttribute("style"));
        this.log.info("'your rating' pop-up is NOT displayed.");
        return false;
    }


    public ProfilePage setRating(String stars) throws InterruptedException {
        int i = Integer.parseInt(stars);
        switch (i) {
            case 1:
                performHoverBetween2Elements(firstStar, firstStar);
                break;
            case 2:
                performHoverBetween2Elements(firstStar, secondStar);
                break;
            case 3:
                performHoverBetween2Elements(firstStar, thirdStar);
                break;
            case 4:
                performHoverBetween2Elements(firstStar, fourthStar);
                break;
            case 5:
                performHoverBetween2Elements(firstStar, fifthStar);
                break;
        }
        this.log.info("Rated with: " + stars + " stars.");
        return this;
    }

    public Boolean verifyStarsAreHighlithed(String starsNumber) {
        this.log.info("Check if all necessary stars are highlighted.");
        int stars = Integer.parseInt(starsNumber);

        this.log.info(highlightedStars.size() + " stars are highlighted.");
        for (int i = 0; i < highlightedStars.size(); i++) {
            this.log.info("Star # " + highlightedStars.get(i).getText() + " is highlighted.");
            if (!highlightedStars.get(i).getAttribute("class").contains("hover")) {
                this.log.info(highlightedStars.get(i).toString() + "is not highlighted although it should be!");
                break;
            }

        }
        if (!(highlightedStars.size()==stars)) {
            return false;
        }

        return true;
    }

    private void performHoverBetween2Elements(WebElement we1, WebElement we2) throws InterruptedException {
        Actions action = new Actions(getDriver());
        action.moveToElement(we1).moveToElement(we2).build().perform();
        Thread.sleep(2000);
    }

    public ReviewPage clickOnStar(String stars) throws InterruptedException {
        int i = Integer.parseInt(stars);
        switch (i) {
            case 1:
                firstStar.click();
                this.log.info(stars + " star clicked.");
                break;
            case 2:
                secondStar.click();
                this.log.info(stars + " star clicked.");
                break;
            case 3:
                thirdStar.click();
                this.log.info(stars + " star clicked.");
                break;
            case 4:
                fourthStar.click();
                this.log.info(stars + " star clicked.");
                break;
            case 5:
                fifthStar.click();
                this.log.info(stars + " star clicked.");
                break;
        }

        return new ReviewPage(getDriver());
    }

    public String getLatestActivity(){
        String latest = latestActivity.getText();
        this.log.info(latest + " is the latest review added.");
        return latest;
    }

    public ProfilePage clickReviewsTab(){
        reviewsTab.click();
        this.log.info("'Reviews' tab clicked.");
        return this;
    }

    public String getLatestReview(){
        String latest = latestReview.getText();
        this.log.info(latest + " is the latest review added.");
        return latest;
    }

}
