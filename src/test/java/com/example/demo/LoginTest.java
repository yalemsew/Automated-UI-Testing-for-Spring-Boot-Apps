package com.example.demo;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest {
    private WebDriver driver;

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://localhost:8080/login");
    }

    @Test
    void testValidLogin() throws InterruptedException {
        WebElement usernameField = driver.findElement(By.name("username"));
        WebElement passwordField = driver.findElement(By.name("password"));
        WebElement loginButton = driver.findElement(By.tagName("button"));

        usernameField.sendKeys("admin");
        passwordField.sendKeys("password");
        Thread.sleep(1000);
        loginButton.click();

        WebElement successMessage = driver.findElement(By.tagName("h2"));
        Thread.sleep(1000);
        assertEquals("Login Successful", successMessage.getText());
    }

    @Test
    void testInvalidLogin() throws InterruptedException {
        WebElement usernameField = driver.findElement(By.name("username"));
        WebElement passwordField = driver.findElement(By.name("password"));
        WebElement loginButton = driver.findElement(By.tagName("button"));

        usernameField.sendKeys("wrongUser");
        passwordField.sendKeys("wrongPass");
        Thread.sleep(1000);
        loginButton.click();

        WebElement errorMessage = driver.findElement(By.tagName("p"));
        Thread.sleep(1000);
        assertEquals("Invalid credentials!", errorMessage.getText());
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
