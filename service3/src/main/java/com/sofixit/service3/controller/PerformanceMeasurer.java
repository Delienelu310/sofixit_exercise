package com.sofixit.service3.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.sofixit.service3.Service3Application;
import com.sofixit.service3.model.CallData;
import com.sofixit.service3.model.DataPoint;

@Component
public class PerformanceMeasurer {

    Logger logger = LoggerFactory.getLogger(Service3Application.class);
    
    public List<List<DataPoint>> measure(List<String> cpuUrl, List<String> memoryUrl, int time, int frequency){

        RestTemplate restTemplate = new RestTemplate();
        List<List<DataPoint>> result = new ArrayList<>();
        for(int i = 0; i < cpuUrl.size(); i++){
            result.add(new ArrayList<>());
        }

        for(int i = 0; i < time; i+= frequency){
            try{
                Thread.sleep(frequency);
            }catch(Throwable t){
                
            };

            for(int j = 0; j < result.size(); j++){
                HttpHeaders headersCpu = new HttpHeaders();
                ResponseEntity<JsonNode> response = restTemplate.exchange(cpuUrl.get(j), HttpMethod.GET, new HttpEntity<>(headersCpu), JsonNode.class);
            
                HttpHeaders headersMemory= new HttpHeaders();
                ResponseEntity<JsonNode> response2 = restTemplate.exchange(memoryUrl.get(j), HttpMethod.GET, new HttpEntity<>(headersMemory), JsonNode.class);
            
                DataPoint dataPoint = new DataPoint();

                dataPoint.setCpuUsage(response.getBody().get("measurements").iterator().next().get("value").asDouble());
                dataPoint.setMemoryUsed(response2.getBody().get("measurements").iterator().next().get("value").asLong());
                dataPoint.setDateTime(LocalDateTime.now());

                result.get(j).add(dataPoint);
            }
            
        }
        

        return result;
    }

    public List<CallData> getCallsTimestamps(String url){
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<JsonNode> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), JsonNode.class);
        // logger.info(response.getBody().toString());
        Iterator<JsonNode> iterator = response.getBody().iterator();

        List<CallData> result = new ArrayList<>();
        while(iterator.hasNext()){
            JsonNode current = iterator.next();
            
            try{
                CallData call = new CallData();
                call.setStart(LocalDateTime.parse(current.get("start").textValue()));
                call.setEnd(LocalDateTime.parse(current.get("end").textValue()));
                result.add(call);
            }catch(Exception e){
                logger.info(e.toString());
            }
            
        }

        return result;
        
    }
}
