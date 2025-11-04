import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SocialsTest extends BaseTest{
    @Test
    public void testFacebookLink() {
        WebElement fbIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("a[href='https://www.facebook.com/ekupiba']")));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", fbIcon);

        assertTrue(fbIcon.isDisplayed());
        String href = fbIcon.getAttribute("href");
        assertTrue(href.contains("facebook.com/ekupiba"));
    }

    @Test
    public void testInstagramLink() {
        WebElement ig = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("a[href='https://www.instagram.com/ekupi_ba/']")));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ig);
        assertTrue(ig.isDisplayed());
        assertTrue(ig.getAttribute("href").contains("instagram.com/ekupi_ba"));
    }

    @Test
    public void testLinkedinLink() {
        WebElement linkedinIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("a[href='https://www.linkedin.com/company/ekupi/']")));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", linkedinIcon);
        assertTrue(linkedinIcon.isDisplayed());
        assertTrue(linkedinIcon.getAttribute("href").contains("linkedin.com/company/ekupi"));
    }

    @Test
    public void testYouTubeLink() {
        WebElement ytIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("a[href='https://www.youtube.com/@ekupiba']")));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ytIcon);
        assertTrue(ytIcon.isDisplayed());
        assertTrue(ytIcon.getAttribute("href").contains("youtube.com/@ekupiba"));
    }

    @Test
    public void testTikTokLink() {
        WebElement tiktokIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("a[href='https://www.tiktok.com/@ekupi.eu/']")));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", tiktokIcon);
        assertTrue(tiktokIcon.isDisplayed());
        assertTrue(tiktokIcon.getAttribute("href").contains("tiktok.com/@ekupi.eu"));
    }

    @Test
    public void testSupportEmail() {
        WebElement emailLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("a[href='mailto:podrska@ekupi.ba?subject=Upit']")));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", emailLink);
        assertTrue(emailLink.isDisplayed());
        assertTrue(emailLink.getAttribute("href").startsWith("mailto:podrska@ekupi.ba"));
    }
}
