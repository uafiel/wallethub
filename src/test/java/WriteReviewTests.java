import general.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class WriteReviewTests extends BaseTest {


    @Test
    public void AddReviewLongerThan200characters() throws InterruptedException {
        HomePage hp = new HomePage(driver);
        LoginPage login = hp.clickLoginBtn();
        Thread.sleep(1000);
        login.enterUsername("facebook_test@inbox.ru");
        login.enterPassword("Wallet@123");
        ProfilePage pp = login.loginBtnClick();
        Thread.sleep(2000);
        loadDirectLink("https://wallethub.com/profile/test_insurance_company/", new ProfilePage(driver));
        Thread.sleep(5000);

        //(a) do the hover
        pp.hoverProfileRating();
        Thread.sleep(3000);
        pp.isYourRatingPopUpDisplayed();
        pp.setRating("4");

        //(2) make sure the stars inside get lit up when you hover over them,
        Assert.assertTrue(pp.verifyStarsAreHighlithed());

        pp.setRating("5");
        Assert.assertTrue(pp.verifyStarsAreHighlithed());

        ReviewPage reviewPage = pp.clickOnStar("5");
        reviewPage.expandDropDown();
        reviewPage.selectDropDownItem("health");
        Thread.sleep(4000);

        reviewPage.enterReviewText("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor." +
                " Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec qu");
        Thread.sleep(2000);
        reviewPage.clickReviewStars("5");
        reviewPage.submitBtnClick();
        Thread.sleep(10000);
        Assert.assertTrue(reviewPage.getSuccMsg().contains("has been posted."));
        Toolbar toolbar = new Toolbar(driver);
        pp = toolbar.navigateToProfilePage();
        pp.getLatestActivity();

        pp.clickReviewsTab();

        pp.getLatestReview();

    }
}
