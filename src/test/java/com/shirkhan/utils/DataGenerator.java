package com.shirkhan.utils;

import com.github.javafaker.Faker;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class DataGenerator {

    private static Faker faker = new Faker();
    private static Random random = new Random();

    // Predefined test cases (replaces Google Sheets data)
    private static final List<TestCaseData> predefinedTestCases = new ArrayList<>();
    
    static {
        // Saucedemo test cases
        predefinedTestCases.add(new TestCaseData("TC001", "standard_user", "secret_sauce", "John", "Doe"));
        predefinedTestCases.add(new TestCaseData("TC002", "locked_out_user", "secret_sauce", "Jane", "Smith"));
        predefinedTestCases.add(new TestCaseData("TC003", "problem_user", "secret_sauce", "Michael", "Johnson"));
        predefinedTestCases.add(new TestCaseData("TC004", "performance_glitch_user", "secret_sauce", "Sarah", "Williams"));
        predefinedTestCases.add(new TestCaseData("TC005", "error_user", "secret_sauce", "David", "Brown"));
        predefinedTestCases.add(new TestCaseData("TC006", "visual_user", "secret_sauce", "Emily", "Davis"));
    }

    // Random data generation methods
    public static String randomFirstName() {
        return faker.name().firstName();
    }

    public static String randomLastName() {
        return faker.name().lastName();
    }

    public static String randomEmail() {
        return faker.internet().emailAddress();
    }

    public static String randomPhoneNumber() {
        return faker.phoneNumber().cellPhone();
    }

    public static String randomCity() {
        return faker.address().city();
    }

    public static String randomAddress() {
        return faker.address().fullAddress();
    }

    public static String randomUsername() {return faker.name().username();}

    public static String randomPassword() {return faker.internet().password(8, 16, true, true, true);}

    public static String randomZipCode() {return faker.address().zipCode();}

    public static String randomCountry() {return faker.address().country();}

    public static String randomCompanyName() {return faker.company().name();}

    public static String randomJobTitle() {return faker.job().title();}

    public static String randomDateOfBirth() {return faker.date().birthday().toString();}

    public static String randomSSN() {return faker.idNumber().ssnValid();}

    public static String randomCreditCardNumber() {return faker.finance().creditCard();}

    public static String randomUUID() {return faker.internet().uuid();}

    public static int randomNumber(int min, int max) {return faker.number().numberBetween(min, max);}

    public static String randomLorem(int words) {return faker.lorem().words(words).toString().replace("[", "").replace("]", "").replace(",", "");}

    public static String randomWebsite() {return faker.internet().url();}

    public static String randomIPAddress() {return faker.internet().ipV4Address();}
    
    // Predefined test case methods (replaces Google Sheets)
    
    /**
     * Get all predefined test cases
     */
    public static List<TestCaseData> getTestCases() {
        return new ArrayList<>(predefinedTestCases);
    }
    
    /**
     * Get test case by ID
     */
    public static TestCaseData getTestCaseById(String testCaseId) {
        return predefinedTestCases.stream()
                .filter(tc -> tc.getTestCaseId().equals(testCaseId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Test case '" + testCaseId + "' not found"));
    }
    
    /**
     * Get random test case
     */
    public static TestCaseData getRandomTestCase() {
        return predefinedTestCases.get(random.nextInt(predefinedTestCases.size()));
    }
    
    /**
     * Get all available test case IDs
     */
    public static List<String> getAvailableTestCaseIds() {
        return predefinedTestCases.stream()
                .map(TestCaseData::getTestCaseId)
                .collect(Collectors.toList());
    }
    
    /**
     * Get test case by row number (1-based, excluding header)
     */
    public static TestCaseData getTestCaseByRow(int rowNumber) {
        if (rowNumber <= 0 || rowNumber > predefinedTestCases.size()) {
            throw new RuntimeException("Invalid row number: " + rowNumber + ". Valid range: 1-" + predefinedTestCases.size());
        }
        return predefinedTestCases.get(rowNumber - 1);
    }
    
    /**
     * Data class for test case information
     */
    public static class TestCaseData {
        private final String testCaseId;
        private final String username;
        private final String password;
        private final String firstName;
        private final String lastName;
        
        public TestCaseData(String testCaseId, String username, String password, String firstName, String lastName) {
            this.testCaseId = testCaseId;
            this.username = username;
            this.password = password;
            this.firstName = firstName;
            this.lastName = lastName;
        }
        
        // Getters
        public String getTestCaseId() { return testCaseId; }
        public String getUsername() { return username; }
        public String getPassword() { return password; }
        public String getFirstName() { return firstName; }
        public String getLastName() { return lastName; }
        
        @Override
        public String toString() {
            return String.format("TestCaseData{id=%s, username=%s, firstName=%s, lastName=%s}", 
                    testCaseId, username, firstName, lastName);
        }
    }
}