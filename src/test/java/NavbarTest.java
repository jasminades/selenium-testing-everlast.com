import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NavbarTest extends BaseTest{

    @Test
    public void testPrimaryLinksVisibility() {
        List<WebElement> primaryLinks = driver.findElements(By.cssSelector(".nav__links--primary > span > a"));
        for (WebElement link : primaryLinks) {
            assertTrue(link.isDisplayed());
        }
    }

    @Test
    public void testPrimaryNavbarLinks() {
        int linkCount = driver.findElements(By.cssSelector(".nav__links--primary > span > a")).size();

        for (int i = 0; i < linkCount; i++) {
            List<WebElement> primaryLinks = driver.findElements(By.cssSelector(".nav__links--primary > span > a"));
            WebElement link = primaryLinks.get(i);

            String href = link.getAttribute("href");
            assertTrue(href != null && !href.isEmpty());

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", link);

            wait.until(ExpectedConditions.urlContains(href.replace(driver.getCurrentUrl(), "")));

            driver.navigate().back();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".nav__links--primary > span > a")));
        }
    }

    @Test
    public void testColoredLinks() {
        List<WebElement> coloredLinks = driver.findElements(By.cssSelector(".navcolorh"));

        for (WebElement link : coloredLinks) {
            String color = link.getCssValue("color");
            assertTrue(color.contains("rgb") || color.contains("#"));
        }
    }

    @Test
    public void testLogoNavigation() {
        WebElement logoLink = driver.findElement(By.cssSelector(".nav__left.js-site-logo a"));
        logoLink.click();

        wait.until(ExpectedConditions.urlToBe("https://www.ekupi.ba/bs/"));
        assertTrue(driver.getCurrentUrl().equals("https://www.ekupi.ba/bs/"));
    }

    @Test
    public void testKucanskiAparatiLink() {
        WebElement link = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.linkText("Kućanski aparati")
        ));

        assertTrue(link.isDisplayed());

        String href = link.getAttribute("href");
        assertTrue(href.endsWith("/bs/kucanski-aparati/c/10003"));
    }

    @Test
    public void testRacunariLink() {
        WebElement link = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.linkText("Računari")
        ));

        assertTrue(link.isDisplayed());

        String href = link.getAttribute("href");
        assertTrue(href.endsWith("/bs/racunari/c/10001"));
    }

    @Test
    public void testElektronikaLink() {
        WebElement link = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.linkText("Elektronika")
        ));
        assertTrue(link.isDisplayed());

        String href = link.getAttribute("href");
        assertTrue(href.endsWith("/bs/elektronika/c/10002"));
    }

    @Test
    public void testGamingLink() {
        WebElement link = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.linkText("Gaming")
        ));
        assertTrue(link.isDisplayed());

        String href = link.getAttribute("href");
        assertTrue(href.endsWith("/bs/elektronika/gaming/c/10018"));
    }

    @Test
    public void testDomiVrtLink() {
        WebElement link = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.linkText("Dom i vrt")
        ));
        assertTrue(link.isDisplayed());

        String href = link.getAttribute("href");
        assertTrue(href.endsWith("/bs/dom-i-vrt/c/10006"));
    }

    @Test
    public void testAlatiiMasine() {
        WebElement link = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.linkText("Alati i mašine")
        ));
        assertTrue(link.isDisplayed());

        String href = link.getAttribute("href");
        assertTrue(href.endsWith("/bs/alati-i-masine/c/10037"));
    }

    @Test
    public void testAutoOprema() {
        WebElement link = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.linkText("Auto i moto oprema")
        ));
        assertTrue(link.isDisplayed());

        String href = link.getAttribute("href");
        assertTrue(href.endsWith("/bs/auto-i-moto-oprema/c/10005"));
    }

    @Test
    public void testFitness() {
        WebElement link = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.linkText("Fitness")
        ));
        assertTrue(link.isDisplayed());

        String href = link.getAttribute("href");
        assertTrue(href.endsWith("/bs/fitness-i-vjezbanje/c/10031"));
    }

    @Test
    public void testEmobility() {
        WebElement link = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.linkText("E-mobility")
        ));
        assertTrue(link.isDisplayed());

        String href = link.getAttribute("href");
        assertTrue(href.endsWith("/bs/elektricni-bicikli-i-romobili/c/13183"));
    }

    @Test
    public void testIgracke() {
        WebElement link = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.linkText("Igračke i dječja oprema")
        ));
        assertTrue(link.isDisplayed());

        String href = link.getAttribute("href");
        assertTrue(href.endsWith("/bs/igracke-i-djecija-oprema/c/10008"));
    }

    @Test
    public void testSupermarket() {
        WebElement link = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.linkText("Supermarket")
        ));
        assertTrue(link.isDisplayed());

        String href = link.getAttribute("href");
        assertTrue(href.endsWith("/bs/supermarket/c/10011"));
    }

    @Test
    public void testPromocije() {
        WebElement link = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.linkText("Promocije")
        ));
        assertTrue(link.isDisplayed());

        String href = link.getAttribute("href");
        assertTrue(href.endsWith("https://www.ekupi.ba/promotions"));
    }

    @Test
    public void testOutlet() {
        WebElement link = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.linkText("Kupkov Outlet")
        ));
        assertTrue(link.isDisplayed());

        String href = link.getAttribute("href");
        assertTrue(href.endsWith("/bs/Kupkov-Outlet"));
    }

}
