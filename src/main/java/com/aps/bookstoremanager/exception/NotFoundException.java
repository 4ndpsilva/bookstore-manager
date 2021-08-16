package com.aps.bookstoremanager.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException(){
        super("Livro não encontrado");
    }
}