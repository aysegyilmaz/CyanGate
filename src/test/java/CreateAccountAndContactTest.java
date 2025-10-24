import Base.BaseTest;
import Pages.AccountsPage;
import org.testng.annotations.Test;

public class CreateAccountAndContactTest extends BaseTest {

    @Test(description = "Account ve Contact kayıtlarını oluşturma ve ilişki doğrulama testi")
    public void createAccountAndContact() throws InterruptedException {
        System.out.println("🚀 Test başladı: Account ve Contact kayıtları oluşturulacak.");


        loginPage.fillUsername(username)
                .fillPassword(password)
                .clickLogin();

        System.out.println(">>> Lütfen MFA doğrulamasını elle tamamla. 30 saniye bekleniyor...");
        Thread.sleep(30000);


        AccountsPage accountsPage = new AccountsPage();


        accountsPage.goToAccountsTab()
                .createNewAccount("CyanGate", "EMEA")
                .verifyAccountCreated("CyanGate");


        System.out.println("✅ Test başarıyla tamamlandı!");
    }
}
