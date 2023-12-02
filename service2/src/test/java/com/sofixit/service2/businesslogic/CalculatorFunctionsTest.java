package com.sofixit.service2.businesslogic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CalculatorFunctionsTest {
    

    private Calculator calculator = new Calculator();

    @Test
    void powTest(){
        assertEquals(calculator.execute("pow(2,3)"), 8);
        assertEquals(calculator.execute("pow(2,-1)"),0.5);
        assertEquals(calculator.execute("pow ( 2, -1 )"),0.5);
        assertEquals(calculator.execute("pow   ( 2,-1)"),0.5);
        assertEquals(calculator.execute("pow(-2,-1)"),-0.5);
        assertEquals(calculator.execute("pow( (-2),-1)"),-0.5);
    }

    @Test 
    void complexPowTest(){
        assertEquals(calculator.execute("pow( pow(3,3), 2)"), 729);
        assertEquals(calculator.execute("pow( pow( 3 ,  3 ), 2)"), 729);
        assertEquals(calculator.execute("pow( pow   (3,3 ), 2)"), 729);
        assertEquals(calculator.execute("pow( pow   ( 3,3),  2    )"), 729);
        assertEquals(calculator.execute("pow( pow(3 ,3), 2)"), 729);
    }

    @Test
    void sqrtTest(){
        assertEquals(calculator.execute("sqrt(+4)"), 2);
        assertEquals(calculator.execute("  sqrt(2.25 )"),1.5);
        assertEquals(calculator.execute("sqrt (  1  )   "),1);
    }


}
