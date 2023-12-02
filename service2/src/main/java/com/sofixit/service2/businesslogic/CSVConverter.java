package com.sofixit.service2.businesslogic;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Comparator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.sofixit.service2.Service2Application;

import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
public class CSVConverter {

    @Autowired
    private Calculator calculator;

    Logger logger = LoggerFactory.getLogger(Service2Application.class);
    
    private RestTemplate restTemplate = new RestTemplate();

    public JsonNode retrieveJson(Integer size){
        String url = "http://localhost:8080/generate/json/" + size; 

        HttpHeaders headers = new HttpHeaders();

        ResponseEntity<JsonNode> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), JsonNode.class);

        return response.getBody();
    }

    public String convertJsonToCsv(JsonNode jsonNode){
        return convertJsonToCsv(jsonNode, "_type, _id, name, type, latitude, longitude", false);
    }

    private void tableLog(List<List<String>> table){
        for(List<String> row : table){
            StringBuilder str = new StringBuilder();
            for(String cell : row){
                str.append(cell + " ");
            }
        }
    }

    public String convertJsonToCsv(JsonNode jsonNode, String format, Boolean math ){
        
        List<List<String>> table = prepareTable(jsonNode);

        tableLog(table);

        List<String> headers = table.get(0);
        List<String> sortedHeaders = new ArrayList<>();
        sortedHeaders.addAll(headers);
        sortedHeaders.sort(Comparator.comparing(String::length).reversed());

        StringBuilder result = new StringBuilder();
        result.append(format + "\r\n");
        for(int i = 1; i < table.size(); i++){
            List<String> row = table.get(i);

            StringBuilder newRow = new StringBuilder();
            newRow.append(format);

            
            for(String header : sortedHeaders){
                int index = newRow.indexOf(header);
                
                while(index != -1){
                    newRow.replace(index, index + header.length(), row.get( headers.indexOf(header) ));
                    index = newRow.indexOf(header);
                }
            }
            
            if(!math) result.append(newRow.toString() + "\r\n");
            else{
                StringBuilder stringBuilder = new StringBuilder();
                String[] expressions = newRow.toString().split(",");
                for(int j = 0; j < expressions.length; j++){
                    try{
                        stringBuilder.append(calculator.execute(expressions[j]));
                        
                    }catch(Exception e){
                        // logger.info(e.getMessage());
                        stringBuilder.append(expressions[j]);
                    }
                    if(j != expressions.length - 1) stringBuilder.append(",");
                }
                stringBuilder.append("\r\n");

                result.append(stringBuilder.toString());
            }
        }

        return result.toString();
        
    }

    private List<List<String>> prepareTable(JsonNode jsonNode){
        List<List<String>> rows = new ArrayList<>();

        //first row consists of  headers and the will be used to prepare csv in required format
        Iterator<JsonNode> iterator = jsonNode.elements();
        
        //the array that we get mustn`t be empty
        if(!iterator.hasNext()) throw new RuntimeException();

        JsonNode currentNode = iterator.next();

        rows.add(writeHeaders(currentNode));
        rows.add(writeRecord(currentNode));

        while(iterator.hasNext()){
            rows.add(writeRecord(iterator.next()));
        }

        return rows;
    }

    private List<String> writeHeaders(JsonNode exampleNode){

        ArrayList<String> headers = new ArrayList<>();

        prepareHeaders(exampleNode, headers);

        return headers;

    }

    private void prepareHeaders(JsonNode exampleNode, ArrayList<String> headers){
        Iterator<Entry<String, JsonNode>> fields = exampleNode.fields();

        while(fields.hasNext()){
            Entry<String, JsonNode> entry = fields.next();

            if(entry.getValue().isContainerNode()){
                prepareHeaders(entry.getValue(), headers);
            }else{
                headers.add(entry.getKey());
            }
        }

    }

    private List<String> writeRecord(JsonNode node){
        ArrayList<String> record = new ArrayList<>();

        prepareRecord(node, record);

        return record;
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
