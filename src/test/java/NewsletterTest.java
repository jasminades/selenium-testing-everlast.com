import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.*;

public class NewsletterTest extends BaseTest{
    @Test
    public void testInputField() {
        WebElement emailInput = driver.findElement(By.name("email"));
        assertEquals("Vaša email adresa", emailInput.getAttribute("placeholder"));
        assertEquals("email", emailInput.getAttribute("type"));
        assertTrue(emailInput.getAttribute("required") != null);
    }

    @Test
    public void testSubmitButtonPresence() {
        WebElement button = driver.findElement(By.id("newsletter-submit-btn"));
        assertTrue(button.isDisplayed());
        assertEquals("PRIJAVI SE", button.getText());
    }

    @Test
    public void testEmptyEmailSubmission() {
        WebElement emailInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("email")));
        emailInput.clear();

        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.id("newsletter-submit-btn")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);

        String validationMessage = emailInput.getAttribute("validationMessage");
        assertFalse(validationMessage.isEmpty());
    }

    @Test
    public void testInvalidEmailFormat() {
        WebElement emailInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("email")));
        emailInput.clear();
        emailInput.sendKeys("invalid-email");

        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.id("newsletter-submit-btn")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);

        String validationMessage = emailInput.getAttribute("validationMessage");
        assertFalse(validationMessage.isEmpty());
    }
}
