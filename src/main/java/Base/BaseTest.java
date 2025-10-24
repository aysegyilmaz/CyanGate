package Base;

import Pages.LoginPage;
import Pages.SetUpPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest extends BaseLibrary {

    protected LoginPage loginPage;
    protected SetUpPage setUpPage;

    @BeforeMethod
    public void openBrowser() {
        // ChromeDriver setup
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();

        // Genel ayarlar
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Login sayfasına git
        webDriver.get("https://login.salesforce.com/");

        // Sayfa nesnelerini başlat
        loginPage = new LoginPage();
        setUpPage = new SetUpPage();
    }

    @AfterMethod
    public void closeBrowser() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

    @Attachment(value = "Failure Screenshot", type = "image/png")
    public byte[] saveScreenshot() {
        return ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
    }
}
