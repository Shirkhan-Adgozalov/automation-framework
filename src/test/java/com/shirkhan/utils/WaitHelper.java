package com.shirkhan.utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitHelper {

    WebDriver driver;
    WebDriverWait wait;

    public WaitHelper(WebDriver driver, int timeoutSeconds) {

        this.driver = driver;

        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));

    }

    public void waitForElementVisible(By locator) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

    }

    public void waitForElementClickable(By locator) {

        wait.until(ExpectedConditions.elementToBeClickable(locator));

    }

}