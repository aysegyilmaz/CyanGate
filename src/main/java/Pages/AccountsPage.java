package Pages;

import Base.BaseTest;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class AccountsPage extends BaseTest {
    private WebDriverWait wait;

    public AccountsPage() {

        if (webDriver != null) {
            this.wait = new WebDriverWait(webDriver, Duration.ofSeconds(15));
        }
    }

    @Step("Accounts sekmesine gidilir")
    public AccountsPage goToAccountsTab() {
        webDriver.get("https://orgfarm-a15b19b6a5-dev-ed.develop.lightning.force.com/lightning/o/Account/home");
        System.out.println("📂 Accounts sekmesine gidildi.");
        return this;
    }

    @Step("Yeni Account oluşturulur: {name}, Bölge: {region}")
    public AccountsPage createNewAccount(String name, String region) {
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@title='New']"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='Name']"))).sendKeys(name);
        webDriver.findElement(By.xpath("//input[@name='Account_Region__c']")).sendKeys(region);

        webDriver.findElement(By.xpath("//button[@name='SaveEdit']")).click();
        System.out.println("✅ Yeni Account oluşturuldu: " + name);
        return this;
    }

    @Step("Account kaydının oluşturulduğu doğrulanır")
    public AccountsPage verifyAccountCreated(String expectedName) {
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));
        WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[contains(@class,'toastMessage')]")));
        Assert.assertTrue(toast.getText().contains(expectedName),
                "❌ Account kaydı doğrulanamadı! Beklenen: " + expectedName);
        System.out.println("🎯 Account kaydı doğrulandı: " + expectedName);
        return this;
    }

    @Step("Belirtilen Account kaydına gidilir")
    public AccountsPage openAccountRecord(String accountName) {
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='" + accountName + "']"))).click();
        System.out.println("📁 Account kaydı açıldı: " + accountName);
        return this;
    }
}
