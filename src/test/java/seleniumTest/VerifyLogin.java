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

        //Delay of 1.25 seconds
        Thread.sleep(1250);

        //Locate username form and input the username
        driver.findElement(By.cssSelector("#defaultLoginFormUsername")).sendKeys(USERNAME);

        //Locate password form and input the password
        driver.findElement(By.cssSelector("#defaultLoginFormPassword")).sendKeys(PASSWORD);

        //The user clicks Remember me
        driver.findElement(By.xpath("/html/body/app-root/div[2]/app-login/div/div/form/div/input")).click();

        //Verify the user is selected Remember me
        Boolean isRememberMeSelected = driver.findElement(By.xpath("/html/body/app-root/div[2]/app-login/div/div/form/div/input")).isSelected();
        System.out.println("User clicks on \"Remember me\" :: " + isRememberMeSelected);

        //Click Sign in button
        driver.findElement(By.cssSelector("#sign-in-button")).click();

        //Delay of 1.25 seconds
        Thread.sleep(1250);

        //Verify the user is successfully logged in
        Boolean isLogoutButtonDisplayed = driver.findElement(By.xpath("//*[@id=\"navbarColor01\"]/ul[2]/li/a/i")).isDisplayed();
        Assert.assertTrue(isLogoutButtonDisplayed, "The user is expecting to see the logout button but it is not displayed.");
        System.out.println("User sees Logout button :: " + isLogoutButtonDisplayed);

        //Delay of 1 seconds
        Thread.sleep(1000);
    }

    @AfterClass
    public void tearDown() throws Exception {
        //Quit the program
        driver.quit();
    }
}
