package com.sofixit.service3.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import com.sofixit.service3.Service3Application;


public class CallingThread extends Thread{

    Logger logger = LoggerFactory.getLogger(Service3Application.class);

    final String 
        url1 = "http://localhost:5000/v3/csv/",
        format = "?format=latitude*longitude,sqrt(location_id)";

    RestTemplate restTemplate = new RestTemplate();

    public void run(){
        HttpHeaders headers = new HttpHeaders();
        restTemplate.exchange(url1 + 1000 + format, HttpMethod.GET, new HttpEntity<>(headers), String.class);
        restTemplate.exchange(url1 + 10000 + format, HttpMethod.GET, new HttpEntity<>(headers), String.class);
        restTemplate.exchange(url1 + 100000 + format, HttpMethod.GET, new HttpEntity<>(headers), String.class);

    }
    
}
