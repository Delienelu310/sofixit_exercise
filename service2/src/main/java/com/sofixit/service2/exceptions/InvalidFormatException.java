package com.sofixit.service2.exceptions;

public class InvalidFormatException extends RuntimeException{
    InvalidFormatException(String message){
        super(message);
    }
}
