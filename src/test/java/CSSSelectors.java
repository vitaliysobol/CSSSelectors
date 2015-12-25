import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class CSSSelectors {

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
    public void CreateNewComment() {

        ffDriver.get("http://comments.azurewebsites.net/");
        ffDriver.findElement(By.cssSelector("a[href*='/?sort=NumberValue&Text=ASC']")).click();
        ffDriver.findElement(By.cssSelector("a[href*='/?sort=NumberValue&Text=DESC']")).click();
        String lastNumber = ffDriver.findElement(By.cssSelector("tr:nth-child(1) > td.numbercolumn")).getText();
        int lastIntNumber = Integer.parseInt(lastNumber);
        lastIntNumber++;
        lastIntNumber++;  //comment with numebr 30 can not be created WHY????
        String lastStrNumber = Integer.toString(lastIntNumber);
        ffDriver.findElement(By.cssSelector("[onclick='RedirectToNewComment()']")).click();
        ffDriver.findElement(By.cssSelector("input#Text")).sendKeys("Comment" + System.currentTimeMillis());
        ffDriver.findElement(By.cssSelector("input#Number")).sendKeys(lastStrNumber);
        ffDriver.findElement(By.cssSelector("[onclick='SelectAll()']")).click();
        ffDriver.findElement(By.cssSelector("[value='Save & Return']")).click();
        ffDriver.findElement(By.cssSelector("[href*='/?sort=NumberValue&Text=ASC']")).click();
        ffDriver.findElement(By.cssSelector("[href*='/?sort=NumberValue&Text=DESC']")).click();
        String createdItemNumber = ffDriver.findElement(By.cssSelector("tr:nth-child(1) > td.numbercolumn")).getText();
        Assert.assertEquals(lastStrNumber, createdItemNumber);
    }

    @Test
    public void DuplicateComment() throws InterruptedException {
        ffDriver.get("http://comments.azurewebsites.net/");
        ffDriver.findElement(By.cssSelector("a[href*='/?sort=NumberValue&Text=ASC']")).click();
        ffDriver.findElement(By.cssSelector("a[href*='/?sort=NumberValue&Text=DESC']")).click();
        String oldComment = ffDriver.findElement(By.cssSelector("tr:nth-child(1) > td.textcolumn")).getText();
        ffDriver.findElement(By.cssSelector("input[type=\"checkbox\"]:nth-child(1)")).click();
        String lastNumber = ffDriver.findElement(By.cssSelector("tr:nth-child(1) > td.numbercolumn")).getText();
        int lastIntNumber = Integer.parseInt(lastNumber);
        lastIntNumber++;
        lastIntNumber++;
        String lastStrNumber = Integer.toString(lastIntNumber);
        ffDriver.findElement(By.cssSelector("[value='Duplicate...']")).click();
        ffDriver.findElement(By.cssSelector("input#Number")).clear();
        ffDriver.findElement(By.cssSelector("input#Number")).sendKeys(lastStrNumber);
        ffDriver.findElement(By.cssSelector("[value='Save & Return']")).click();
        ffDriver.findElement(By.cssSelector("a[href*='/?sort=NumberValue&Text=ASC']")).click();
        ffDriver.findElement(By.cssSelector("a[href*='/?sort=NumberValue&Text=DESC']")).click();
        String newText = ffDriver.findElement(By.cssSelector("tr:nth-child(1) > td.textcolumn")).getText();
        Assert.assertEquals(newText, "Copy of" + oldComment);
    }

    @Test
    public void PageResresh() {
        ffDriver.get("http://comments.azurewebsites.net/");
        ffDriver.findElement(By.cssSelector("a[href='/']")).click();
        ffDriver.navigate().refresh();
        String text = ffDriver.findElement(By.cssSelector("#title h1")).getText();
        Assert.assertEquals(text, "Comments");
    }

    @Test
    public void CssStyle() {
        ffDriver.get("http://comments.azurewebsites.net/");
        String color = ffDriver.findElement(By.cssSelector("#title h1")).getCssValue("color");
        Assert.assertEquals("rgba(255, 255, 255, 1)", color);
    }

    @Test
    public void ChangeComment() throws InterruptedException {
        ffDriver.get("http://comments.azurewebsites.net/");
        ffDriver.findElement(By.cssSelector("a[href*='/?sort=NumberValue&Text=ASC']")).click();
        ffDriver.findElement(By.cssSelector("a[href*='/?sort=NumberValue&Text=DESC']")).click();
        ffDriver.findElement(By.cssSelector("input[type=\"checkbox\"]:nth-child(1)")).click();
        ffDriver.findElement(By.cssSelector("[value='Edit...']")).click();
        String commentText = "Comment" + System.currentTimeMillis();
        ffDriver.findElement(By.cssSelector("input#Text")).clear();
        ffDriver.findElement(By.cssSelector("input#Text")).sendKeys(commentText);
        ffDriver.findElement(By.cssSelector("[value='Save & Return']")).click();
        ffDriver.findElement(By.cssSelector("a[href*='/?sort=NumberValue&Text=ASC']")).click();
        ffDriver.findElement(By.cssSelector("a[href*='/?sort=NumberValue&Text=DESC']")).click();
        String changedCommentText = ffDriver.findElement(By.cssSelector("tr:nth-child(1) > td.textcolumn")).getText();
        Assert.assertEquals(commentText, changedCommentText);
    }

    @Test
    public void DeleteComment() {
        ffDriver.get("http://comments.azurewebsites.net/");
        ffDriver.findElement(By.cssSelector("a[href*='/?sort=NumberValue&Text=ASC']")).click();
        ffDriver.findElement(By.cssSelector("a[href*='/?sort=NumberValue&Text=DESC']")).click();
        ffDriver.findElement(By.cssSelector("input[type=\"checkbox\"]:nth-child(1)")).click();
        String lastNumber = ffDriver.findElement(By.cssSelector("tr:nth-child(1) > td.numbercolumn")).getText();
        ffDriver.findElement(By.cssSelector("[value='Delete']")).click();
        ffDriver.findElement(By.cssSelector("button:nth-child(1)")).click();
        String infoField = ffDriver.findElement(By.cssSelector("[id='infoField']")).getText();
        Assert.assertEquals("Selected comments deleted successfull", infoField);
    }

}