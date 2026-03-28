package com.shirkhan.utils;

import com.github.javafaker.Faker;

public class DataGenerator {

    private static Faker faker = new Faker();

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
}