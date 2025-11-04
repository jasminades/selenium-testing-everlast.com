import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartTest extends BaseTest {

    @BeforeEach
    public void setUp() {
        driver.get("https://www.ekupi.ba/bs/");
    }

    @Test
    public void testCartHeaderDisplayed() {
        WebElement cartHeader = driver.findElement(By.cssSelector("h1.cart-headline"));
        Assertions.assertTrue(cartHeader.isDisplayed());

        String cartId = driver.findElement(By.cssSelector(".cart__id")).getText();
        Assertions.assertNotNull(cartId);
    }

    @Test
    public void testEmptyCartMessage() {
        WebElement emptyMessage = driver.findElement(By.cssSelector(".content__empty .content p"));
        Assertions.assertEquals("Vaša košarica je prazna!", emptyMessage.getText());
    }

    @Test
    public void testClearCartLink() {
        WebElement clearCartLink = driver.findElement(By.cssSelector("a[onclick*='ACC.cart.clearCartCheck']"));
        Assertions.assertTrue(clearCartLink.isDisplayed());
        Assertions.assertEquals("Isprazni košaricu", clearCartLink.getText().trim());
    }
}
