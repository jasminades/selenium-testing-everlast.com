import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProfileTest extends BaseTest {

    @BeforeEach
    public void login() {
        driver.get("https://www.ekupi.ba/bs/login");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("j_username")))
                .sendKeys("jasmina.destanovic@stu.ibu.edu.ba");
        driver.findElement(By.id("j_password")).sendKeys("svvttest123");
        driver.findElement(By.id("submit")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Moj profil")));
    }

    @Test
    @Order(1)
    public void testMojProfil() {
        driver.findElement(By.linkText("Moj profil")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".account-nav_left")));

        WebElement title = driver.findElement(By.cssSelector(".account-nav_left h3.title"));
        Assertions.assertEquals("Moj račun", title.getText().trim());

        List<WebElement> navLinks = driver.findElements(By.cssSelector(".accLinkList li a"));
        String[] expectedLinks = {
                "Istorija Narudžbi",
                "Sačuvane košarice",
                "Ažurirajte Lične Podatke",
                "Promjena lozinke",
                "Podaci O Plaćanju",
                "Gašenje profila"
        };

        for (String expected : expectedLinks) {
            boolean found = navLinks.stream()
                    .anyMatch(link -> link.getText().trim().equalsIgnoreCase(expected));
            Assertions.assertTrue(found, "missing sidebar link: " + expected);
        }

        WebElement header = driver.findElement(By.cssSelector(".account-section-header"));
        Assertions.assertTrue(header.getText().contains("Istorija"));
    }

    @Test
    @Order(2)
    public void testIstorijaNarudzbi() {
        driver.findElement(By.linkText("Moj profil")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Istorija Narudžbi"))).click();

        wait.until(ExpectedConditions.urlContains("/my-account/orders"));
        Assertions.assertTrue(driver.getCurrentUrl().contains("/orders"));

        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".account-section-header")));
        String headerText = header.getText().trim();
        Assertions.assertTrue(headerText.toLowerCase().contains("istorija"));

        WebElement content = driver.findElement(By.cssSelector(".account-section-content"));
        Assertions.assertTrue(content.getText().toLowerCase().contains("narudžbi"));
    }

    @Test
    @Order(3)
    public void testSacuvaneKosarice() {
        driver.findElement(By.linkText("Moj profil")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Sačuvane košarice"))).click();

        wait.until(ExpectedConditions.urlContains("/my-account/saved-carts"));
        Assertions.assertTrue(driver.getCurrentUrl().contains("/saved-carts"));

        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".account-section-header")));
        String headerText = header.getText().trim();
        Assertions.assertTrue(headerText.toLowerCase().contains("košaric"));

        WebElement content = driver.findElement(By.cssSelector(".account-section-content"));
        Assertions.assertTrue(content.isDisplayed());
    }

    @Test
    @Order(4)
    public void testAzurirajteProfil() {
        driver.findElement(By.linkText("Moj profil")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Ažurirajte Lične Podatke"))).click();

        wait.until(ExpectedConditions.urlContains("/my-account/update-profile"));
        Assertions.assertTrue(driver.getCurrentUrl().contains("/update-profile"));

        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".account-section-header")));
        String headerText = header.getText().trim();
        System.out.println("Header text found: " + headerText);

        Assertions.assertTrue(
                headerText.toLowerCase().contains("moji") ||
                        headerText.toLowerCase().contains("podatci") ||
                        headerText.toLowerCase().contains("adrese")
        );
    }

    @Test
    @Order(5)
    public void testPromjenaLozinke() {
        driver.findElement(By.linkText("Moj profil")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Promjena lozinke"))).click();

        wait.until(ExpectedConditions.urlContains("/my-account/update-password"));
        Assertions.assertTrue(driver.getCurrentUrl().contains("/update-password"));

        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".account-section-header")));
        String headerText = header.getText().trim();
        Assertions.assertTrue(
                headerText.toLowerCase().contains("lozinku") ||
                        headerText.toLowerCase().contains("ažurirajte")
        );


        WebElement content = driver.findElement(By.cssSelector(".account-section-content"));
        Assertions.assertTrue(content.isDisplayed());
    }

    @Test
    @Order(6)
    public void testPodaciOPlacanju() {
        driver.findElement(By.linkText("Moj profil")).click();

        WebElement paymentLink = wait.until(
                ExpectedConditions.elementToBeClickable(By.linkText("Podaci O Plaćanju"))
        );
        paymentLink.click();

        wait.until(ExpectedConditions.urlContains("/my-account/payment-details"));
        Assertions.assertTrue(driver.getCurrentUrl().contains("/payment-details"));

        WebElement header = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".account-section-header"))
        );
        String headerText = header.getText().trim();

        Assertions.assertTrue(
                headerText.toLowerCase().contains("plaćanju") ||
                        headerText.toLowerCase().contains("podaci")
        );
    }

    @Test
    @Order(7)
    public void testGasenjeProfila() {
        driver.findElement(By.linkText("Moj profil")).click();

        WebElement closeAccountLink = wait.until(
                ExpectedConditions.elementToBeClickable(By.linkText("Gašenje profila"))
        );
        closeAccountLink.click();

        wait.until(ExpectedConditions.urlContains("/my-account/close-account"));
        Assertions.assertTrue(driver.getCurrentUrl().contains("/close-account"));

        WebElement header = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".account-section-header.account-close-section-header"))
        );
        String headerText = header.getText().trim();

        Assertions.assertTrue(
                headerText.toLowerCase().contains("gašenju") ||
                        headerText.toLowerCase().contains("profil")
        );

        WebElement button = driver.findElement(By.cssSelector(".js-close-account-popup-button"));
        Assertions.assertTrue(button.isDisplayed());
        Assertions.assertEquals("UGASITE PROFIL", button.getText().trim());
    }



}
