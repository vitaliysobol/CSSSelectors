import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SimpleSelectors {

    private WebDriver ffDriver;

    @Before
    public void DriverStart() {
        ffDriver = new FirefoxDriver();
    }

    @After
    public void DriverQuit() {
        ffDriver.quit();
    }

    @Test
    public void PageResresh() {
        ffDriver.get("http://comments.azurewebsites.net/");
        ffDriver.findElement(By.linkText("Refresh")).click();
        String text = ffDriver.findElement(By.cssSelector("#title h1")).getText();
        Assert.assertEquals(text, "Comments");
    }

    @Test
    public void CssStyle() {
        ffDriver.get("http://comments.azurewebsites.net/");
        String color = ffDriver.findElement(By.tagName("h1")).getCssValue("color");
        Assert.assertEquals("rgba(255, 255, 255, 1)", color);
    }
}