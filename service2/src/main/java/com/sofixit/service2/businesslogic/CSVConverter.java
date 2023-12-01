package com.sofixit.service2.businesslogic;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.opencsv.CSVWriter;

import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
public class CSVConverter {
    
    private RestTemplate restTemplate = new RestTemplate();

    public JsonNode retrieveJson(Integer size){
        String url = "http://localhost:8080/generate/json/" + size; 

        HttpHeaders headers = new HttpHeaders();

        ResponseEntity<JsonNode> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), JsonNode.class);

        return response.getBody();
    }

    public String convertJsonToCsv(JsonNode jsonNode){
        return convertJsonToCsv(jsonNode, null);
    }

    public String convertJsonToCsv(JsonNode jsonNode, String format ){
        CSVWriter csvWriter = null;
        
        try{
            StringWriter stringWriter = new StringWriter();
            csvWriter = new CSVWriter(stringWriter);

            Iterator<JsonNode> elements = jsonNode.elements();
            if(! elements.hasNext()) {
                csvWriter.close();
                return "[]";
            }

            //inserting headers into csv string:
            JsonNode node = elements.next();
            writeHeaders(node, csvWriter);
            writeRecord(node, csvWriter);

            while(elements.hasNext()){
                node = elements.next();
                writeRecord(node, csvWriter);
            }

            csvWriter.close();
            return stringWriter.toString();
        }catch(IOException exception){

            //todo
            return null;
        }
        
    }

    private void writeHeaders(JsonNode exampleNode, CSVWriter csvWriter){

        ArrayList<String> headers = new ArrayList<>();

        prepareHeaders(exampleNode, headers);

        csvWriter.writeNext(headers.toArray(new String[headers.size()]));

    }

    private void prepareHeaders(JsonNode exampleNode, ArrayList<String> headers){
        Iterator<Entry<String, JsonNode>> fields = exampleNode.fields();

        while(fields.hasNext()){
            Entry<String, JsonNode> entry = fields.next();

            if(entry.getValue().isContainerNode()){
                prepareRecord(entry.getValue(), headers);
            }else{
                headers.add(entry.getKey());
            }
        }

    }

    private void writeRecord(JsonNode node, CSVWriter csvWriter){
        ArrayList<String> record = new ArrayList<>();

        prepareRecord(node, record);

        csvWriter.writeNext(record.toArray(new String[record.size()]));
    }

    private void prepareRecord(JsonNode node, ArrayList<String> record){
        Iterator<Entry<String, JsonNode>> fields = node.fields();

        while(fields.hasNext()){
            JsonNode value = fields.next().getValue();

            if(value.isContainerNode()){
                prepareRecord(value, record);
            }else{
                record.add(value.toString());
            }
        }
    }





}
