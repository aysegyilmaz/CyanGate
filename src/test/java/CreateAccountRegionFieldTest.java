
import Base.BaseTest;
import Pages.SetUpPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreateAccountRegionFieldTest extends BaseTest {

    private SetUpPage setUpPage;

    @BeforeMethod
    public void init() {
        setUpPage = new SetUpPage();
    }

    @Test(description = "Salesforce Setup > Account > Fields & Relationships > Yeni Text Field oluşturma testi", timeOut = 180000)
    public void createAccountRegionField() throws InterruptedException {

        // 🔹 Login
        loginPage.fillUsername(username)
                .fillPassword(password)
                .clickLogin();

        System.out.println(">>> MFA onayını tamamla, sistem 30 saniye bekliyor...");
        Thread.sleep(30000);


        setUpPage.goToSetup()
                .openAccountObject()
                .openFieldsAndRelationships();

    }
}
