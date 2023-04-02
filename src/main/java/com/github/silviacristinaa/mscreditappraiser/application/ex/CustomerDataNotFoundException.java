package com.github.silviacristinaa.mscreditappraiser.application.ex;

public class CustomerDataNotFoundException extends Exception{

    public CustomerDataNotFoundException() {
        super("customer data not found for the informed CPF.");
    }
}
