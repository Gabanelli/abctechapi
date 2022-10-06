package br.com.fiap.abctechapi.handler.exception;

public class AssistanceNotFoundException extends RuntimeException {
    private final Long assistanceId;

    public AssistanceNotFoundException(String message, Long assistanceId){
        super(message);
        this.assistanceId =  assistanceId;
    }
}
