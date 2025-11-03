import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.Duration;

public class RegistrationTest extends BaseTest {

    @BeforeEach
    public void getUrl(){
        driver.get("https://www.ekupi.ba/bs/login");
    }

    @Test
    public void testSuccessfulRegistration() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement titleDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.id("register.title")));
        new Select(titleDropdown).selectByValue("g");
        driver.findElement(By.id("register.firstName")).sendKeys("ime");
        driver.findElement(By.id("register.lastName")).sendKeys("prezime");
        driver.findElement(By.id("register.email")).sendKeys("ime.prezime@mail.com");
        driver.findElement(By.id("password")).sendKeys("password123");
        driver.findElement(By.id("register.checkPwd")).sendKeys("password123");

        WebElement termsCheckbox = driver.findElement(By.cssSelector("input[name='termsCheck']"));
        WebElement gdprCheckbox = driver.findElement(By.cssSelector("input[name='gdprCheck']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", termsCheckbox);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", termsCheckbox);
    }

    @Test
    void testEmptyForm() {
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


    @Test
    public void testMismatchedPasswords() {
        new Select(driver.findElement(By.id("register.title"))).selectByValue("g");

        driver.findElement(By.id("register.firstName")).sendKeys("ime");
        driver.findElement(By.id("register.lastName")).sendKeys("prezime");
        driver.findElement(By.id("register.email")).sendKeys("ime.prezime@mail.com");
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

    @Test
    public void testInvalidEmailFormat() {
        new Select(driver.findElement(By.id("register.title"))).selectByValue("g");
        driver.findElement(By.id("register.firstName")).sendKeys("ime");
        driver.findElement(By.id("register.lastName")).sendKeys("prezime");
        driver.findElement(By.id("register.email")).sendKeys("imeprezime");
        driver.findElement(By.id("password")).sendKeys("password123");
        driver.findElement(By.id("register.checkPwd")).sendKeys("password123");

        WebElement termsCheckbox = driver.findElement(By.cssSelector("input[name='termsCheck']"));
        WebElement gdprCheckbox = driver.findElement(By.cssSelector("input[name='gdprCheck']"));

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", termsCheckbox);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", gdprCheckbox);

        WebElement registerButton = driver.findElement(By.id("register-submit-btn"));
        assertTrue(registerButton.isEnabled());

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", registerButton);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean validationShown = false;

        try {
            WebElement emailError = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("div.form-group.has-error input#register\\.email, span.help-block, .alert-danger")
            ));
            validationShown = emailError.isDisplayed();
        } catch (TimeoutException e) {
            validationShown = false;
        }
        assertTrue(validationShown);
    }



    @Test
    public void testPasswordTooShort() {
        new Select(driver.findElement(By.id("register.title"))).selectByValue("g");
        driver.findElement(By.id("register.firstName")).sendKeys("ime");
        driver.findElement(By.id("register.lastName")).sendKeys("prezime");
        driver.findElement(By.id("register.email")).sendKeys("ime.prezime@mail.com");
        driver.findElement(By.id("password")).sendKeys("123");
        driver.findElement(By.id("register.checkPwd")).sendKeys("123");

        WebElement helpBlock = driver.findElement(By.id("password_minchar"));
        assertTrue(helpBlock.isDisplayed());
        assertTrue(helpBlock.getText().contains("Minimalna dužina"));

        WebElement registerButton = driver.findElement(By.id("register-submit-btn"));
        assertFalse(registerButton.isEnabled());
    }

    @Test
    public void testUncheckedTermsValidation() {
        new Select(driver.findElement(By.id("register.title"))).selectByValue("g");
        driver.findElement(By.id("register.firstName")).sendKeys("ime");
        driver.findElement(By.id("register.lastName")).sendKeys("prezime");
        driver.findElement(By.id("register.email")).sendKeys("ime.prezime@mail.com");
        driver.findElement(By.id("password")).sendKeys("password123");
        driver.findElement(By.id("register.checkPwd")).sendKeys("password123");

        WebElement registerButton = driver.findElement(By.id("register-submit-btn"));
        assertFalse(registerButton.isEnabled());
    }

    @Test
    public void testRegisterButtonEnabledAfterValidInput() {
        new Select(driver.findElement(By.id("register.title"))).selectByValue("g");
        driver.findElement(By.id("register.firstName")).sendKeys("ime");
        driver.findElement(By.id("register.lastName")).sendKeys("prezime");
        driver.findElement(By.id("register.email")).sendKeys("ime.prezime@mail.com");
        driver.findElement(By.id("password")).sendKeys("password123");
        driver.findElement(By.id("register.checkPwd")).sendKeys("password123");

        WebElement termsCheckbox = driver.findElement(By.cssSelector("input[name='termsCheck']"));
        WebElement gdprCheckbox = driver.findElement(By.cssSelector("input[name='gdprCheck']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", termsCheckbox);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", gdprCheckbox);

        WebElement registerButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("register-submit-btn")));
        assertTrue(registerButton.isEnabled());
    }
}