package com.sofixit.service2.businesslogic;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.ArrayList;

public class Calculator {


    private final Character[] technicalChars = new Character[]{'-', '*', '+', '/', '%', ' ', ')', '(', '.'};

    
    /**
     * 
     * @param expressison      string expression that contains numbers and operations (log, ln, sin, cos, tg, ctg, etc )
     * @return      the result of expression provided as String
     */
    public Double execute( String expressison){
        //takes only numbers, technical chars and function names

        //1. remove function call by  recursively executing expression inside and replacing them with values
        //2. remove the () recursively executing expressions inside and replacing them with values (take into account the ( before a sign))
        //3. make ** operation
        //4. make multiplication, division, % operation
        //5. make addition and substraction
        //use example 
        // executeFunc(expressison, (args) -> Math.sin(args[0]) , expressison, null);

        return null;
        
    }

    private boolean isTechnicalChar(Character ch){
        for(int i = 0; i < technicalChars.length; i++){
            if(technicalChars[i] == ch) return true;
        }
        return false;
    }

    private String executeFunc(String expression, Function<Double[], Double> function, String funcname, Integer argsNum){
        StringBuilder newExpression = new StringBuilder(expression);

        int index = newExpression.indexOf(funcname);
        while(index != -1){
            
            // firstly check whether it is actually a sin, not a "klsin", "sin_" - this should be considered as nonsense, 
            // unless it is another function
            Character 
                before = index > 0 ? newExpression.charAt(index - 1) : null,
                after = index + funcname.length() >= newExpression.length() ? null : newExpression.charAt(index + funcname.length());
            
            if(
                before != null && !isTechnicalChar(before) ||
                after != null && !isTechnicalChar(after)
            ) continue;

            //if it is the case, we seek for "(", then take the whole expression till ")" that closes the first one
            //end perform this expression using execute

            int opening = index + funcname.length();
            
            for(; opening < newExpression.length(); opening++){
                if(newExpression.charAt(opening) == '(') break;
                else if(newExpression.charAt(opening) != ' ') throw new RuntimeException();
            }
            if(opening == newExpression.length()) throw new RuntimeException("Invalid query");            
            

            Double[] arguments = new Double[argsNum];
            
            int count = 0;
            int currentIndex;
            int args = 0;
            for(currentIndex = opening + 1; currentIndex < newExpression.length(); currentIndex++){
                
                Character currentCharacter = newExpression.charAt(currentIndex);
                if(currentCharacter == '(') count++;
                else if(currentCharacter == ')'){
                    if(count == 0) break;
                    count--;
                }else if(currentCharacter == ','){
                    arguments[args] = execute(newExpression.substring( opening, currentIndex));
                    args++;
                }
            }
            
            if(args != argsNum) throw new RuntimeException();
            if(currentIndex == newExpression.length()) throw new RuntimeException();

            Double value = function.apply(arguments);
            
            // we replace the whole function() and its content with the numerical value
            newExpression.replace(index, currentIndex + 1, value.toString());

            // finally, lets move onto the next step
            index = newExpression.indexOf(funcname, index + 1);
            
        }

        return newExpression.toString();
        
    }
}
