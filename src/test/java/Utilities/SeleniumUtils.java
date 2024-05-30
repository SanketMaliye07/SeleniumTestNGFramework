package Utilities;
 
import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class SeleniumUtils {

	private WebDriver driver;

	public SeleniumUtils(WebDriver driver) {
		this.driver = driver;
	}

	// Method to click on an element
	public void clickElement(By by) {
		try {
			WebElement element = driver.findElement(by);
			element.click();
		} catch (NoSuchElementException e) {
			System.out.println("Element not found: " + by);
			throw e;
		}
	}

	// Method to enter text into an input field
	public void enterText(By by, String text) {
		try {
			WebElement element = driver.findElement(by);
			element.sendKeys(text);
		} catch (NoSuchElementException e) {
			System.out.println("Element not found: " + by);
			throw e;
		}
	}

	// Method to get text from an element
	public String getElementText(By by) {
		try {
			WebElement element = driver.findElement(by);
			return element.getText();
		} catch (NoSuchElementException e) {
			System.out.println("Element not found: " + by);
			throw e;
		}
	}

	// Method to select an option from a dropdown
	public void selectOptionFromDropdown(By dropdownLocator, String optionText) {
		try {
			Select dropdown = new Select(driver.findElement(dropdownLocator));
			dropdown.selectByVisibleText(optionText);
		} catch (NoSuchElementException e) {
			handleException("Element not found: " + dropdownLocator, e);
		}
	}

	// Method to switch to a frame
	public void switchToFrame(By frameLocator) {
		try {
			WebElement frame = driver.findElement(frameLocator);
			driver.switchTo().frame(frame);
		} catch (NoSuchElementException e) {
			handleException("Element not found: " + frameLocator, e);
		}
	}

	// Method to switch to the default content
	public void switchToDefaultContent() {
		try {
			driver.switchTo().defaultContent();
		} catch (Exception e) {
			handleException("Failed to switch to default content", e);
		}
	}

	// Method to clear text from an input field
	public void clearText(By by) {
		try {
			WebElement element = driver.findElement(by);
			element.clear();
		} catch (NoSuchElementException e) {
			handleException("Element not found: " + by, e);
		}
	}

	// Method to get the value of a specific attribute of an element
	public String getAttributeValue(By by, String attributeName) {
		try {
			WebElement element = driver.findElement(by);
			return element.getAttribute(attributeName);
		} catch (NoSuchElementException e) {
			handleException("Element not found: " + by, e);
			return null; // or throw e; depending on your use case
		}
	}

	// Method to scroll to a specific element
	public void scrollToElement(By by) {
		try {
			WebElement element = driver.findElement(by);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		} catch (NoSuchElementException e) {
			handleException("Element not found: " + by, e);
		}
	}

	// Method to capture a screenshot
	public void takeScreenshot(String fileName) {
		try {
			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot, new File(fileName));
		} catch (IOException | WebDriverException e) {
			handleException("Failed to capture screenshot: " + e.getMessage(), e);
		}
	}

	// Method to switch to a window by its title
	public void switchToWindowByTitle(String windowTitle) {
		try {
			Set<String> windowHandles = driver.getWindowHandles();
			for (String handle : windowHandles) {
				driver.switchTo().window(handle);
				if (driver.getTitle().equals(windowTitle)) {
					break;
				}
			}
		} catch (Exception e) {
			handleException("Failed to switch to window by title: " + windowTitle, e);
		}
	}

	// Method to perform drag and drop
	public void dragAndDrop(By source, By target) {
		try {
			Actions actions = new Actions(driver);
			WebElement sourceElement = driver.findElement(source);
			WebElement targetElement = driver.findElement(target);
			actions.dragAndDrop(sourceElement, targetElement).perform();
		} catch (NoSuchElementException e) {
			handleException("Element not found for drag and drop", e);
		}
	}

	// Method to upload a file
	public void uploadFile(By by, String filePath) {
		try {
			WebElement element = driver.findElement(by);
			element.sendKeys(filePath);
		} catch (NoSuchElementException e) {
			handleException("Element not found: " + by, e);
		}
	}

	// Private method to handle exceptions
	private void handleException(String message, Exception e) {
		System.out.println("Exception: " + message);
		e.printStackTrace();
	}

	// Perform a specific action on a specific element
	public void performActionOnElement(By locator, String action, String... keys) {
		WebElement element = driver.findElement(locator);
		Actions actions = new Actions(driver);

		switch (action.toLowerCase()) {
		case "sendkeys":
			if (keys.length > 0) {
				actions.sendKeys(element, keys[0]).perform();
			} else {
				System.out.println("No keys provided for sendkeys action.");
			}
			break;
		case "click":
			actions.click(element).perform();
			break;
		case "moveto":
			actions.moveToElement(element).perform();
			break;
			
		default:
			System.out.println("Unsupported action: " + action);
			break;
		}
	}

	 

}
