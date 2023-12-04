package com.sofixit.service3.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sofixit.service3.model.CallData;
import com.sofixit.service3.model.DataPoint;
import com.sofixit.service3.model.Report;

@RestController
public class MeasurementController {

    final String
            cpuUrl1 = "http://localhost:8080/actuator/metrics/process.cpu.usage",
            memoryUrl1 = "http://localhost:8080/actuator/metrics/jvm.memory.used",
            cpuUrl2 = "http://localhost:5000/actuator/metrics/process.cpu.usage",
            memoryUrl2 = "http://localhost:5000/actuator/metrics/jvm.memory.used",
            callUrl1 = "http://localhost:8080/calls/timestamps",
            callUrl2 = "http://localhost:8080/calls/timestamps";

    @Autowired
    private PerformanceMeasurer performanceMeasurer;

    @GetMapping("/report/{time}")
    public List<Report> getReport(@PathVariable int time){
        List<Report> result = new ArrayList<>();

        Report service1Report = new Report();
        Report service2Report = new Report(); 
        result.add(service1Report);
        result.add(service2Report);
        service1Report.setStart(LocalDateTime.now());
        service2Report.setStart(LocalDateTime.now());
        

        List<List<DataPoint>> servicesMeasurements = 
            performanceMeasurer.measure(List.of(cpuUrl1, cpuUrl2), List.of(memoryUrl1, memoryUrl2), time, 50);
        List<CallData> callsService1 = performanceMeasurer.getCallsTimestamps(callUrl1);
        List<CallData> callsService2 = performanceMeasurer.getCallsTimestamps(callUrl2);

        service1Report.setCalls(callsService1);
        service1Report.setDataGraph(servicesMeasurements.get(0));
        service1Report.setEnd(LocalDateTime.now());
        service2Report.setCalls(callsService2);
        service2Report.setDataGraph(servicesMeasurements.get(1));
        service2Report.setEnd(LocalDateTime.now());

        return result;
    }
}
