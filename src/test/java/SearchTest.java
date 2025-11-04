import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class SearchTest extends BaseTest {

    @BeforeEach
    public void openHomePage() {
        driver.get("https://www.ekupi.ba/bs/");
    }

    @Test
    public void testSearchButtonDisabledWhenEmpty() {
        WebElement searchInput = driver.findElement(By.id("js-site-search-input"));
        WebElement searchButton = driver.findElement(By.cssSelector("button.js_search_button"));

        assertTrue(searchButton.getAttribute("disabled") != null);

        searchInput.sendKeys(" ");
        assertTrue(searchButton.getAttribute("disabled") != null);
    }

    @Test
    public void testSearchButtonEnabledAfterInput() {
        WebElement searchInput = driver.findElement(By.id("js-site-search-input"));
        WebElement searchButton = driver.findElement(By.cssSelector("button.js_search_button"));

        searchInput.sendKeys("laptop");

        new WebDriverWait(driver, Duration.ofSeconds(3)).until(driver1 ->
                searchButton.isEnabled());

        assertTrue(searchButton.isEnabled());
    }

    @Test
    public void testAutocompleteAppears() {
        WebElement searchInput = driver.findElement(By.id("js-site-search-input"));
        searchInput.sendKeys("lap");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement autocompleteList = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ui-id-1")));

        assertTrue(autocompleteList.isDisplayed());
    }


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

