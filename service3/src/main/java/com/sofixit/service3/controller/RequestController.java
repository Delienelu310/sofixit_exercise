package com.sofixit.service3.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RequestController {
    
    RestTemplate restTemplate = new RestTemplate();

    final String[] urls = new String[]{
        "http://localhost:5000/v1/csv/",
        "http://localhost:5000/v2/csv/",
        "http://localhost:5000/v3/csv/"
    };

    @GetMapping("/request/{type}/{size}")
    public ResponseEntity<String> makeRequest(@PathVariable int type, @PathVariable int size, @RequestParam String format ){
        StringBuilder url = new StringBuilder(urls[type]);
        url.append(size);
        url.append("?format=" + format);

        HttpHeaders headers = new HttpHeaders();
    
        return restTemplate.exchange(url.toString(), HttpMethod.GET, new HttpEntity<>(headers), String.class);
    }

}
