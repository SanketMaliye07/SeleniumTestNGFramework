package Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class AssertionsUtils {

    private WebDriver driver;

    public AssertionsUtils(WebDriver driver) {
        this.driver = driver;
    }

    public void assertElementPresent(By by) {
        try {
            Assert.assertTrue(isElementPresent(by), "Element not present: " + by.toString());
        } catch (Exception e) {
            handleAssertionException(e);
        }
    }

    public void assertElementNotPresent(By by) {
        try {
            Assert.assertFalse(isElementPresent(by), "Element present but should not be: " + by.toString());
        } catch (Exception e) {
            handleAssertionException(e);
        }
    }

    public void assertTextEquals(By by, String expectedText) {
        try {
            WebElement element = driver.findElement(by);
            String actualText = element.getText().trim();
            Assert.assertEquals(actualText, expectedText, "Text mismatch for element " + by.toString());
        } catch (Exception e) {
            handleAssertionException(e);
        }
    }

    public void assertTitleEquals(String expectedTitle) {
        try {
            String actualTitle = driver.getTitle().trim();
            Assert.assertEquals(actualTitle, expectedTitle, "Title mismatch");
        } catch (Exception e) {
            handleAssertionException(e);
        }
    }

    public void assertUrlContains(String expectedUrlPart) {
        try {
            String actualUrl = driver.getCurrentUrl();
            Assert.assertTrue(actualUrl.contains(expectedUrlPart), "URL does not contain the expected part: " + expectedUrlPart);
        } catch (Exception e) {
            handleAssertionException(e);
        }
    }

    public void assertElementDisplayed(By by) {
        try {
            WebElement element = driver.findElement(by);
            Assert.assertTrue(element.isDisplayed(), "Element is not displayed: " + by.toString());
        } catch (Exception e) {
            handleAssertionException(e);
        }
    }

    public void assertElementSelected(By by) {
        try {
            WebElement element = driver.findElement(by);
            Assert.assertTrue(element.isSelected(), "Element is not selected: " + by.toString());
        } catch (Exception e) {
            handleAssertionException(e);
        }
    }

    public void assertElementEnabled(By by) {
        try {
            WebElement element = driver.findElement(by);
            Assert.assertTrue(element.isEnabled(), "Element is not enabled: " + by.toString());
        } catch (Exception e) {
            handleAssertionException(e);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    private void handleAssertionException(Exception e) { 
        System.err.println("Assertion Exception: " + e.getMessage());
    }
}
