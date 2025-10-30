import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import javax.swing.*;
import java.time.Duration;

public class LoginTest extends BaseTest {

    @Test
    public void testLoginPageLoads() {
        driver.get(websiteUrl + "/account/login");
        WebElement loginHeader = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h2")));
        assertEquals(loginHeader.getText(), "Sign in", "Login page header should be 'Sign in'");
    }

    @Test
    public void testLoginValidEmailFlow() {
        driver.get(websiteUrl + "/account/login");

        WebElement emailInput = driver.findElement(By.id("email"));
        WebElement continueButton = driver.findElement(By.cssSelector("button[type='submit']"));

        emailInput.clear();
        emailInput.sendKeys("testuser@mail.com");
        continueButton.click();

        WebElement checkEmailMsg = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("#customer-account-description-visuallyhidden")));
        assertTrue(checkEmailMsg.getText().toLowerCase().contains("verification code"),
                "Expected 'check your email' message");
    }

    @Test
    public void testLoginPageElements() {
        driver.get(websiteUrl + "/account/login");

        WebElement emailInput = driver.findElement(By.id("email"));
        WebElement continueButton = driver.findElement(By.cssSelector("button[type='submit']"));

        assertTrue(emailInput.isDisplayed(), "Email input field should be visible");

        assertTrue(continueButton.isDisplayed(), "Continue button should be visible");
        assertTrue(continueButton.getAttribute("disabled") != null,
                "Continue button should be disabled before captcha");
    }

    @Test
    public void testLoginPageMessages() {
        driver.get(websiteUrl + "/account/login");

        WebElement description = driver.findElement(By.id("customer-account-description"));
        WebElement visuallyHiddenDescription = driver.findElement(By.id("customer-account-description-visuallyhidden"));

        assertTrue(description.isDisplayed(), "Login description should be visible.");
        assertTrue(description.getText().contains("Choose how you'd like to sign in"),
                "Description text is correct");

        assertTrue(visuallyHiddenDescription.isDisplayed(), "Visually hidden description should be present");
        assertTrue(visuallyHiddenDescription.getText().contains("Enter your email"),
                "Hidden description text is correct");
    }
}