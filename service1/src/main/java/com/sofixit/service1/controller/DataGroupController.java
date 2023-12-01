package com.sofixit.service1.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sofixit.service1.model.DataGroup;

@RestController
public class DataGroupController {
    
    @GetMapping("/generate/json/{size}")
    public List<DataGroup> generateData(@RequestParam Integer size){
        List<DataGroup> result = new ArrayList<>();

        for(int i = 0; i < size; i++){
            result.add(generateRandomDataGroup());
        }

        return result;
    }

    private DataGroup generateRandomDataGroup(){
        return new DataGroup();
    }
}
