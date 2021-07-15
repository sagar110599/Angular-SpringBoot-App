package com.luv2code.springboot.cruddemo.exception.myexceptions;
public class RegisterException extends RuntimeException{
    public RegisterException(String s){
    super(s);
    }
    @Override
    public String getMessage() {
        return super.getMessage();
    }
}