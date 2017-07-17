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
    @CacheLookup
    private List<WebElement> highlightedStars;


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
                performHover(firstStar, firstStar);
                isStarHighlighted();

                break;
            case 2:
                performHover(fifthStar, secondStar);
                isStarHighlighted();

                break;
            case 3:

                performHover(firstStar, thirdStar);
                isStarHighlighted();
                break;
            case 4:
                performHover(firstStar, fourthStar);
                isStarHighlighted();
                break;
            case 5:
                performHover(firstStar, fifthStar);
                isStarHighlighted();
                break;
        }
        this.log.info("Rated with: " + stars + " stars.");
        return this;
    }

    public Boolean isStarHighlighted() {
        for (int i = 0; i < highlightedStars.size(); i++) {
            this.log.info(highlightedStars.size());
            System.out.println(highlightedStars.get(i).getText());
            if (!highlightedStars.get(i).getAttribute("class").contains("hover")) {
                System.out.println(i);
                System.out.println(highlightedStars.get(i).toString());
                break;
            }

        }

        return true;
    }

    private void performHover(WebElement we1, WebElement we2) throws InterruptedException {
        Actions action = new Actions(getDriver());
        action.moveToElement(we1).moveToElement(we2).build().perform();
    }

}
