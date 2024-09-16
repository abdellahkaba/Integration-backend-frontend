package com.isi.demo.exception;

public class OperationDepartementNotPermittedException extends RuntimeException{

    public OperationDepartementNotPermittedException(){

    }
    public OperationDepartementNotPermittedException(String message){
        super(message);
    }
}
