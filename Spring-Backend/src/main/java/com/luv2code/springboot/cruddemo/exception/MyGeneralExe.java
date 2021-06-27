package com.luv2code.springboot.cruddemo.exception;
public class MyGeneralExe extends RuntimeException {

    public MyGeneralExe() {

        super();
    }
    @Override
    public String getMessage() {
        return super.getMessage();
    }
}