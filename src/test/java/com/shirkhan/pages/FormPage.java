package com.shirkhan.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.shirkhan.base.BasePage;
import com.shirkhan.utils.GoogleSheetsHelper;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.stream.Collectors;

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

    // Google Sheets Data Methods
    
    /**
     * Fill form with data from Google Sheets
     * @param rowNumber - Row number to read from (starting from 2, as row 1 is header)
     */
    public void fillFormFromGoogleSheets(int rowNumber) throws IOException, GeneralSecurityException {
        // Read data from Google Sheets (skip header, start from row 2)
        List<List<String>> formData = GoogleSheetsHelper.readSheetData("Sheet1!A" + rowNumber + ":H" + rowNumber);
        
        if (!formData.isEmpty()) {
            List<String> row = formData.get(0);
            
            // Fill form fields with data from Google Sheets
            // Assuming column A: TestCase, B: FirstName, C: LastName, D: Email, E: Mobile, F: City, G: ExpectedResult, H: Status
            enterFirstName(row.size() > 1 ? row.get(1) : "");  // Column B: FirstName
            enterLastName(row.size() > 2 ? row.get(2) : "");   // Column C: LastName
            enterEmail(row.size() > 3 ? row.get(3) : "");      // Column D: Email
            enterMobile(row.size() > 4 ? row.get(4) : "");     // Column E: Mobile
            selectGender(); // Always select male for demo
        }
    }
    
    /**
     * Fill form with specific test case data from Google Sheets
     * @param testCaseId - Test case ID to search for (e.g., "TC001")
     */
    public void fillFormWithTestCase(String testCaseId) throws IOException, GeneralSecurityException {
        // Read all data from Google Sheets
        List<List<String>> allData = GoogleSheetsHelper.readSheetData("Sheet1!A:H");
        
        // Find the row with matching test case ID
        for (int i = 1; i < allData.size(); i++) { // Skip header row
            List<String> row = allData.get(i);
            if (!row.isEmpty() && row.get(0).equals(testCaseId)) {
                // Fill form with this test case data
                enterFirstName(row.size() > 1 ? row.get(1) : "");  // Column B: FirstName
                enterLastName(row.size() > 2 ? row.get(2) : "");   // Column C: LastName
                enterEmail(row.size() > 3 ? row.get(3) : "");      // Column D: Email
                enterMobile(row.size() > 4 ? row.get(4) : "");     // Column E: Mobile
                selectGender();
                break; // Stop after finding the matching test case
            }
        }
    }
    
    /**
     * Get all available test case IDs from Google Sheets
     */
    public List<String> getAvailableTestCases() throws IOException, GeneralSecurityException {
        List<List<String>> allData = GoogleSheetsHelper.readSheetData("Sheet1!A:A");
        return allData.stream()
                .filter(row -> !row.isEmpty() && !row.get(0).equals("Test Case"))
                .map(row -> row.get(0))
                .collect(Collectors.toList());
    }
    
    /**
     * Fill form with random test case data from Google Sheets
     */
    public void fillFormWithRandomTestCase() throws IOException, GeneralSecurityException {
        List<String> testCases = getAvailableTestCases();
        if (!testCases.isEmpty()) {
            // Pick a random test case
            String randomTestCase = testCases.get((int) (Math.random() * testCases.size()));
            fillFormWithTestCase(randomTestCase);
        }
    }

}