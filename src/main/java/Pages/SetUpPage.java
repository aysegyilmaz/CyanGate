package Pages;

import Base.BaseTest;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.Set;

public class SetUpPage extends BaseTest {

    private final WebDriverWait wait;

    public SetUpPage() {
        this.wait = new WebDriverWait(webDriver, Duration.ofSeconds(25));
    }

    @Step("Setup menüsünden 'Setup' sekmesine gidilir")
    public SetUpPage goToSetup() {
        long startTime = System.currentTimeMillis();
        long maxWait = 10000; // 10 saniye
        JavascriptExecutor js = (JavascriptExecutor) webDriver;

        try {
            System.out.println("⏳ Setup ikonunu bekliyor...");

            while (System.currentTimeMillis() - startTime < maxWait) {
                try {

                    WebElement setupIcon = webDriver.findElement(By.xpath("//button[@title='Setup']"));
                    if (setupIcon.isDisplayed()) {
                        js.executeScript("arguments[0].click();", setupIcon);
                        System.out.println("⚙️ Setup ikonuna tıklandı.");


                        WebElement setupLink = webDriver.findElement(
                                By.xpath("//a[@role='menuitem']//span[normalize-space()='Setup']")
                        );
                        js.executeScript("arguments[0].click();", setupLink);
                        System.out.println("🧭 Menüdeki 'Setup' bağlantısına tıklandı.");


                        switchToSetupTab();
                        return this;
                    }
                } catch (NoSuchElementException e) {
                    Thread.sleep(1000);
                }
            }


        } catch (Exception e) {
            Assert.fail("❌ Setup menüsüne gidilemedi: " + e.getMessage());
        }

        return this;
    }

    private void switchToSetupTab() {
        Set<String> handles = webDriver.getWindowHandles();
        for (String handle : handles) {
            webDriver.switchTo().window(handle);
            if (webDriver.getTitle().contains("Setup Home")) {
                System.out.println("✅ Setup sekmesine geçildi.");
                return;
            }
        }
        System.out.println("⚠️ Setup sekmesi bulunamadı, fallback başlatılıyor...");
        fallbackToSetupHome();
    }


    private void fallbackToSetupHome() {
        try {
            webDriver.get("https://orgfarm-a15b19b6a5-dev-ed.develop.lightning.force.com/lightning/setup/SetupOneHome/home");
            wait.until(ExpectedConditions.titleContains("Setup Home"));
            System.out.println("✅ Fallback ile Setup sayfasına geçildi.");
        } catch (Exception e) {
            Assert.fail("❌ Setup sayfasına erişilemedi: " + e.getMessage());
        }
    }

    @Step("Account objesine gidilir")
    public SetUpPage openAccountObject() {
        try {
            String base = webDriver.getCurrentUrl().split("/lightning")[0];
            String objectManagerUrl = base + "/lightning/setup/ObjectManager/home";
            webDriver.get(objectManagerUrl);
            System.out.println("🧭 Object Manager sayfasına gidildi.");

            By accountLocator = By.xpath("//a[contains(@href,'ObjectManager/Account/view')]");
            WebDriverWait customWait = new WebDriverWait(webDriver, Duration.ofSeconds(30));


            WebElement accountLink = customWait.until(ExpectedConditions.visibilityOfElementLocated(accountLocator));
            ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", accountLink);

            ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", accountLink);
            System.out.println("📁 Account objesi JavaScript ile tıklandı.");

        } catch (Exception e) {
            Assert.fail("❌ Account objesi bulunamadı veya tıklanamadı: " + e.getMessage());
        }
        return this;
    }


    @Step("Fields & Relationships sekmesine gidilir")
    public SetUpPage openFieldsAndRelationships() {
        try {
            By fieldsTab = By.xpath("//a[contains(@href,'FieldsAndRelationships/view')]");
            WebDriverWait customWait = new WebDriverWait(webDriver, Duration.ofSeconds(30));

            WebElement fieldsLink = customWait.until(ExpectedConditions.visibilityOfElementLocated(fieldsTab));

            ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", fieldsLink);

            ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", fieldsLink);
            System.out.println("🔖 Fields & Relationships sekmesine JavaScript ile tıklandı.");

            customWait.until(ExpectedConditions.urlContains("FieldsAndRelationships"));
            System.out.println("✅ Fields & Relationships sayfası yüklendi.");

        } catch (Exception e) {
            Assert.fail("❌ Fields & Relationships sekmesine gidilemedi: " + e.getMessage());
        }
        return this;
    }
}
