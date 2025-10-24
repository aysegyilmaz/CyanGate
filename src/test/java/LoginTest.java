import Base.BaseTest;
import Pages.LoginPage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest extends BaseTest {


    @Test(description = "Başarılı kullanıcı girişi")
    public void LoginSuccessful() {

        loginPage.fillUsername(username)
                .fillPassword(password)
                .clickLogin();
        // Başarılı yönlendirmeyi bekle
        new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlToBe(overviewUrl));

        Assert.assertEquals(webDriver.getCurrentUrl(), overviewUrl, "URL beklenen sayfaya yönlendirilmedi.");
    }

}
