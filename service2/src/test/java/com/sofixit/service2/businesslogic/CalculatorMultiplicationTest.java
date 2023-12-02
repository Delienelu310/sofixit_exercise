package com.sofixit.service2.businesslogic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CalculatorMultiplicationTest {
    
    private Logger logger = LoggerFactory.getLogger(getClass());

    private Calculator calculator = new Calculator();
    
    @Test
    void testSimpleMultiplication(){
        assertEquals(Double.parseDouble(calculator.executeMultiplication("4 * 5")), 20);
        assertEquals(Double.parseDouble(calculator.executeMultiplication("2 * 3")), 6);
        assertEquals(Double.parseDouble(calculator.executeMultiplication("20 * 17")), 340);
    }

    @Test 
    void testMultWithDouble(){
        assertEquals(Double.parseDouble(calculator.executeMultiplication("1.5 * 1.5")), 2.25);
    }

    @Test
    void testSpecialMultiplication(){
        assertEquals(Double.parseDouble(calculator.executeMultiplication("-1.5 * (-1.5)")), 2.25);
        assertEquals(Double.parseDouble(calculator.executeMultiplication("+1.5 * (+1.5)")), 2.25);
        assertEquals(Double.parseDouble(calculator.executeMultiplication("+1.5 * (-1.5)")), -2.25);
    }

    @Test 
    void testSpaces(){
        assertEquals(Double.parseDouble(calculator.executeMultiplication("1.5*1.5")), 2.25);
        assertEquals(Double.parseDouble(calculator.executeMultiplication("1.5* 1.5")), 2.25);
        assertEquals(Double.parseDouble(calculator.executeMultiplication("1.5 *1.5")), 2.25);
        assertEquals(Double.parseDouble(calculator.executeMultiplication("1.5 *(-1.5)")), -2.25);
    }

    @Test 
    void testDivision(){
        assertEquals(Double.parseDouble(calculator.executeMultiplication("30.5 /(-2)")), -15.25);
    }

    @Test 
    void testComplexExpressions(){
        assertEquals(calculator.executeMultiplication("1.5*1.5 + (65) - 431 + 15 / 3"), "2.25 + (65) - 431 +5.0");
    }

}
