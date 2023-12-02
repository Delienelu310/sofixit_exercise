package com.sofixit.service1.controller;

import java.util.Random;

import org.springframework.stereotype.Component;

import com.sofixit.service1.model.DataGroup;
import com.sofixit.service1.model.GeoPosition;

import lombok.Data;

@Component
public class DataRandomizer {

    @Data
    public static class Country{
        private String title;
        private String code;
        private boolean inEurope;

        Country(String title, String code, boolean inEurope){
            this.title = title;
            this.code = code;
            this.inEurope = inEurope;
        }
    }

    public static final Country coreCountry = new Country("Poland", "PL", true);

    public static final String fullnames[] = 
        new String[]{"Oksywka Nowak", "Jan Kowalski", "Hanna Wojcik", "Michal Kowalczyk", "Jakub Lewandowski", "Zosia Kaminska"};

    public static final Country countries[] = 
        new Country[]{
            coreCountry,
            new Country("Britain", "UK", true),
            new Country("Germany", "DE", true),
            new Country("USA", "US", false),
            new Country("China", "CH", false)
        }; 

    private Random random = new Random();

    public DataGroup getRandomDataGroup(){
        DataGroup dataGroup = new DataGroup();

        dataGroup.set_type("Position" + random.nextInt(0,16));
        dataGroup.setType("Location" + random.nextInt(0,5));

        dataGroup.set_id(random.nextLong( (int)Math.pow(10, 6), (int)Math.pow(10, 7)));
        dataGroup.setKey(random.nextLong( (int)Math.pow(10, 6), (int)Math.pow(10, 7)));
        dataGroup.setDistance(random.nextLong( 100, 7000));
        dataGroup.setIata_airport_code(random.nextLong(100, 1000));
        dataGroup.setGeoPosition(new GeoPosition(random.nextDouble() * 100, random.nextDouble() * 100));
        dataGroup.setLocation_id(random.nextLong( (int)Math.pow(10, 6), (int)Math.pow(10,7) ));

        String randomFullName = fullnames[random.nextInt(0, fullnames.length)];
        dataGroup.setName(randomFullName.split(" ")[0]);
        dataGroup.setFullname(randomFullName);

        Country randomCountry = countries[random.nextInt(0,countries.length)];
        dataGroup.setCountry(randomCountry.getTitle());
        dataGroup.setCountryCode(randomCountry.getCode());
        dataGroup.setInEurope(randomCountry.isInEurope());
        dataGroup.setCoreCountry(randomCountry == coreCountry);


        return dataGroup;
    }
    
}
