package com.isi.demo.exception;

public class DepartementDeletionException extends RuntimeException{
    public DepartementDeletionException(){}
    public DepartementDeletionException(String message){
        super(message);
    }
}
