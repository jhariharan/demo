package com.example;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutionException;

/**
 * Created by arvind on 30/12/16.
 */
public class PhoneNumberValidatorTest {

    private static PhoneNumberValidator phoneNumberValidator;

    @BeforeClass
    public static void setup(){
        phoneNumberValidator = new PhoneNumberValidator();
        ReflectionTestUtils.setField(phoneNumberValidator, "landlineServiceUrl", "http://localhost:8080/phone-number-validation-api-server/v1/Landlinenumber/landline-number/validate");
        ReflectionTestUtils.setField(phoneNumberValidator, "mobileServiceUrl", "http://localhost:8080/phone-number-validation-api-server/v1/Mobilenumber/mobile-number/validate");
        ReflectionTestUtils.setField(phoneNumberValidator, "restTemplate", new RestTemplate());
    }

    @Test
    public void validatePhoneNumber() throws ExecutionException, InterruptedException {
        String expected = "{\"phoneNumber\":\"+6444716390\",\"isoCountryCode\":\"NZ\",\"valid\":true,\"geoLocation\":\"Wellington\"}{\"phoneNumber\":\"+6444716390\",\"isoCountryCode\":\"NZ\",\"valid\":false}";

        String actual = phoneNumberValidator.validateNumber("NZ", "44716390");
        Assert.assertNotNull(actual);
        Assert.assertTrue(expected.equals(actual));



    }
}
