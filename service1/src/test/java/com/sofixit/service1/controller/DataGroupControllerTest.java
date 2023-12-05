package com.sofixit.service1.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DataGroupControllerTest {

    @Autowired
    private DataGroupController dataGroupController;
    
    @Test
    public void testResponseSize(){
        assertEquals( dataGroupController.generateData(5).size(), 5);
    }
}
