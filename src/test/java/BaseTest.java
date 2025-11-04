import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;


import javax.swing.*;
import java.time.Duration;

public class BaseTest {
    protected static WebDriver driver;
    protected static final String websiteUrl = "https://www.ekupi.ba";

    protected WebDriverWait wait;
    protected Actions actions;

    @BeforeEach
    public void setup() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);

        driver.get(websiteUrl);

    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
