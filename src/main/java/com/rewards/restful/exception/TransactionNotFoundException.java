package com.rewards.restful.exception;


public class TransactionNotFoundException extends RuntimeException{
    private String errorMessage;

    public TransactionNotFoundException(){
        super();
    }

    public TransactionNotFoundException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public String getMessage() {
        return errorMessage;
    }
}
