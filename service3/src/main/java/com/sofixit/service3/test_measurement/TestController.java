package com.sofixit.service3.test_measurement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.sofixit.service3.controller.MeasurementController;
import com.sofixit.service3.model.Report;


@RestController
public class TestController {

    RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private MeasurementController measurementController;

    @GetMapping("/test/report/{time}")
    public List<Report> getTestReport(@PathVariable int time){

        CallingThread callingThread = new CallingThread();
        callingThread.start();

        return measurementController.getReport(time);
    }
}
