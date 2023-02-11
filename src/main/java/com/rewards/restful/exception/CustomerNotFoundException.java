package com.rewards.restful.exception;

public class CustomerNotFoundException extends RuntimeException{
    private String errorMessage;

    public CustomerNotFoundException(){
        super();
    }

    public CustomerNotFoundException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public String getMessage() {
        return errorMessage;
    }
}
