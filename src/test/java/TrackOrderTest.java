import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TrackOrderTest extends BaseTest {
    @BeforeEach
    public void setUp() {
        driver.get("https://www.ekupi.ba/bs/order-tracking");
    }

    @Test
    public void testOrderTrackingFormVisible() {
        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h1[contains(text(), 'Praćenje narudžbe')]")
        ));
        assertTrue(header.isDisplayed());

        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.name("orderCode")
        ));
        assertTrue(input.isDisplayed());

        WebElement submitButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("button.search-button")
        ));
        assertTrue(submitButton.isDisplayed());
    }

    @Test
    public void testInvalidOrderNumberShowsError() {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.name("orderCode")
        ));
        input.sendKeys("123");

        WebElement submitButton = driver.findElement(By.cssSelector("button.search-button"));
        submitButton.click();

        WebElement errorAlert = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div.alert.alert-danger.getAccAlert")
        ));

        assertTrue(errorAlert.getText().contains("nije pronađena"));
    }

    @Test
    public void testEmptyInput() {
        WebElement input = driver.findElement(By.name("orderCode"));
        input.clear();

        WebElement button = driver.findElement(By.cssSelector("button.search-button"));
        button.click();

        // Check for HTML5 required validation or custom alert
        String validationMessage = input.getAttribute("validationMessage");
        assertFalse(validationMessage.isEmpty(), "Validation message should be shown for empty input");
    }
}
