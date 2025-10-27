import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

public class FooterTest extends BaseTest {
    @Test
    public void testFooterLinks() {
        driver.get(websiteUrl);
        List<WebElement> footerLinks = driver.findElements(By.cssSelector(
                "div.footer__desktop-links-wrapper ul.footer_menu-ul li a"));
        for (WebElement link : footerLinks) {
            String url = link.getAttribute("href");
            Assert.assertNotNull(url, "Link href should not be null");
        }
    }

    @Test
    public void testSocialIcons() {
        driver.get(websiteUrl);

        WebElement footer = driver.findElement(By.tagName("footer"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", footer);

        By socialLinksSelector = By.cssSelector(
               "footer a[href*='facebook'], footer a[href*='twitter'], footer a[href*='instagram']"
        );
        List<WebElement> socialLinks = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(socialLinksSelector));

        Assert.assertTrue(socialLinks.size() > 0, "No social icons found");

        for(WebElement link : socialLinks) {
            Assert.assertNotNull(link.getAttribute("href"));
        }
    }

    @Test
    public void testNewsletter() {
        driver.get(websiteUrl);

        WebElement input = driver.findElement(By.cssSelector(
                "div.footer-newsletter__form-wrapper input[type='email']"
        ));

        WebElement submit = driver.findElement(By.cssSelector(
                "div.footer-newsletter__form-wrapper button[type='submit']"
        ));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", input);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", submit);

        ((JavascriptExecutor) driver).executeScript(
                "var banners = document.querySelectorAll('.cookie-banner, .newsletter-popup');" +
                        "banners.forEach(b => b.style.display='none');"
        );

        Runnable clickSubmit = () -> ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submit);

        input.clear();
        input.sendKeys("testni@mail.com");
        clickSubmit.run();

        input.clear();
        input.sendKeys("invalid-email");
        clickSubmit.run();

    }

    @Test
    public void testPaymentIcon() {
        driver.get(websiteUrl);
        List<WebElement> paymentIcon = driver.findElements(By.cssSelector("ul.footer__payment-ul li"));
        Assert.assertTrue(paymentIcon.size() > 0, "Payment icon visible");

        for(WebElement icon : paymentIcon) {
            Assert.assertTrue(icon.isDisplayed(), "Payment icon displayed");
        }
    }

    @Test
    public void testCopyright() {
        driver.get(websiteUrl);
        WebElement footerText = driver.findElement(By.cssSelector("div.footer_copyright-text-wrapper p"));
        String expectedText = "© 2025 EVERLAST WORLDWIDE INC.";
        Assert.assertEquals(footerText.getText(), expectedText, "Text should match");
    }
}
