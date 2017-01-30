package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by arvind on 30/12/16.
 */
@Component
public class PhoneNumberValidator {

    @Autowired
    RestTemplate restTemplate;

    @Value("${service.landline.validation.url}")
    String landlineServiceUrl;

    @Value("${service.mobile.validation.url}")
    String mobileServiceUrl;

    final ExecutorService executorService = Executors.newFixedThreadPool(10);

    public String validateNumber(String isoCode, String number) throws ExecutionException, InterruptedException {

        UriComponentsBuilder landlineUrilBuilder = UriComponentsBuilder.fromUriString(landlineServiceUrl)
                // Add query parameter
                .queryParam("phoneNumber", number)
                .queryParam("isoCountryCode", isoCode);

        UriComponentsBuilder mobileUriBuilder = UriComponentsBuilder.fromUriString(mobileServiceUrl)
                // Add query parameter
                .queryParam("phoneNumber", number)
                .queryParam("isoCountryCode", isoCode);


        final Future<String> res1 = executorService.submit(new ValidatorTask(landlineUrilBuilder, restTemplate));
        final Future<String> res2 = executorService.submit(new ValidatorTask(mobileUriBuilder, restTemplate));
        String landlineValidation = res1.get();
        String mobileValidation = res2.get();

        return landlineValidation.concat(mobileValidation);
    }

}
