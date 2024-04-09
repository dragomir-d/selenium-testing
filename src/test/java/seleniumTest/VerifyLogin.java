package seleniumTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class VerifyLogin {

    public final String URL = "http://training.skillo-bg.com:4200/users/login";
    public final String USERNAME = "gandalf";
    public final String PASSWORD = "thegray";

    WebDriver driver = new ChromeDriver();

    @Test
    public void verifyUserCanLogin() throws Exception {

        //Maximise current window
        driver.manage().window().maximize();

        //Navigate to website
        driver.get(URL);

        //Delay of 3.5 seconds
        Thread.sleep(3500);

        //Locate username form and input the username
        driver.findElement(By.cssSelector("#defaultLoginFormUsername")).sendKeys(USERNAME);

        //Locate password form and input the password
        driver.findElement(By.cssSelector("#defaultLoginFormPassword")).sendKeys(PASSWORD);

        //Click Sign in button
        driver.findElement(By.cssSelector("#sign-in-button")).click();

        //Delay of 5 seconds
        Thread.sleep(5000);

        //Verify the user is successfully logged in
        WebElement logoutButton = driver.findElement(By.xpath("//*[@id=\"navbarColor01\"]/ul[2]/li/a/i"));
        Assert.assertTrue(logoutButton.isDisplayed());

        System.out.print("The user can see logout button :::: ");
    }

    @AfterClass
    public void tearDown() throws Exception {
        //Quit the program
        driver.quit();
    }
}
