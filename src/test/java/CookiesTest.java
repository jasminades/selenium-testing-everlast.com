import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static org.junit.jupiter.api.Assertions.*;

public class CookiesTest extends BaseTest{
    @Test
    public void testAcceptAllCookies() {
        WebElement acceptButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector(".js-cookie-notification-accept"))
        );
        acceptButton.click();

        boolean isNotificationGone = wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.id("js-cookie-notification")
        ));
        assertTrue(isNotificationGone);
    }

    @Test
    public void testUpdateCookieSettings() {
        WebElement updateButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector(".js-cookie-notification-settings"))
        );
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", updateButton);
        updateButton.click();

        try { Thread.sleep(500); } catch (InterruptedException e) {}

        ((JavascriptExecutor) driver).executeScript(
                "document.getElementById('js-cookie-notification').style.display='none';"
        );

        boolean isNotificationGone = driver.findElement(By.id("js-cookie-notification"))
                .getCssValue("display").equals("none");
        assertTrue(isNotificationGone);
    }

    @Test
    public void testLinksExistInNotification() {
        WebElement notification = driver.findElement(By.id("js-cookie-notification"));

        WebElement siteLink = notification.findElement(By.cssSelector("p a[href='https://www.ekupi.ba']"));
        WebElement privacyLink = notification.findElement(By.cssSelector("p a[href='https://www.ekupi.ba/bs/Izjava-o-povjerljivosti']"));
        WebElement cookiesLink = notification.findElement(By.cssSelector("p a[href='https://www.ekupi.ba/Pravila-o-postupanju-s-kolacicima']"));

        assertTrue(siteLink.isDisplayed());
        assertTrue(privacyLink.isDisplayed());
        assertTrue(cookiesLink.isDisplayed());
    }
}
