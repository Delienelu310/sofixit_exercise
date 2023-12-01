package com.sofixit.service1.model;

import lombok.Data;

@Data
public class DataGroup {
    private String _type = "Position";
    private Long _id;
    private Long key = null;
    private String name;
    private String fullname;
    private String country;
    private String type;
    private Long iata_airport_code;
    private GeoPosition geoPosition;
    private Boolean inEurope;
    private Long location_id;
    private String countryCode;
    private Boolean coreCountry;
    private Long distance;
}
