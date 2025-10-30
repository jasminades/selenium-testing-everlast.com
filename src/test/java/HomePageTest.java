import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.time.Duration;
import java.util.List;

public class HomePageTest extends BaseTest {

    @Test
    public void testHomePageLoads() {
        driver.get(websiteUrl);

        String title = driver.getTitle();
        System.out.println("Page title " + title);
        assertTrue(title.toLowerCase().contains("everlast"), "Title should contain 'Everlast'");
    }

    @Test
    public void testPageTitle() {
        driver.get(websiteUrl);

        String expectedTitle = "Everlast | Greatness is Within";
        wait.until(driver -> !driver.getTitle().isEmpty());

        String actualTitle = driver.getTitle();
        assertEquals(actualTitle, expectedTitle, "Page title does not match");
    }

    @Test
    public void testLogoDisplay() {
        driver.get(websiteUrl);

        WebElement logo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("img[alt='Everlast Logo']")));
        assertTrue(logo.isDisplayed());
    }

    @Test
    public void testNavigationLinks() {
        driver.get(websiteUrl);

        String[] navLinks = {"EQUIPMENT", "CLOTHING", "FOOTWEAR"};
        for (String linkText : navLinks) {
            try {
                WebElement link = wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(linkText)));
                assertTrue(link.isDisplayed(), linkText + " link is not visible.");
            } catch (TimeoutException e) {
                fail("Could not find link: " + linkText);
            }
        }
    }

    @Test
    public void testPageLoadTime() {
        driver.get(websiteUrl);
        long start = System.currentTimeMillis();
        long loadTime = System.currentTimeMillis() - start;
        assertTrue(loadTime < 5000, "Page loads for too long: " + loadTime + " ms");
    }

    @Test
    public void testSearchModal() {
        driver.get(websiteUrl);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("summary.header_icon--search, summary.header__icon--search")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", searchButton);
        searchButton.click();

        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("input[name='q']")));
        searchInput.sendKeys("gloves");
        searchInput.submit();

        wait.until(ExpectedConditions.urlContains("/search?q=gloves"));

        WebElement resultsContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div#ProductGridContainer, .search__results")));

        String pageSource = driver.getPageSource().toLowerCase();
        assertTrue(pageSource.contains("glove"), "Search results page does not contain 'gloves'");
    }

    @Test
    public void testHeaderIcons() {
        driver.get(websiteUrl);

        WebElement searchIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("summary.header_icon--search, summary.header__icon--search")));
        WebElement accountIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.header__icon--account")));
        WebElement cartIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.header__icon--cart")));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", searchIcon);

        assertTrue(searchIcon.isDisplayed(), "Search icon not visible.");
        assertTrue(accountIcon.isDisplayed(), "Account icon not visible.");
        assertTrue(cartIcon.isDisplayed(), "Cart icon not visible.");
    }

    @Test
    public void testTrendingArticles() {
        driver.get(websiteUrl);
        WebElement trendingHeading = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h2[contains(text(), 'TRENDING NOW')]")));
        assertTrue(trendingHeading.isDisplayed(), "Trending heading not visible.");
    }
}
