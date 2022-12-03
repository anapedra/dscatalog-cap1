package com.anasatanaslopessantantospedra.dscatalog.service.exceptions;

public class ResorceNotFoundException extends RuntimeException{
    private static final long serialVersionUID=1L;

    public ResorceNotFoundException(String msg){
        super(msg);
    }
}
