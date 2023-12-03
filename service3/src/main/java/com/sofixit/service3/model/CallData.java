package com.sofixit.service3.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CallData {
    LocalDateTime start;
    LocalDateTime end;
    public CallData() {
    }
}
