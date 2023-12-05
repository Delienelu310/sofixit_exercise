package com.sofixit.service1.measurements;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sofixit.service1.controller.DataGroupController;

@SpringBootTest
public class MeasurementAspectTest {

    @Autowired
    private MeasurementAspect measurementAspect;

    @Autowired
    private DataGroupController dataGroupController;

    @Test
    public void testAspectWork(){
        dataGroupController.generateData(10);
        dataGroupController.generateData(9);
        dataGroupController.generateData(8);
        
        assertEquals(measurementAspect.getCallTimeStamps().size(), 3);
    }

    @Test
    public void testCallsListSize(){
        for(int i = 0; i < 15; i++){
            dataGroupController.generateData(10);
        }

        assertEquals(measurementAspect.getCallTimeStamps().size(), 10);
    }
}
