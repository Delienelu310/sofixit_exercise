package com.sofixit.service2.businesslogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

@RestController
public class Controller {
    
    @Autowired
    private CSVConverter csvConverter;

    //  /csv/n?format="field1, field2 + field3"
    // or   .csv.n
    @GetMapping("/v1/csv/{size}")
    public ResponseEntity<String> returnCsvVersion1(@PathVariable("size") Integer size){
        JsonNode jsonNode = csvConverter.retrieveJson(size);
    
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        return new ResponseEntity<String>(csvConverter.convertJsonToCsv(jsonNode), headers, HttpStatus.OK);
    }

    @GetMapping("/v2/csv/{size}")
    public ResponseEntity<String> returnCsvVersion2(@PathVariable("size") Integer size, @RequestParam(name="format") String format){
        JsonNode jsonNode = csvConverter.retrieveJson(size);
    
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        return new ResponseEntity<String>(csvConverter.convertJsonToCsv(jsonNode, format, false), headers, HttpStatus.OK);
    }

    @GetMapping("/v3/csv/{size}")
    public ResponseEntity<String> returnCsvVersion3(@PathVariable("size") Integer size, @RequestParam(name="format") String format){
        JsonNode jsonNode = csvConverter.retrieveJson(size);
    
        //to include calculations
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        return new ResponseEntity<String>(csvConverter.convertJsonToCsv(jsonNode, format, true), headers, HttpStatus.OK);
    }
}
