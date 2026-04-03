package com.shirkhan.base;

import com.shirkhan.utils.ConfigReader;
import com.shirkhan.utils.ScreenshotHelper;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeOptions;
import java.time.Duration;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Hooks {

    public static WebDriver driver;

    static {
        // Setup drivers once at class loading
        String browser = System.getProperty("browser", "chrome");
        switch (browser.toLowerCase()) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                break;
            case "safari":
                // Safari doesn't need WebDriverManager setup on macOS
                // SafariDriver works with system's Safari automatically
                break;
            default:
                WebDriverManager.chromedriver().setup();
                break;
        }
    }

    @Before(order = 0)
    public void setup() {
        // Reload properties to get latest changes
        ConfigReader.reloadProperties();
        
        String browser = ConfigReader.getBrowser(); // Use environment-aware browser selection
        String headlessStr = ConfigReader.getProperty("headless", "false");

        // Default to chrome if browser is null
        if (browser == null || browser.isEmpty()) {
            browser = "chrome";
        }

        boolean headless = Boolean.parseBoolean(headlessStr);
        
        // Debug output
        System.out.println("=== DEBUG INFO ===");
        System.out.println("Browser: " + browser);
        System.out.println("Headless property value: " + headlessStr);
        System.out.println("Headless boolean: " + headless);
        System.out.println("==================");

        switch (browser.toLowerCase()) {
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (headless) {
                    firefoxOptions.addArguments("--headless");
                    System.out.println("Running in HEADLESS mode");
                } else {
                    System.out.println("Running in NORMAL mode");
                }
                driver = new FirefoxDriver(firefoxOptions);
                break;
            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                if (headless) {
                    edgeOptions.addArguments("--headless");
                    edgeOptions.addArguments("--disable-gpu");
                    edgeOptions.addArguments("--no-sandbox");
                    edgeOptions.addArguments("--disable-dev-shm-usage");
                    edgeOptions.addArguments("--window-size=1920,1080");
                    System.out.println("Running in HEADLESS mode");
                } else {
                    System.out.println("Running in NORMAL mode");
                }
                driver = new EdgeDriver(edgeOptions);
                break;
            case "safari":
                // Safari on macOS - Headless not fully supported
                if (headless) {
                    System.out.println("WARNING: Safari doesn't fully support headless mode. Running in normal mode.");
                }
                System.out.println("Running Safari on macOS");
                driver = new SafariDriver();
                break;
            default:
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--disable-extensions");
                options.addArguments("--disable-plugins");
                options.addArguments("--disable-images");
                
                // Add headless mode if enabled
                if (headless) {
                    options.addArguments("--headless");
                    options.addArguments("--disable-gpu");
                    options.addArguments("--no-sandbox");
                    options.addArguments("--disable-dev-shm-usage");
                    options.addArguments("--window-size=1920,1080");
                    System.out.println("Running in HEADLESS mode");
                } else {
                    System.out.println("Running in NORMAL mode");
                }
                
                driver = new ChromeDriver(options);
                break;
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }


    public static WebDriver getDriver() {
        return driver;
    }

    @After
    public void tearDown(Scenario scenario) {

        if (driver != null) {
            if (scenario.isFailed()) {
                String fileName = scenario.getName()
                        .replaceAll("[^a-zA-Z0-9]", "_");

                // Save screenshot to screenshots folder
                try {
                    ScreenshotHelper.takeScreenshot(driver, fileName);
                } catch (Exception e) {
                    System.out.println("Failed to take screenshot: " + e.getMessage());
                }

                // Attach screenshot to cucumber report
                try {
                    byte[] screenshot =
                            ((TakesScreenshot) driver)
                                    .getScreenshotAs(OutputType.BYTES);

                    scenario.attach(
                            screenshot,
                            "image/png",
                            fileName
                    );
                } catch (Exception e) {
                    System.out.println("Failed to attach screenshot to report: " + e.getMessage());
                }
            }

            // Quit driver with proper resource cleanup
            try {
                driver.quit();
                driver = null;
            } catch (Exception e) {
                System.out.println("Error during driver quit: " + e.getMessage());
                // Force null assignment to prevent reuse of dead driver
                driver = null;
            }
        }
    }
}