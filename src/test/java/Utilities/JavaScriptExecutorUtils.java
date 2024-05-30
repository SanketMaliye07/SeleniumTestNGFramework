package Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptExecutorUtils {

    private WebDriver driver;

    public JavaScriptExecutorUtils(WebDriver driver) {
        this.driver = driver;
    }

    // Method to execute JavaScript code
    public Object executeJavaScript(String script, Object... args) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return jsExecutor.executeScript(script, args);
    }

    // Method to scroll to the top of the page
    public void scrollToTop() {
        executeJavaScript("window.scrollTo(0, 0);");
    }

    // Method to scroll to the bottom of the page
    public void scrollToBottom() {
        executeJavaScript("window.scrollTo(0, document.body.scrollHeight);");
    }

 // Method to highlight an element using JavaScript
    public void highlightElement(String elementId) {
        By byId = By.id(elementId); 
        WebElement element = driver.findElement(byId); 
        executeJavaScript("arguments[0].style.border='3px solid red'", element);
    }


    // Method to get the document title using JavaScript
    public String getDocumentTitle() {
        return (String) executeJavaScript("return document.title;");
    }

    // Method to click an element using JavaScript
    public void clickElement(By by) {
        executeJavaScript("arguments[0].click();", driver.findElement(by));
    }

    // Method to set the value of an input field using JavaScript
    public void setInputValue(By by, String value) {
        executeJavaScript("arguments[0].value='" + value + "';", driver.findElement(by));
    }

    // Method to get the page's current scroll position
    public Long getScrollPosition() {
        return (Long) executeJavaScript("return window.scrollY;");
    }

    // Method to scroll to a specific element using JavaScript
    public void scrollToElement(By by) {
        executeJavaScript("arguments[0].scrollIntoView(true);", driver.findElement(by));
    }

}
