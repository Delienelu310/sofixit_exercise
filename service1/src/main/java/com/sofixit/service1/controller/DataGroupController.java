package com.sofixit.service1.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sofixit.service1.model.DataGroup;

import jakarta.validation.constraints.Min;


@RestController
public class DataGroupController {


    @Autowired
    private DataRandomizer dataRandomizer;

    @GetMapping("/generate/json/{size}")
    public List<DataGroup> generateData(@PathVariable("size") @Min(0) Integer size){

        List<DataGroup> result = new ArrayList<>();

        for(int i = 0; i < size; i++){
            result.add(dataRandomizer.getRandomDataGroup());
        }

        return result;

    }
}
