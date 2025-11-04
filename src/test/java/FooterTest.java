import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FooterTest extends BaseTest {
    private WebDriverWait waiter;
    private JavascriptExecutor js;

    @BeforeEach
    public void init() {
        waiter = new WebDriverWait(driver, Duration.ofSeconds(10));
        js = (JavascriptExecutor) driver;
        driver.get("https://www.ekupi.ba/bs");
        try {
            WebElement acceptBtn = waiter.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector(".js-cookie-notification-accept")
            ));
            acceptBtn.click();
        } catch (Exception ignored) {}
    }

    @Test
    public void testVizijaIMisijaLink() {
        WebElement link = waiter.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("a[title='Vizija i misija']")
        ));

        js.executeScript("arguments[0].scrollIntoView(true);", link);
        js.executeScript("arguments[0].click();", link);

        waiter.until(ExpectedConditions.urlContains("/bs/Vizija-i-misija"));
        assertTrue(driver.getCurrentUrl().contains("/bs/Vizija-i-misija"));
    }

    @Test
    public void testPrednosti() {
        WebElement link = waiter.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("a[title='Prednosti kupovine na eKupi']")
        ));

        js.executeScript("arguments[0].scrollIntoView(true);", link);
        js.executeScript("arguments[0].click();", link);

        waiter.until(ExpectedConditions.urlContains("/bs/Prednosti-kupovine-na-eKupi"));
        assertTrue(driver.getCurrentUrl().contains("/bs/Prednosti-kupovine-na-eKupi"));
    }

    @Test
    public void testPoslovanje() {
        WebElement link = waiter.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("a[title='Gdje poslujemo']")
        ));

        js.executeScript("arguments[0].scrollIntoView(true);", link);
        js.executeScript("arguments[0].click();", link);

        waiter.until(ExpectedConditions.urlContains("/bs/Gdje-poslujemo"));
        assertTrue(driver.getCurrentUrl().contains("/bs/Gdje-poslujemo"));
    }

    @Test
    public void testKoJeKupko() {
        WebElement link = waiter.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("a[title='Ko je Kupko']")
        ));

        js.executeScript("arguments[0].scrollIntoView(true);", link);
        js.executeScript("arguments[0].click();", link);

        waiter.until(ExpectedConditions.urlContains("/bs/Ko-je-Kupko"));
        assertTrue(driver.getCurrentUrl().contains("/bs/Ko-je-Kupko"));
    }


    @Test
    public void testNagrade() {
        WebElement link = waiter.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("a[title='Dobijene nagrade']")
        ));

        js.executeScript("arguments[0].scrollIntoView(true);", link);
        js.executeScript("arguments[0].click();", link);

        waiter.until(ExpectedConditions.urlContains("/bs/Osvojene-nagrade"));
        assertTrue(driver.getCurrentUrl().contains("/bs/Osvojene-nagrade"));
    }

    @Test
    public void testKakoSeRegistrovati() {
        WebElement link = waiter.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("a[title='Kako se registrovati']")
        ));

        js.executeScript("arguments[0].scrollIntoView(true);", link);
        js.executeScript("arguments[0].click();", link);

        waiter.until(ExpectedConditions.urlContains("/bs/Kako-se-registrovati"));
        assertTrue(driver.getCurrentUrl().contains("/bs/Kako-se-registrovati"));
    }

    @Test
    public void testKakoKupovati() {
        WebElement link = waiter.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("a[title='Kako kupovati na eKupi']")
        ));

        js.executeScript("arguments[0].scrollIntoView(true);", link);
        js.executeScript("arguments[0].click();", link);

        waiter.until(ExpectedConditions.urlContains("/bs/Kako-kupovati-na-eKupi"));
        assertTrue(driver.getCurrentUrl().contains("/bs/Kako-kupovati-na-eKupi"));
    }

    @Test
    public void testDUS() {
        WebElement link = waiter.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("a[title='DUS - Dostava u stan']")
        ));

        js.executeScript("arguments[0].scrollIntoView(true);", link);
        js.executeScript("arguments[0].click();", link);

        waiter.until(ExpectedConditions.urlContains("/eKupi-Benefiti"));
        assertTrue(driver.getCurrentUrl().contains("/eKupi-Benefiti"));
    }

    @Test
    public void testCestaPitanja() {
        WebElement link = waiter.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("a[title='Česta pitanja']")
        ));

        js.executeScript("arguments[0].scrollIntoView(true);", link);
        js.executeScript("arguments[0].click();", link);

        waiter.until(ExpectedConditions.urlContains("/bs/cesta-pitanja"));
        assertTrue(driver.getCurrentUrl().contains("/bs/cesta-pitanja"));
    }

    @Test
    public void testNaciniPlacanja() {
        WebElement link = waiter.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("a[title='Načini plaćanja']")
        ));

        js.executeScript("arguments[0].scrollIntoView(true);", link);
        js.executeScript("arguments[0].click();", link);

        waiter.until(ExpectedConditions.urlContains("/bs/Nacini-placanja"));
        assertTrue(driver.getCurrentUrl().contains("/bs/Nacini-placanja"));
    }

    @Test
    public void testSigurnostPlacanja() {
        WebElement link = waiter.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("a[title='Sigurnost plaćanja']")
        ));

        js.executeScript("arguments[0].scrollIntoView(true);", link);
        js.executeScript("arguments[0].click();", link);

        waiter.until(ExpectedConditions.urlContains("/bs/Sigurnost-placanja"));
        assertTrue(driver.getCurrentUrl().contains("/bs/Sigurnost-placanja"));
    }

    @Test
    public void testServis() {
        WebElement link = waiter.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("a[title='Servis']")
        ));

        js.executeScript("arguments[0].scrollIntoView(true);", link);
        js.executeScript("arguments[0].click();", link);

        waiter.until(ExpectedConditions.urlContains("/bs/Servis"));
        assertTrue(driver.getCurrentUrl().contains("/bs/Servis"));
    }

    @Test
    public void testReklamacije() {
        WebElement link = waiter.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("a[title='Reklamacije']")
        ));

        js.executeScript("arguments[0].scrollIntoView(true);", link);
        js.executeScript("arguments[0].click();", link);

        waiter.until(ExpectedConditions.urlContains("/bs/Reklamacije"));
        assertTrue(driver.getCurrentUrl().contains("/bs/Reklamacije"));
    }

    @Test
    public void testKontakt() {
        WebElement link = waiter.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("a[title='Kontakt']")
        ));

        js.executeScript("arguments[0].scrollIntoView(true);", link);
        js.executeScript("arguments[0].click();", link);

        waiter.until(ExpectedConditions.urlContains("/bs/Kontakt"));
        assertTrue(driver.getCurrentUrl().contains("/bs/Kontakt"));
    }


}
