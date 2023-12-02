package com.sofixit.service1.model;

import lombok.Data;

@Data
public class DataGroup {

    private String _type = "Position";
    private String type;
    
    private Long _id;
    private Long key = null;

    private String name;
    private String fullname;

    private String country;
    private String countryCode;
    private Boolean coreCountry;
    private Boolean inEurope;
    
    private GeoPosition geoPosition;
    
    private Long iata_airport_code;
    private Long location_id;
    private Long distance;
}
