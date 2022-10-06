package br.com.fiap.abctechapi.handler.exception;

public class MinimumAssistRequiredException extends RuntimeException {
    private String description;

    public MinimumAssistRequiredException(String message, String description){
        super(message);
        this.description =  description;
    }
}
