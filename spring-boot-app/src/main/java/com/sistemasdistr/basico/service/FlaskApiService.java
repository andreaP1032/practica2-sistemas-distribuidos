package com.sistemasdistr.basico.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FlaskApiService {

   // private final String BASE_URL = "http://localhost:5000/api/test/";
    private final String BASE_URL = "http://host.docker.internal:5000/api/test/";

    public String callApi(String endpoint) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(BASE_URL + endpoint, String.class);
    }
}