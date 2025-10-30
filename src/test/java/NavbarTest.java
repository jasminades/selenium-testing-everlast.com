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

public class NavbarTest extends BaseTest {

    @Test
    public void testNavbar() {
        driver.get(websiteUrl);
        WebElement navbar = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("nav.header_inline-menu, nav.header__inline-menu")
        ));
        assertTrue(navbar.isDisplayed(),"Navbar is supposed to be visible");
    }

    @Test
    public void testMainMenuItems() {
        driver.get(websiteUrl);

        List<WebElement> mainMenuItems = driver.findElements(
                By.cssSelector("nav.header__inline-menu > ul.list-menu > li")
        );

        assertTrue(mainMenuItems.size() > 0, "An item is expected");

        for (WebElement item : mainMenuItems) {
            String text = item.getText().trim();
            assertFalse(text.isEmpty(), "Menu item text should not be empty");
            assertTrue(item.isDisplayed(), "Menu item should be visible");
        }
    }

    @Test
    public void testDropdown() {
        driver.get(websiteUrl);

        List<WebElement> dropdowns = driver.findElements(
                By.cssSelector("nav.header__inline-menu details.mega-menu")
        );

        assertTrue(dropdowns.size() > 0, "Expected dropdown menu elements");

        for (WebElement dropdown : dropdowns) {
            try{
                WebElement summary = dropdown.findElement(By.cssSelector("summary"));
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", summary);
                actions.moveToElement(summary).perform();
                summary.click();

                WebElement menuContent = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("div.mega-menu__child-ul-wrapper, div.mega-menu__content")
                ));

                assertTrue(menuContent.isDisplayed(), "Dropdown menu should appear after click");

                List<WebElement> links = menuContent.findElements(By.cssSelector("a[href]"));
                if(!links.isEmpty()) {
                    WebElement firstLink = links.get(0);
                    String href = firstLink.getAttribute("href");
                    assertNotNull(href, "Dropdown link should have an href attribute");

                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", firstLink);
                    wait.until(ExpectedConditions.urlContains(href));

                    String currentUrl = driver.getCurrentUrl();
                    assertTrue(currentUrl.contains(href.replace(websiteUrl, "")),
                            "Dropdown link did not lead to the correct page");

                    driver.navigate().back();
                    wait.until(ExpectedConditions.urlContains("everlast.com"));
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}