package com.sofixit.service2.businesslogic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CalculatorTest {

    Logger logger = LoggerFactory.getLogger(getClass());

    private Calculator calculator = new Calculator();

    @Test
    void testSimpleOperations(){
        assertEquals(calculator.execute("1 + 1"), 2);
        assertEquals(calculator.execute("-1 + 1"), 0);
        assertEquals(calculator.execute("1 *5"), 5);
    }


    @Test
    void testMultWithAdd(){
        assertEquals(calculator.execute("2 + 2 * 2"), 6);
        assertEquals(calculator.execute("30 + 2 * (-2)"), 26);
        assertEquals(calculator.execute("-2*(+2)*2+60"), 52);
        assertEquals(calculator.execute("-2.25/ 0.25+ 78 - (-2) * 6.5"), 82);
    }

    @Test
    void testAdditionWithBrackets(){
        assertEquals(calculator.execute("(2 + 3)"), 5);
        assertEquals(calculator.execute("-(2 + 3)"), -5);
        assertEquals(calculator.execute("(2 + 3 + (3 - 2))"), 6);
    }

    @Test
    void testMultWithBrackets(){
        assertEquals((double)Math.round(calculator.execute("(25/2.5 *5 + 42.2 * (-(-(-1))))") * 10) / 10, 7.8);
    }

}
