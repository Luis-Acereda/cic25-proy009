package es.cic.curso25.proy009.controller;

public class CreationException extends RuntimeException{

    public CreationException(){
        super("Estas intentando pasar el id del arbol al intentar crearlo");
    }

    public CreationException(String message){
        super(message);
    }

    public CreationException(String message, Throwable throwable){
        super(message, throwable);
    }
}
