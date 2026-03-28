package com.shirkhan.stepDefinitions.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.shirkhan.base.Hooks;
import com.shirkhan.pages.FormPage;
import com.shirkhan.utils.WaitHelper;
import io.cucumber.java.en.*;
import org.testng.Assert;

public class FormSteps {

    WebDriver driver = Hooks.driver;
    FormPage formPage;
    WaitHelper waitHelper = new WaitHelper(driver, 5);

    @Given("I open DemoQA practice form page")
    public void open_form_page() {
        driver.get(com.shirkhan.utils.ConfigReader.getProperty("baseUrl"));
        formPage = new FormPage(driver);
        // Wait for page to load
        waitHelper.waitForElementVisible(By.id("firstName"));
    }



    @When("I enter first name {string}")
    public void enter_first_name(String name) {

        formPage.enterFirstName(name);

    }

    @When("I enter last name {string}")
    public void enter_last_name(String lastname) {

        formPage.enterLastName(lastname);

    }

    @When("I enter email {string}")
    public void enter_email(String email) {

        formPage.enterEmail(email);

    }

    @When("I select gender Male")
    public void select_gender() {

        formPage.selectGender();

    }

    @When("I enter mobile number {string}")
    public void enter_mobile(String number) {

        formPage.enterMobile(number);

    }

    @When("I submit the form")
    public void submit_form() {

        formPage.submitForm();

    }

    @Then("form should be submitted successfully")
    public void verify_submission() {
        // Wait for success message to appear
        waitHelper.waitForElementVisible(By.id("example-modal-sizes-title-lg"));
        String actualMessage = formPage.getSuccessMessage();
        Assert.assertEquals(actualMessage, "Thanks for submitting the form");
    }
}