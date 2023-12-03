package com.sofixit.service2.measurements;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CallTimeRange{
    LocalDateTime start;
    LocalDateTime end;
    public CallTimeRange() {
    }
    CallTimeRange(LocalDateTime start, LocalDateTime end){
        this.start = start;
        this.end = end;
    }
}
