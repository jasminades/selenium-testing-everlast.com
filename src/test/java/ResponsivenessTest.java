import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ResponsivenessTest extends BaseTest{
    @BeforeEach
    public void setUp() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
        driver.get(websiteUrl);

        try {
            WebElement cookieAccept = wait.until(
                    ExpectedConditions.elementToBeClickable(By.cssSelector(".js-cookie-notification-accept"))
            );
            cookieAccept.click();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void checkResponsiveLayouts() {
        driver.manage().window().setSize(new org.openqa.selenium.Dimension(1920, 1080));
        verifyMainVisuals();

        driver.manage().window().setSize(new org.openqa.selenium.Dimension(768, 1024));
        verifyMainVisuals();

        driver.manage().window().setSize(new org.openqa.selenium.Dimension(375, 667));
        verifyMainVisuals();
    }

    private void verifyMainVisuals() {
        try {
            By mainVisual = By.cssSelector(".main-slider img, .featured-product img, .promo-banner img");
            WebElement visibleItem = wait.until(ExpectedConditions.visibilityOfElementLocated(mainVisual));
            boolean isVisible = visibleItem.isDisplayed();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
