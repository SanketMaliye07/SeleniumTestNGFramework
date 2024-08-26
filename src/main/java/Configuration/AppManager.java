package Configuration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class AppManager {

    protected WebDriver driver;
    private Properties properties;

    public void setUp() {
        try {
            loadProperties();
            String browser = properties.getProperty("BROWSER", "chrome").toLowerCase();
            boolean headless = Boolean.parseBoolean(properties.getProperty("HEADLESS", "false"));
            initializeDriver(browser, headless); 
            configureDriver();
            navigateToBaseUrl();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to set up the WebDriver: " + e.getMessage());
        }
    }

    private void initializeDriver(String browser, boolean headless) {
        switch (browser) {
            case "chrome":
                setUpChrome(headless);
                break;
            case "firefox":
                setUpFirefox(headless);
                break;
            case "edge":
                setUpEdge(headless);
                break;
            default:
                throw new IllegalArgumentException("Invalid browser specified in config.properties");
        }
    }

    private void setUpChrome(boolean headless) {
        ChromeOptions options = new ChromeOptions();
//        options.addArguments(
//                "user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");
        options.addArguments("--remote-allow-origins=*");
        if (headless) {
            options.addArguments("--headless");
            options.addArguments("--disable-gpu");
            options.addArguments("--window-size=1920,1080");
//            options.addArguments("--disable-dev-shm-usage");
//            options.addArguments("--no-sandbox");
        }
        driver = new ChromeDriver(options);
    }


    private void setUpFirefox(boolean headless) {
        FirefoxOptions options = new FirefoxOptions();
        if (headless) {
            options.addArguments("-headless");
        }
        driver = new FirefoxDriver(options);
    }

    private void setUpEdge(boolean headless) {
        EdgeOptions options = new EdgeOptions();
        if (headless) {
            options.addArguments("headless");
        }
        driver = new EdgeDriver(options);
    }

    private void configureDriver() {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    private void navigateToBaseUrl() {
        driver.get(properties.getProperty("BASE.URL"));
    }

    private void loadProperties() {
        try (FileInputStream fis = new FileInputStream("src/main/resources/config.properties")) {
            properties = new Properties();
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load properties file: " + e.getMessage());
        }
    }

    public void tearDown() {       
        if (driver != null) {
            driver.quit();
        }
        System.out.println("Closing...");
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }
}
