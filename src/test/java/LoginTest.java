import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.Duration;

public class LoginTest extends BaseTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.ekupi.ba/bs/login");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void testValidLogin() throws InterruptedException {
        driver.findElement(By.id("j_username")).sendKeys("jasmina.destanovic1@gmail.com");
        driver.findElement(By.id("j_password")).sendKeys("password");
        driver.findElement(By.id("submit")).click();

        Thread.sleep(2000);
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("/bs/"));
    }

    @Test
    public void testInvalidPassword() throws InterruptedException {
        driver.findElement(By.id("j_username")).sendKeys("jasmina.destanovic1@gmail.com");
        driver.findElement(By.id("j_password")).sendKeys("wrong-password");
        driver.findElement(By.id("submit")).click();

        Thread.sleep(2000);
        WebElement error = driver.findElement(By.cssSelector(".alert-danger"));
        assertTrue(error.isDisplayed());

    }

    @Test
    public void testEmptyEmail() {
        driver.findElement(By.id("j_password")).sendKeys("wrong-password");
        driver.findElement(By.id("submit")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div.alert.alert-danger.getAccAlert")
        ));

        String expectedMessage = "Email adresa ili lozinka su neispravni.";
        assertTrue(alert.isDisplayed());
        assertTrue(alert.getText().contains(expectedMessage));
    }

    @Test
    public void testEmptyPassword() {
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("j_username")));
        emailField.clear();
        emailField.sendKeys("jasmina.destanovic1@gmail.com");

        WebElement passwordField = driver.findElement(By.id("j_password"));
        passwordField.clear();

        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();

        WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div.alert.alert-danger.getAccAlert")
        ));

        String expectedMessage = "Email adresa ili lozinka su neispravni.";
        assertTrue(alert.isDisplayed());
        assertTrue(alert.getText().contains(expectedMessage));
    }

    @Test
    public void testForgotPasswordLink() {
        WebElement forgotLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(".js-password-forgotten")));
        assertTrue(forgotLink.isDisplayed());

        forgotLink.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("#cboxLoadedContent")
        ));

        assertTrue(modal.isDisplayed());
    }
}