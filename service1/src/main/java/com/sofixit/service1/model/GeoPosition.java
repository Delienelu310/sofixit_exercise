package com.sofixit.service1.model;

import lombok.Data;

@Data
public class GeoPosition {
    
    private Double longitude;
    private Double latitude;

    public GeoPosition(Double longitude, Double latitude){
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
