package com.shirkhan.base;

import com.shirkhan.utils.LoggerHelper;
import com.shirkhan.utils.ScreenshotHelper;
import com.shirkhan.utils.WaitHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BasePage {
    
    protected WebDriver driver;
    protected WaitHelper waitHelper;
    
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.waitHelper = new WaitHelper(driver, 10); // Default 10 seconds
    }
    
    // Basic element interactions with logging
    protected void clickElement(By locator) {
        LoggerHelper.info("Clicking element: " + locator.toString());
        waitHelper.waitForElementClickable(locator);
        driver.findElement(locator).click();
    }
    
    protected void sendKeys(By locator, String text) {
        LoggerHelper.info("Entering text '" + text + "' in element: " + locator.toString());
        waitHelper.waitForElementVisible(locator);
        WebElement element = driver.findElement(locator);
        element.clear();
        element.sendKeys(text);
    }
    
    protected String getElementText(By locator) {
        LoggerHelper.info("Getting text from element: " + locator.toString());
        waitHelper.waitForElementVisible(locator);
        return driver.findElement(locator).getText();
    }
    
    protected String getText(By locator) {
        LoggerHelper.info("Getting text from element: " + locator.toString());
        waitHelper.waitForElementVisible(locator);
        return driver.findElement(locator).getText();
    }
    
    protected boolean isElementDisplayed(By locator) {
        try {
            waitHelper.waitForElementVisible(locator);
            LoggerHelper.info("Element is displayed: " + locator.toString());
            return true;
        } catch (Exception e) {
            LoggerHelper.info("Element is not displayed: " + locator.toString());
            return false;
        }
    }
    
    protected boolean isElementEnabled(By locator) {
        boolean enabled = driver.findElement(locator).isEnabled();
        LoggerHelper.info("Element enabled status for " + locator.toString() + ": " + enabled);
        return enabled;
    }
    
    // Navigation methods
    protected void navigateTo(String url) {
        LoggerHelper.info("Navigating to: " + url);
        driver.get(url);
    }
    
    protected String getCurrentUrl() {
        String url = driver.getCurrentUrl();
        LoggerHelper.info("Current URL: " + url);
        return url;
    }
    
    protected String getTitle() {
        String title = driver.getTitle();
        LoggerHelper.info("Page title: " + title);
        return title;
    }
    
    // Wait methods with custom timeout
    protected void waitForElementVisible(By locator, int timeoutSeconds) {
        LoggerHelper.info("Waiting for element visibility: " + locator.toString() + " (timeout: " + timeoutSeconds + "s)");
        WaitHelper customWait = new WaitHelper(driver, timeoutSeconds);
        customWait.waitForElementVisible(locator);
    }
    
    protected void waitForElementClickable(By locator, int timeoutSeconds) {
        LoggerHelper.info("Waiting for element clickable: " + locator.toString() + " (timeout: " + timeoutSeconds + "s)");
        WaitHelper customWait = new WaitHelper(driver, timeoutSeconds);
        customWait.waitForElementClickable(locator);
    }
    
    // Screenshot method
    protected void takeScreenshot(String fileName) {
        LoggerHelper.info("Taking screenshot: " + fileName);
        ScreenshotHelper.takeScreenshot(driver, fileName);
    }
    
    // Page load wait
    protected void waitForPageLoad() {
        LoggerHelper.info("Waiting for page to load completely");
        new org.openqa.selenium.support.ui.WebDriverWait(driver, Duration.ofSeconds(30))
            .until(webDriver -> "complete".equals(
                ((org.openqa.selenium.JavascriptExecutor) webDriver)
                    .executeScript("return document.readyState")
            ));
    }
    
    // Scroll methods
    protected void scrollToElement(By locator) {
        LoggerHelper.info("Scrolling to element: " + locator.toString());
        WebElement element = driver.findElement(locator);
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
    
    protected void scrollToTop() {
        LoggerHelper.info("Scrolling to top of page");
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
    }
    
    protected void scrollToBottom() {
        LoggerHelper.info("Scrolling to bottom of page");
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }
    
    // Form Actions
    protected void selectDropdownByVisibleText(By locator, String text) {
        LoggerHelper.info("Selecting dropdown option by visible text: " + text);
        waitHelper.waitForElementVisible(locator);
        WebElement element = driver.findElement(locator);
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }
    
    protected void selectDropdownByValue(By locator, String value) {
        LoggerHelper.info("Selecting dropdown option by value: " + value);
        waitHelper.waitForElementVisible(locator);
        WebElement element = driver.findElement(locator);
        Select select = new Select(element);
        select.selectByValue(value);
    }
    
    protected void selectDropdownByIndex(By locator, int index) {
        LoggerHelper.info("Selecting dropdown option by index: " + index);
        waitHelper.waitForElementVisible(locator);
        WebElement element = driver.findElement(locator);
        Select select = new Select(element);
        select.selectByIndex(index);
    }
    
    protected void uploadFile(By locator, String filePath) {
        LoggerHelper.info("Uploading file: " + filePath);
        waitHelper.waitForElementVisible(locator);
        WebElement element = driver.findElement(locator);
        element.sendKeys(filePath);
    }
    
    // Checkbox & Radio Actions
    protected void checkCheckbox(By locator) {
        LoggerHelper.info("Checking checkbox: " + locator.toString());
        waitHelper.waitForElementClickable(locator);
        WebElement element = driver.findElement(locator);
        if (!element.isSelected()) {
            element.click();
        }
    }
    
    protected void uncheckCheckbox(By locator) {
        LoggerHelper.info("Unchecking checkbox: " + locator.toString());
        waitHelper.waitForElementClickable(locator);
        WebElement element = driver.findElement(locator);
        if (element.isSelected()) {
            element.click();
        }
    }
    
    protected boolean isCheckboxSelected(By locator) {
        boolean selected = driver.findElement(locator).isSelected();
        LoggerHelper.info("Checkbox selected status for " + locator.toString() + ": " + selected);
        return selected;
    }
    
    // Mouse & Keyboard Actions
    protected void hoverOverElement(By locator) {
        LoggerHelper.info("Hovering over element: " + locator.toString());
        waitHelper.waitForElementVisible(locator);
        WebElement element = driver.findElement(locator);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }
    
    protected void doubleClick(By locator) {
        LoggerHelper.info("Double clicking element: " + locator.toString());
        waitHelper.waitForElementClickable(locator);
        WebElement element = driver.findElement(locator);
        Actions actions = new Actions(driver);
        actions.doubleClick(element).perform();
    }
    
    protected void rightClick(By locator) {
        LoggerHelper.info("Right clicking element: " + locator.toString());
        waitHelper.waitForElementClickable(locator);
        WebElement element = driver.findElement(locator);
        Actions actions = new Actions(driver);
        actions.contextClick(element).perform();
    }
    
    protected void dragAndDrop(By sourceLocator, By targetLocator) {
        LoggerHelper.info("Dragging from " + sourceLocator.toString() + " to " + targetLocator.toString());
        waitHelper.waitForElementVisible(sourceLocator);
        waitHelper.waitForElementVisible(targetLocator);
        WebElement source = driver.findElement(sourceLocator);
        WebElement target = driver.findElement(targetLocator);
        Actions actions = new Actions(driver);
        actions.dragAndDrop(source, target).perform();
    }
    
    protected void pressKey(By locator, Keys key) {
        LoggerHelper.info("Pressing key: " + key.toString());
        waitHelper.waitForElementVisible(locator);
        WebElement element = driver.findElement(locator);
        element.sendKeys(key);
    }
    
    // Window & Tab Actions
    protected void switchToWindow(int windowIndex) {
        Set<String> windowHandles = driver.getWindowHandles();
        List<String> windows = List.copyOf(windowHandles);
        if (windowIndex < windows.size()) {
            LoggerHelper.info("Switching to window index: " + windowIndex);
            driver.switchTo().window(windows.get(windowIndex));
        } else {
            throw new IndexOutOfBoundsException("Window index " + windowIndex + " is out of bounds");
        }
    }
    
    protected void switchToTab(String tabTitle) {
        Set<String> windowHandles = driver.getWindowHandles();
        for (String windowHandle : windowHandles) {
            driver.switchTo().window(windowHandle);
            if (driver.getTitle().equals(tabTitle)) {
                LoggerHelper.info("Switched to tab with title: " + tabTitle);
                return;
            }
        }
        throw new RuntimeException("No tab found with title: " + tabTitle);
    }
    
    protected void closeCurrentWindow() {
        LoggerHelper.info("Closing current window");
        driver.close();
    }
    
    protected Set<String> getAllWindowHandles() {
        Set<String> windowHandles = driver.getWindowHandles();
        LoggerHelper.info("Total windows: " + windowHandles.size());
        return windowHandles;
    }
    
    // Alert Actions
    protected void acceptAlert() {
        LoggerHelper.info("Accepting alert");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }
    
    protected void dismissAlert() {
        LoggerHelper.info("Dismissing alert");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().dismiss();
    }
    
    protected String getAlertText() {
        LoggerHelper.info("Getting alert text");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());
        return driver.switchTo().alert().getText();
    }
    
    protected void sendKeysToAlert(String text) {
        LoggerHelper.info("Sending keys to alert: " + text);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().sendKeys(text);
    }
    
    // Advanced Wait Actions
    protected void waitForElementToDisappear(By locator) {
        LoggerHelper.info("Waiting for element to disappear: " + locator.toString());
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
    
    protected void waitForTextToBePresent(By locator, String text) {
        LoggerHelper.info("Waiting for text to be present: " + text);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }
    
    protected void waitForAttributeToContain(By locator, String attribute, String value) {
        LoggerHelper.info("Waiting for attribute to contain: " + attribute + " = " + value);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.attributeContains(locator, attribute, value));
    }
    
    // Validation Actions
    protected boolean isElementSelected(By locator) {
        boolean selected = driver.findElement(locator).isSelected();
        LoggerHelper.info("Element selected status for " + locator.toString() + ": " + selected);
        return selected;
    }
    
    protected String getAttribute(By locator, String attributeName) {
        String attributeValue = driver.findElement(locator).getAttribute(attributeName);
        LoggerHelper.info("Getting attribute " + attributeName + " from element: " + locator.toString() + " = " + attributeValue);
        return attributeValue;
    }
    
    protected String getCssValue(By locator, String propertyName) {
        String cssValue = driver.findElement(locator).getCssValue(propertyName);
        LoggerHelper.info("Getting CSS property " + propertyName + " from element: " + locator.toString() + " = " + cssValue);
        return cssValue;
    }
    
    protected int getElementCount(By locator) {
        int count = driver.findElements(locator).size();
        LoggerHelper.info("Element count for " + locator.toString() + ": " + count);
        return count;
    }
}
