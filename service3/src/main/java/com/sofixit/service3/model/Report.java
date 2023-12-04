package com.sofixit.service3.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Report {
    public Report() {
    }
    List<DataPoint> dataGraph = new ArrayList<>();
    List<CallData> calls = new ArrayList<>();
    LocalDateTime start;
    LocalDateTime end;
}
