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
            default:
                WebDriverManager.chromedriver().setup();
                break;
        }
    }

    @Before(order = 0)
    public void setup() {
        // Reload properties to get latest changes
        ConfigReader.reloadProperties();
        
        String browser = ConfigReader.getProperty("browser");
        String headlessStr = ConfigReader.getProperty("headless", "false");
        boolean headless = Boolean.parseBoolean(headlessStr);
        
        // Debug output
        System.out.println("=== DEBUG INFO ===");
        System.out.println("Browser: " + browser);
        System.out.println("Headless property value: " + headlessStr);
        System.out.println("Headless boolean: " + headless);
        System.out.println("==================");

        switch (browser.toLowerCase()) {
            case "firefox":
                if (headless) {
                    // Firefox headless setup
                    // Note: Firefox headless requires additional configuration
                    driver = new FirefoxDriver();
                } else {
                    driver = new FirefoxDriver();
                }
                break;
            case "edge":
                if (headless) {
                    // Edge headless setup
                    // Note: Edge headless requires additional configuration
                    driver = new EdgeDriver();
                } else {
                    driver = new EdgeDriver();
                }
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

        if (scenario.isFailed()) {

            String fileName = scenario.getName()
                    .replaceAll("[^a-zA-Z0-9]", "_");

            // Save screenshot to screenshots folder
            ScreenshotHelper.takeScreenshot(driver, fileName);

            // Attach screenshot to cucumber report
            byte[] screenshot =
                    ((TakesScreenshot) driver)
                            .getScreenshotAs(OutputType.BYTES);

            scenario.attach(
                    screenshot,
                    "image/png",
                    fileName
            );
        }

        if (driver != null) {
            driver.quit();
        }
    }
}