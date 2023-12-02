package com.sofixit.service1.controller;

import java.util.Random;

import org.springframework.stereotype.Component;

import com.sofixit.service1.model.DataGroup;

@Component
public class DataRandomizer {

    private Random random = new Random();

    public DataGroup getRandomDataGroup(){
        DataGroup dataGroup = new DataGroup();

        dataGroup.set_id(random.nextLong(1000, 100000));
        dataGroup.setIata_airport_code(null);
    

        return dataGroup;
    }
    
}
