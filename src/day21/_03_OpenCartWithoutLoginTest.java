package day21;

import day20.OpenCartDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class _03_OpenCartWithoutLoginTest extends OpenCartDriver {

    private OpenCartPage page;

    @BeforeClass
    void initPage() {
        this.page = new OpenCartPage(driver);
    }

    @Test(dataProvider = "searchTermDataProvider")
    void searchTestCase(String searchTerm){
        System.out.println("initail searchterm: "+searchTerm);
        // type iphone in search bar
        methods.clearAndSendKeys(By.cssSelector("#search > input"), searchTerm);
        page.searchButton.click();
        // validate that result have iphone in their product title
        methods.verifyAllContainsText(page.products, searchTerm);
    }

    @DataProvider(name = "searchTermDataProvider")
    public Object[][] data2() {
        return new Object[][]{
                {"iphone"},
                {"MacBook"},
                {"apple cinema"},
                {"Canon"}
        };
    }

    // task 5
    @Test
    public void contactUsTest() {
        // go to contact us page
        driver.findElement(By.cssSelector("a[href*=contact]")).click();
        wait.until(ExpectedConditions.titleIs("Contact Us"));
        // fill in form click submit
        driver.findElement(By.cssSelector("#input-name")).sendKeys("AYSEGUL");
        driver.findElement(By.cssSelector("#input-email")).sendKeys("aa@gmail.com");
        driver.findElement(By.cssSelector("textarea[name='enquiry']")).sendKeys("blabla bla blabla");
        driver.findElement(By.cssSelector("input[value='Submit']")).click();
        // verify "Your enquiry has been successfully sent to the store owner!"
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#content>p")));
        Assert.assertEquals("Your enquiry has been successfully sent to the store owner!",driver.findElement(By.cssSelector("#content>p")).getText());
    }
}
