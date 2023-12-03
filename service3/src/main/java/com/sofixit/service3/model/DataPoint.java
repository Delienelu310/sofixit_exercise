package com.sofixit.service3.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class DataPoint {
    double cpuUsage;
    long memoryUsed;
    LocalDateTime dateTime;
    public DataPoint() {
    }
}
