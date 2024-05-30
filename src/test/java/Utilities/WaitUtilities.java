package Utilities;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Configuration.AppManager;

public class WaitUtilities extends AppManager{
    private WebDriver driver;

    public WaitUtilities(WebDriver driver) {
        super();
        this.driver = driver;
    }

    // Wait for an element to be present in the DOM
    public WebElement waitForElementPresent(By locator, Duration timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
            return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (TimeoutException e) {
            throw new RuntimeException("Timeout waiting for element with locator: " + locator, e);
        }
    }

    // Wait for an element to be clickable
    public WebElement waitForElementClickable(By locator, Duration timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
            return wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (TimeoutException e) {
            throw new RuntimeException("Timeout waiting for element to be clickable with locator: " + locator, e);
        }
    }

    // Wait for an element to be visible
    public WebElement waitForElementVisible(By locator, Duration timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            throw new RuntimeException("Timeout waiting for element to be visible with locator: " + locator, e);
        }
    }

    // Wait for the text of an element to be present
    public boolean waitForTextToBePresent(By locator, String text, Duration timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
            return wait.until(ExpectedConditions.textToBe(locator, text));
        } catch (TimeoutException e) {
            throw new RuntimeException("Timeout waiting for text to be present in element with locator: " + locator, e);
        }
    }

    // Wait for an element to be invisible
    public boolean waitForElementInvisible(By locator, Duration timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
            return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            throw new RuntimeException("Timeout waiting for element to be invisible with locator: " + locator, e);
        }
    }

    // Wait for an element's attribute to contain a specific value
    public boolean waitForAttributeContains(By locator, String attribute, String value, Duration timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
            return wait.until(ExpectedConditions.attributeContains(locator, attribute, value));
        } catch (TimeoutException e) {
            throw new RuntimeException("Timeout waiting for attribute to contain value in element with locator: " + locator, e);
        }
    }

 // Wait for an element's attribute to be present
    public boolean waitForAttributeToBePresent(By locator, String attribute, Duration timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            return wait.until(ExpectedConditions.attributeToBe(locator, attribute, attribute));
        } catch (TimeoutException e) {
            throw new RuntimeException("Timeout waiting for attribute to be present in element with locator: " + locator, e);
        }
    }
    
    public void sleepInSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
