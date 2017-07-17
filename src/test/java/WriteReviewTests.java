import general.BaseTest;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.ProfilePage;

public class WriteReviewTests extends BaseTest  {


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
        pp.hoverProfileRating();
        Thread.sleep(3000);
        pp.isYourRatingPopUpDisplayed();
        pp.setRating("3");
    }
}
