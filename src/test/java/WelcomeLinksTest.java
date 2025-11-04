import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WelcomeLinksTest extends BaseTest{
    @Test
    public void testWelcomeLinks() {
        WebElement welcomeSection = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div.welcome")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", welcomeSection);
        assertTrue(welcomeSection.isDisplayed());
    }

    @Test
    public void testKakoSeRegistrovatiLink() {
        WebElement registerLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div[onclick*='/Kako-se-registrovati']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", registerLink);
        assertTrue(registerLink.isDisplayed());
        assertTrue(registerLink.getAttribute("onclick").contains("/Kako-se-registrovati"));
    }

    @Test
    public void testReklamacijeLink() {
        WebElement reklamacijeLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div[onclick*='/Reklamacije']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", reklamacijeLink);
        assertTrue(reklamacijeLink.isDisplayed());
        assertTrue(reklamacijeLink.getAttribute("onclick").contains("/Reklamacije"));
    }

    @Test
    public void testNarudzbeTelefonomLink() {
        WebElement phoneOrderLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div[onclick*='/Narudzbe-telefonom-ili-e-mailom']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", phoneOrderLink);
        assertTrue(phoneOrderLink.isDisplayed());
        assertTrue(phoneOrderLink.getAttribute("onclick").contains("/Narudzbe-telefonom-ili-e-mailom"));
    }

    @Test
    public void testZastoKupovatiLink() {
        WebElement benefitsLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div[onclick*='/eKupi-Benefiti']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", benefitsLink);
        assertTrue(benefitsLink.isDisplayed());
        assertTrue(benefitsLink.getAttribute("onclick").contains("/eKupi-Benefiti"));
    }

}
