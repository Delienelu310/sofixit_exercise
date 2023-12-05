package com.sofixit.service2.exceptions;

public class OperatorExcpectedException extends InvalidFormatException{
    
    public OperatorExcpectedException(int position){
        super("Operator expected on position "+ position + " inside of brackets");
    }
}
