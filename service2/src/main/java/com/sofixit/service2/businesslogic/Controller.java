package com.sofixit.service2.businesslogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

@RestController
public class Controller {
    
    @Autowired
    private CSVConverter csvConverter;

    @GetMapping("/csv/{size}")
    public String returnCsv(@PathVariable Integer size){
        JsonNode jsonNode = csvConverter.retrieveJson(size);
    
        return  csvConverter.convertJsonToCsv(jsonNode);
    }



}
