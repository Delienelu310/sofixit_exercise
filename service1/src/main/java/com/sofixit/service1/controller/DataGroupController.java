package com.sofixit.service1.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sofixit.service1.Service1Application;
import com.sofixit.service1.model.DataGroup;

import io.micrometer.core.annotation.Timed;

@RestController
public class DataGroupController {

    private Logger logger = LoggerFactory.getLogger(Service1Application.class);

    @Autowired
    private DataRandomizer dataRandomizer;

    @Timed("jsongenerate.time")
    @GetMapping("/generate/json/{size}")
    public List<DataGroup> generateData(@PathVariable("size") Integer size){

        List<DataGroup> result = new ArrayList<>();

        for(int i = 0; i < size; i++){
            result.add(dataRandomizer.getRandomDataGroup());
        }

        return result;

    }
}
