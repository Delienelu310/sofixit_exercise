package com.sofixit.service2.exceptions;

public class InvalidFuncArgumentsException extends InvalidFunctionUseException{

    public InvalidFuncArgumentsException(int given, int expected ){
        super("Invalid number of arguments: " + given + " given, " + expected + "expected");
    }

}
