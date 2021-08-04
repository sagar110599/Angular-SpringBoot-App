package com.luv2code.springboot.cruddemo.exception.myexceptions;
public class BadRequestException extends RuntimeException{
    public BadRequestException(String s){
    super(s);
    }
    @Override
    public String getMessage() {
        return super.getMessage();
    }
}