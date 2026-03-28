package com.shirkhan.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.shirkhan.base.BasePage;

public class FormPage extends BasePage {

    public FormPage(WebDriver driver) {
        super(driver);
    }

    // Locators

    private By firstNameField = By.id("firstName");

    private By lastNameField = By.id("lastName");

    private By emailField = By.id("userEmail");

    private By genderMaleRadio = By.xpath("//label[text()='Male']");

    private By mobileField = By.id("userNumber");

    private By submitButton = By.id("submit");

    private By successMessage = By.id("example-modal-sizes-title-lg");


    // Actions

    public void enterFirstName(String name) {
        sendKeys(firstNameField, name);
    }


    public void enterLastName(String lastname) {
        sendKeys(lastNameField, lastname);
    }


    public void enterEmail(String email) {
        sendKeys(emailField, email);
    }


    public void selectGender() {
        clickElement(genderMaleRadio);
    }


    public void enterMobile(String number) {
        sendKeys(mobileField, number);
    }


    public void submitForm() {
        scrollToElement(submitButton);
        clickElement(submitButton);
    }


    public String getSuccessMessage() {
        return getText(successMessage);
    }

}