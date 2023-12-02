package com.sofixit.service2.businesslogic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CalculatorAdditionTest {

    private Calculator calculator = new Calculator();

    @Test
    void testSimpleAddition(){
        assertEquals(Double.parseDouble(calculator.executeAddition("1 + 1")), 2 );
        assertEquals(Double.parseDouble(calculator.executeAddition("1 + 8")), 9 );
        assertEquals(Double.parseDouble(calculator.executeAddition("17 + 25")), 42 );

    }

    @Test 
    void testWithUnaryOperators(){
        assertEquals(Double.parseDouble(calculator.executeAddition("-1 + 4")), 3 );
        assertEquals(Double.parseDouble(calculator.executeAddition("+1 + 9")), 10 );
        assertEquals(Double.parseDouble(calculator.executeAddition("1 + (+190)")), 191 );
        assertEquals(Double.parseDouble(calculator.executeAddition("15 + (-10)")), 5 );
    }

    @Test
    void testWithDoubles(){
        assertEquals(Double.parseDouble(calculator.executeAddition("1.65 + 1")), 2.65 );
    }

    @Test
    void testSubstraction(){
        assertEquals(Double.parseDouble(calculator.executeAddition("88-43")),  45);
        assertEquals(((double)Math.round( Double.parseDouble(calculator.executeAddition("88.76-43")) * 100)) / 100,  45.76);
        assertEquals(Double.parseDouble(calculator.executeAddition("-88 - 43")),  -131);
        assertEquals(Double.parseDouble(calculator.executeAddition("(-88.4) - (+43)")),  -131.4);

    }

    @Test
    void testLongExpressions(){
        assertEquals(Double.parseDouble(calculator.executeAddition("88 - 43 - 25 ")),  20);
        assertEquals(Double.parseDouble(calculator.executeAddition("88.5 + (-43) + 0 - 25 + 34")), 54.5);
    }

    @Test
    void testInvalidBehavior(){
        //to be continued
    }
    
}
