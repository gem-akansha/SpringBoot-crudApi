package com.example.demo.Exception;

public class IdNotFoundException extends RuntimeException{
    private String message;

    public IdNotFoundException(String msg){
        super(msg);
        this.message = msg;
    }
    public IdNotFoundException(){

    }
}
