import general.BaseTest;
import general.ExcelUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.*;

public class WriteReviewTests extends BaseTest {


    @Test(dataProvider="TestData")
    public void AddReview(String username, String password, String testUrl, String stars_1, String stars_2, String policy, String reviewText) throws InterruptedException {
        HomePage hp = new HomePage(driver);
        LoginPage login = hp.clickLoginBtn();
        login.enterUsername(username);
        login.enterPassword(password);
        ProfilePage pp = login.loginBtnClick();
        Thread.sleep(3000);
        loadDirectLink(testUrl, new ProfilePage(driver));
        Thread.sleep(3000);

        //(a) do the hover
        pp.hoverProfileRating();
        pp.isYourRatingPopUpDisplayed();
        pp.setRating(stars_1);

        //(2) make sure the stars inside get lit up when you hover over them,
        Assert.assertTrue(pp.verifyStarsAreHighlithed(stars_1));

        pp.setRating(stars_2);
        Thread.sleep(4000);
        Assert.assertTrue(pp.verifyStarsAreHighlithed(stars_2));

        ReviewPage reviewPage = pp.clickOnStar(stars_2);
        reviewPage.expandDropDown();
        reviewPage.selectDropDownItem(policy);
        Thread.sleep(4000);

        reviewPage.enterReviewText(reviewText);
        Thread.sleep(2000);
        reviewPage.clickReviewStars(stars_2);
        reviewPage.submitBtnClick();
        Thread.sleep(2000);
        Assert.assertTrue(reviewPage.getSuccMsg().contains("has been posted."));
        Toolbar toolbar = new Toolbar(driver);
        pp = toolbar.navigateToProfilePage();
        pp.getLatestActivity();

        pp.clickReviewsTab();
        Assert.assertEquals(pp.getLatestReview(),reviewText);

    }

    @DataProvider
    public Object[][] TestData() throws Exception {
        Object[][] testObjArray = ExcelUtils.getTableArray("src/main/resources/TestData.xlsx", "Sheet1");
        return (testObjArray);
    }
}
