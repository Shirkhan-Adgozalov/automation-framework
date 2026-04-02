package com.shirkhan.pages;

import com.shirkhan.base.BasePage;
import com.shirkhan.utils.DataGenerator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SaucedemoPage extends BasePage {
    
    // Locators
    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By productsTitle = By.className("title");
    private final By errorMessage = By.cssSelector("h3[data-test='error']");
    private final By burgerMenu = By.id("react-burger-menu-btn");
    private final By logoutButton = By.id("logout_sidebar_link");
    
    public SaucedemoPage(WebDriver driver) {
        super(driver);
    }
    
    public void enterUsername(String username) {
        sendKeys(usernameField, username);
    }
    
    public void enterPassword(String password) {
        sendKeys(passwordField, password);
    }
    
    public void clickLogin() {
        clickElement(loginButton);
    }
    
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }
    
    public void loginWithTestCase(DataGenerator.TestCaseData testCase) {
        System.out.println("=== DEBUG: Starting login with test case: " + testCase.getTestCaseId() + " ===");
        System.out.println("Username: " + testCase.getUsername());
        System.out.println("Password: " + testCase.getPassword());
        
        login(testCase.getUsername(), testCase.getPassword());
        
        System.out.println("=== DEBUG: Login completed ===");
    }
    
    public boolean isProductsPageDisplayed() {
        try {
            return driver.findElement(productsTitle).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isErrorMessageDisplayed() {
        try {
            return driver.findElement(errorMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public String getErrorMessage() {
        return getElementText(errorMessage);
    }
    
    public String getProductsTitle() {
        return getElementText(productsTitle);
    }
    
    public void logout() {
        try {
            System.out.println("Attempting to logout...");
            
            // Click burger menu
            clickElement(burgerMenu);
            System.out.println("Burger menu clicked");
            
            // Wait for menu to open and logout button to be visible
            Thread.sleep(1000);
            
            // Try multiple selectors for logout button
            try {
                clickElement(logoutButton);
                System.out.println("Logout clicked with primary selector");
            } catch (Exception e1) {
                // Try alternative selector
                By logoutButtonAlt = By.xpath("//a[contains(text(),'Logout')]");
                try {
                    clickElement(logoutButtonAlt);
                    System.out.println("Logout clicked with alternative selector");
                } catch (Exception e2) {
                    // Try JavaScript click
                    ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", 
                        driver.findElement(logoutButton));
                    System.out.println("Logout clicked with JavaScript");
                }
            }
            
            // Wait for logout to complete
            Thread.sleep(1000);
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    public boolean isLoginPageDisplayed() {
        try {
            return driver.findElement(loginButton).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
