package com.example;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.concurrent.Callable;

/**
 * Created by arvind on 30/12/16.
 */
public class ValidatorTask implements Callable<String> {

    private final UriComponentsBuilder uriComponentsBuilder;
    private final RestTemplate restTemplate;

    public ValidatorTask(UriComponentsBuilder uriComponentsBuilder, RestTemplate restTemplate) {
        this.uriComponentsBuilder = uriComponentsBuilder;
        this.restTemplate = restTemplate;
    }

    @Override
    public String call() throws Exception {
        return  restTemplate.getForObject(uriComponentsBuilder.toUriString(), String.class);
    }
}
