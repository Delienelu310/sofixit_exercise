package com.sofixit.service1.measurements;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CallTimeRange{
    LocalDateTime start;
    LocalDateTime end;
    CallTimeRange(LocalDateTime start, LocalDateTime end){
        this.start = start;
        this.end = end;
    }
    CallTimeRange(){
        
    }
}
