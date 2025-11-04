import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class FeaturedItemsTest extends BaseTest {
    @Test
    void testCarouselVisible() {
        WebElement carousel = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".carousel__component")));
        assertTrue(carousel.isDisplayed());
    }

    @Test
    public void testImagesAreDisplayed() {
        List<WebElement> images = driver.findElements(By.cssSelector(".grid-container img"));
        for (WebElement img : images) {
            Assertions.assertTrue(img.isDisplayed(), "Image should be visible: " + img.getAttribute("alt"));
        }
    }

    @Test
    public void testImagesHaveSrcAndAlt() {
        List<WebElement> images = driver.findElements(By.cssSelector(".grid-container img"));
        for (WebElement img : images) {
            String src = img.getAttribute("src");
            String alt = img.getAttribute("alt");
            Assertions.assertFalse(src.isEmpty());
            Assertions.assertFalse(alt.isEmpty());
        }
    }

    @Test
    public void testImagesAreClickable() {
        List<WebElement> links = driver.findElements(By.cssSelector(".grid-container a"));
        for (WebElement link : links) {
            Assertions.assertTrue(link.isDisplayed(), "Link should be visible: " + link.getAttribute("href"));
            Assertions.assertTrue(link.isEnabled(), "Link should be clickable: " + link.getAttribute("href"));
        }
    }

}
