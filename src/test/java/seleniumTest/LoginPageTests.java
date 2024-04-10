package seleniumTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginPageTests {

    public static final String LOGIN_PAGE_URL = "http://training.skillo-bg.com:4200/users/login";
    public static final String USERNAME = "gandalf";
    public static final String PASSWORD = "thegray";
    public static final String USERNAME_FIELD_LOCATOR = "#defaultLoginFormUsername";
    public static final String PASSWORD_FIELD_LOCATOR = "#defaultLoginFormPassword";
    public static final String REMEMBER_ME_CHECKBOX_LOCATOR = "//div[contains(@class, \"remember-me\")]/input";
    public static final String SIGN_IN_BTN_LOCATOR = "#sign-in-button";
    public static final String LOGOUT_BTN_LOCATOR = "//*[@id=\"navbarColor01\"]/ul[2]/li/a/i";

    WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));


    @BeforeMethod
    public void setUpBrowser() {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(LOGIN_PAGE_URL);
        wait.until(ExpectedConditions.urlContains(LOGIN_PAGE_URL));
    }

    @Test
    public void verifyUserCanLogin() throws InterruptedException {
        Thread.sleep(2000);
        //Locate username form and input the username
        WebElement username = driver.findElement(By.cssSelector(USERNAME_FIELD_LOCATOR));
        wait.until(ExpectedConditions.visibilityOf(username));
        username.clear();
        username.sendKeys(USERNAME);

        Thread.sleep(2000);
        //Locate password form and input the password
        WebElement password = driver.findElement(By.cssSelector(PASSWORD_FIELD_LOCATOR));
        wait.until(ExpectedConditions.visibilityOf(password));
        password.clear();
        password.sendKeys(PASSWORD);

        Thread.sleep(2000);
        //Locate and click "Remember me" checkbox
        WebElement checkbox = driver.findElement(By.xpath(REMEMBER_ME_CHECKBOX_LOCATOR));
        wait.until(ExpectedConditions.visibilityOf(checkbox));
        checkbox.click();

        Thread.sleep(2000);
        //Verify the user selected "Remember me" checkbox
        boolean isRememberMeSelected = checkbox.isSelected();
        System.out.println("User clicks on \"Remember me\" :: " + isRememberMeSelected);

        Thread.sleep(2000);
        //Locate and click "Sign in" button
        WebElement signInBtn = driver.findElement(By.cssSelector(SIGN_IN_BTN_LOCATOR));
        wait.until(ExpectedConditions.visibilityOf(signInBtn));
        signInBtn.click();

        Thread.sleep(2000);
        //Verify the user is successfully logged in
        WebElement logoutBtn = driver.findElement(By.xpath(LOGOUT_BTN_LOCATOR));
        boolean isLogoutButtonDisplayed = logoutBtn.isDisplayed();
        Assert.assertTrue(isLogoutButtonDisplayed, "The user is expecting to see the logout button but it is not displayed.");
        System.out.println("User sees Logout button :: " + isLogoutButtonDisplayed);

        Thread.sleep(2000);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
