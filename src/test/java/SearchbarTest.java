import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import static org.junit.jupiter.api.Assertions.*;
import java.time.Duration;
import java.util.List;

public class SearchTest extends BaseTest {

    @BeforeEach
    public void openHomePage() {
        driver.get("https://www.ekupi.ba/bs/");
    }

    // radi
    @Test
    public void testSearchButtonDisabledWhenEmpty() {
        WebElement searchInput = driver.findElement(By.id("js-site-search-input"));
        WebElement searchButton = driver.findElement(By.cssSelector("button.js_search_button"));

        assertTrue(searchButton.getAttribute("disabled") != null);

        searchInput.sendKeys(" ");
        assertTrue(searchButton.getAttribute("disabled") != null);
    }

    // radi
    @Test
    public void testSearchButtonEnabledAfterInput() {
        WebElement searchInput = driver.findElement(By.id("js-site-search-input"));
        WebElement searchButton = driver.findElement(By.cssSelector("button.js_search_button"));

        searchInput.sendKeys("laptop");

        new WebDriverWait(driver, Duration.ofSeconds(3)).until(driver1 ->
                searchButton.isEnabled());

        assertTrue(searchButton.isEnabled());
    }

    // radi
    @Test
    public void testAutocompleteAppears() {
        WebElement searchInput = driver.findElement(By.id("js-site-search-input"));
        searchInput.sendKeys("lap");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement autocompleteList = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ui-id-1")));

        assertTrue(autocompleteList.isDisplayed());
    }


    @Test
    public void testSearchResultsPage() {

        // 1️⃣ Locate search input and button
        WebElement searchInput = driver.findElement(By.id("js-site-search-input"));
        WebElement searchButton = driver.findElement(By.cssSelector("button.js_search_button"));

        // 2️⃣ Enter search term
        searchInput.clear();
        searchInput.sendKeys("telefon");

        // 3️⃣ Wait for search button to be clickable and click it
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", searchButton);

        // 4️⃣ Wait until page URL changes to search results page
        wait.until(driver -> driver.getCurrentUrl().contains("search"));

        // 5️⃣ Wait for the results container to be visible
        WebElement resultsSection = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".product__listing--wrapper, .product__list--wrapper")));

        assertTrue(resultsSection.isDisplayed(), "Search results section should be visible");

        // 6️⃣ Wait for at least one product to appear (handles AJAX load)
        List<WebElement> products = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(
                By.cssSelector(".product__item"), 0
        ));

        assertTrue(products.size() > 0, "There should be at least one product in results");

        // 7️⃣ Optional: verify that product titles or descriptions contain search keyword
        boolean containsKeyword = products.stream()
                .anyMatch(product -> product.getText().toLowerCase().contains("telefon"));

        assertTrue(containsKeyword, "At least one product should contain the search keyword in its text");
    
    }

    // radi
    @Test
    public void testNoResultsMessage() {
        WebElement searchInput = driver.findElement(By.id("js-site-search-input"));
        WebElement searchButton = driver.findElement(By.cssSelector("button.js_search_button"));

        searchInput.sendKeys("asdfghqwertyzxcvbn123");
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(searchButton));
        searchButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement noResultsMsg = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector(".no-results, .alert-info, .product__no-results")));
        assertTrue(noResultsMsg.isDisplayed());
    }
}
