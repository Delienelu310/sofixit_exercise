package com.sofixit.service2.businesslogic;

import java.util.function.Function;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Calculator {

    private Logger logger = LoggerFactory.getLogger(getClass());

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
        //3. make multiplication, division, % operation
        //4. make addition and substraction
        //5. get rid of unnecessary brackets
        //use example 
        // executeFunc(expressison, (args) -> Math.sin(args[0]) , expressison, null);

        StringBuilder newExpression = new StringBuilder(expressison);

        //1
        //TODO: add new functions: log, sin, cos, ln, factorial and other 
        logger.info("beofre pow:" + newExpression.toString());
        newExpression = new StringBuilder(executeFunc(newExpression.toString(), (args) -> Math.pow(args[0], args[1]), "pow", 2));
        
        newExpression = new StringBuilder(executeFunc(newExpression.toString(), (args) -> Math.sqrt(args[0]), "sqrt", 1));;

        //2
        int begin = -1;
        int count = 0;
        for(int i = 0; i < newExpression.length(); i++){
            if(newExpression.charAt(i) == '('){
                if(count == 0) begin = i; 
                count++;
            }else if(newExpression.charAt(i) == ')'){
                count--;
                if(count < 0) throw new RuntimeException("Invalid input");
                if(count == 0){
                    Double replacerValue = execute(newExpression.substring(begin + 1, i));
                    if(begin == 1){
                        Character previous = newExpression.charAt(begin - 1);
                        if(previous == '+'){
                            begin--;
                        }else if(previous == '-'){
                            replacerValue *= -1;
                            begin--;
                        }
                    }
                    String replacer = replacerValue >= 0 ? replacerValue.toString() : "(-" + Math.abs(replacerValue) + ")";
                    newExpression.replace(begin, i+1, replacer);
                    i = begin + replacer.length();
                    begin = -1;
                }
            }
        }

        //3
        newExpression = new StringBuilder(executeMultiplication(newExpression.toString()));

        //4
        newExpression = new StringBuilder(executeAddition(newExpression.toString()));

        //5 eliminate ( and ) if they still remain
        for(int i = 0; i < newExpression.length(); i++){
            Character current = newExpression.charAt(i);
            if(current == '(' || current == ')') newExpression.delete(i, i+1);
        }

        return Double.valueOf(newExpression.toString());
        
    }

    public String executeMultiplication(String expression){
        StringBuilder newExpression = new StringBuilder(expression);

        Double a = null, b = null;
        String sa = "", sb = "";
        Character operation = null;

        int j = 0; 
        int count = 0;

        Integer currentStart = 0;

        while(j < newExpression.length()){
            if(currentStart == null) currentStart = j;
            Character current = newExpression.charAt(j);

            // logger.info(sa + ", " + operation + ", " + sb);

            if(sb.length() != 0 && (current != '.' && !Character.isDigit(current)) && count == 0) b = Double.valueOf(sb);
            else if(sa.length() != 0 && (current != '.' && !Character.isDigit(current)) && count == 0) a = Double.valueOf(sa);

            if(current == '('){
                count++;
            }else if(current == ')'){
                count--;
            }else if(current != ' '){
                if(a == null){
                    sa += current;
                }else if(operation == null){
                    operation = current;
                    if(operation != '*' && operation != '/'){
                        sa = "";
                        a = null;
                        operation = null;
                        currentStart = j + 1;
                        j++;
                        continue;
                    } 
                }else if(b == null){
                    sb += current; 
                }
            }
            if(count > 1 || count < 0) throw new RuntimeException();

            if(a != null && b != null && operation != null){
                if(operation == '*'){
                    a *= b;
                }else if(operation == '/'){
                    a /= b;
                }else{
                    throw new RuntimeException();
                }

                newExpression.replace(currentStart, j, a.toString());

                j = currentStart;
                currentStart = null;

                a = null;
                b = null;
                sa = "";
                sb = "";
                operation = null;

                continue;
            }
            
            j++;
        }

        if(sb.length() != 0){
            b = Double.valueOf(sb);
            if(operation == '*'){
                a *= b;
            }else if(operation == '/'){
                a /= b;
            }else{
                throw new RuntimeException();
            }
            newExpression.replace(currentStart, j, a.toString());
        }

        return newExpression.toString();
    }

    public String executeAddition(String expression){
        StringBuilder newExpression = new StringBuilder(expression);

        Double a = null, b = null;
        String sa = "", sb = "";
        Character operation = null;

        int j = 0; 
        int count = 0;

        while(j < newExpression.length()){

            Character current = newExpression.charAt(j);

            // logger.info(sa + ", " + operation + ", " + sb);

            if(sb.length() != 0 && (current == ' ' || current == '+' || current == '-') && count == 0) b = Double.valueOf(sb);
            else if(sa.length() != 0 && (current == ' ' || current == '+' || current == '-') && count == 0) a = Double.valueOf(sa);

            if(current == '('){
                count++;
            }else if(current == ')'){
                count--;
            }else if(current != ' '){
                if(a == null){
                    sa += current;
                }else if(operation == null){
                    operation = current;
                }else if(b == null){
                    sb += current; 
                }
            }
            if(count > 1 || count < 0) throw new RuntimeException();

            if(a != null && b != null && operation != null){
                if(operation == '+'){
                    a += b;
                }else if(operation == '-'){
                    a -= b;
                }else{
                    throw new RuntimeException();
                }

                newExpression.replace(0, j, a.toString());

                a = null;
                b = null;
                sa = "";
                sb = "";
                operation = null;
                j = 0;
                continue;
            }
            
            j++;
        }

        if(sb.length() != 0){
            b = Double.valueOf(sb);
            if(operation == '+'){
                a += b;
            }else if(operation == '-'){
                a -= b;
            }else{
                throw new RuntimeException();
            }
            return a.toString();
        }

        return newExpression.toString();
    }

    private boolean isTechnicalChar(Character ch){
        for(int i = 0; i < technicalChars.length; i++){
            if(technicalChars[i] == ch) return true;
        }
        return false;
    }

    public String executeFunc(String expression, Function<Double[], Double> function, String funcname, Integer argsNum){
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
            int lastCurrentIndex = opening + 1;
            int currentIndex;
            int args = 0;
            for(currentIndex = opening + 1; currentIndex < newExpression.length(); currentIndex++){
                
                Character currentCharacter = newExpression.charAt(currentIndex);
                if(currentCharacter == '(') count++;
                else if(currentCharacter == ')'){
                    
                    if(count == 0){
                        arguments[args] = execute(newExpression.substring( lastCurrentIndex, currentIndex));
                        lastCurrentIndex = currentIndex + 1;
                        args++;
                        break;
                    }
                    count--;
                    
                }else if(currentCharacter == ','){
                    if(count > 0) continue;
                    arguments[args] = execute(newExpression.substring( lastCurrentIndex, currentIndex));
                    args++;
                    lastCurrentIndex = currentIndex + 1;
                }
            }
            // logger.info(Arrays.deepToString(arguments));
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
