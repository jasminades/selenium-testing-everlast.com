import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

public class RegistrationTest extends BaseTest {
    private WebDriverWait wait;

    @Test
    public void testSuccessfulRegistration() {
        driver.get("https://www.ekupi.ba/bs/login");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement titleDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.id("register.title")));
        new Select(titleDropdown).selectByValue("g");
        driver.findElement(By.id("register.firstName")).sendKeys("ime");
        driver.findElement(By.id("register.lastName")).sendKeys("prezime");
        driver.findElement(By.id("register.email")).sendKeys("ime.prezime@gmail.com");
        driver.findElement(By.id("password")).sendKeys("password123");
        driver.findElement(By.id("register.checkPwd")).sendKeys("password123");

        WebElement termsCheckbox = driver.findElement(By.cssSelector("input[name='termsCheck']"));
        WebElement gdprCheckbox = driver.findElement(By.cssSelector("input[name='gdprCheck']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", termsCheckbox);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", termsCheckbox);
    }

    @Test
    void testEmptyForm() {
        driver.get("https://www.ekupi.ba/bs/login");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement form = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ekupiRegisterForm")));

        String[] fieldIds = {
                "register.title",
                "register.firstName",
                "register.lastName",
                "register.email",
                "password",
                "register.checkPwd"
        };

        for (String id : fieldIds) {
            WebElement element = driver.findElement(By.id(id));
            assertEquals("", element.getAttribute("value").trim());

            WebElement registerButton = driver.findElement(By.id("register-submit-btn"));
            wait.until(ExpectedConditions.visibilityOf(registerButton));

            assertFalse(registerButton.isEnabled());
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", registerButton);
            boolean hasValidation = driver.findElements(By.cssSelector(
                    ".alert-danger, .has-error, .help-block, .error"
            )).size() > 0;

            assertTrue(!registerButton.isEnabled() || hasValidation);
        }
    }



    // radi
    @Test
    public void testMismatchedPasswords() {
        driver.get("https://www.ekupi.ba/bs/login");
        new Select(driver.findElement(By.id("register.title"))).selectByValue("g");

        driver.findElement(By.id("register.firstName")).sendKeys("TestIme");
        driver.findElement(By.id("register.lastName")).sendKeys("TestPrezime");
        driver.findElement(By.id("register.email")).sendKeys("test" + System.currentTimeMillis() + "@example.com");
        driver.findElement(By.id("password")).sendKeys("password123");
        driver.findElement(By.id("register.checkPwd")).sendKeys("password1");

        WebElement termsCheckbox = driver.findElement(By.cssSelector("input[name='termsCheck']"));
        WebElement gdprCheckbox = driver.findElement(By.cssSelector("input[name='gdprCheck']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", termsCheckbox);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", gdprCheckbox);


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement registerButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("register-submit-btn")));

        assertTrue(registerButton.isEnabled());
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", registerButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", registerButton);

        try{
            WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("div.alert.alert-danger")
            ));
        } catch (TimeoutException e) {
            System.out.println("No visible alert");
        }
    }
}