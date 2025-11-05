import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class HTTPSTest extends BaseTest {

    @Test
    public void testRedirectToHTTPS() throws Exception {
        String httpUrl = "http://www.ekupi.ba/bs";
        URL url = new URL(httpUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setInstanceFollowRedirects(false);
        connection.connect();

        int status = connection.getResponseCode();
        String location = connection.getHeaderField("Location");

        Assertions.assertTrue(status == 301 || status == 302,
                "got: " + status);
        Assertions.assertNotNull(location);
        Assertions.assertTrue(location.startsWith("https://"),
                "got: " + location);
    }

    @Test
    public void testHTTPS() throws Exception {
        String httpsUrl = "https://www.ekupi.ba/bs";
        URL url = new URL(httpsUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.connect();

        int status = connection.getResponseCode();
        Assertions.assertEquals(200, status, "got: " + status);
    }

    @Test
    public void testHTTPSLinks() {
        List<WebElement> links = driver.findElements(By.tagName("a"));
        for (WebElement link : links) {
            String href = link.getAttribute("href");
            if (href != null && href.startsWith("http")) {
                Assertions.assertTrue(href.startsWith("https://"),
                        "got: " + href);
            }
        }
    }

    @Test
    public void testLoginHTTPS() {
        driver.get("https://www.ekupi.ba/bs/prijava");

        WebElement form = driver.findElement(By.tagName("form"));
        String action = form.getAttribute("action");
        Assertions.assertTrue(action.startsWith("https://"),
                "got: " + action);
        driver.quit();
    }

    @Test
    public void testHSTS() throws Exception {
        URL url = new URL("https://www.ekupi.ba/bs");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.connect();

        String hsts = connection.getHeaderField("Strict-Transport-Security");
        Assertions.assertNotNull(hsts);
    }
}
