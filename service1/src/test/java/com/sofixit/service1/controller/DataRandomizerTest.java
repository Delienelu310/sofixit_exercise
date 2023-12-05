package com.sofixit.service1.controller;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;
import java.util.HashSet;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sofixit.service1.controller.DataRandomizer.Country;
import com.sofixit.service1.model.DataGroup;

@SpringBootTest
public class DataRandomizerTest {
    
    @Autowired
    private DataRandomizer dataRandomizer;

    @Test
    public void testRandomness(){

        Set<Long> locationIds = new HashSet<>();
        Set<String> _types = new HashSet<>();
        Set<String> countries = new HashSet<>();

        for(int i = 0; i < 1000; i++){
            DataGroup dataGroup = dataRandomizer.getRandomDataGroup(); 


            locationIds.add(dataGroup.getLocation_id());
            _types.add(dataGroup.get_type());
            countries.add(dataGroup.getCountry());

        }

        if(locationIds.size() / 1000.0 < 0.9) assert false;
        if(_types.size() / 10.0 < 0.8) assert false;
        if( ((double)countries.size()) / DataRandomizer.countries.length < 0.9) assert false;
        
        assert true;
    }

    @Test
    public void testDataIntegrity(){

        for(int i = 0; i < 100; i++){
            DataGroup dataGroup = dataRandomizer.getRandomDataGroup();

            Country country = null;
            for(Country c : DataRandomizer.countries){
                if(dataGroup.getCountry().equals(c.getTitle())) country = c;
            }

            assertEquals(country.getCode(), dataGroup.getCountryCode());
            assertEquals(country.isInEurope(), dataGroup.getInEurope());
            assertEquals(country.equals(DataRandomizer.coreCountry), dataGroup.getCoreCountry());
        }
    }
}
