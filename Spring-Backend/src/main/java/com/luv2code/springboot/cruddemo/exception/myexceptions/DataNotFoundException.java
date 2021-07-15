package com.luv2code.springboot.cruddemo.exception.myexceptions;
public class DataNotFoundException extends RuntimeException{
    public DataNotFoundException(String s){
    super(s);
    }
    @Override
    public String getMessage() {
        return super.getMessage();
    }
}