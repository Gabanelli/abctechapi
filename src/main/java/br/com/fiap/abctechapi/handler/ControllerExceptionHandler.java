package br.com.fiap.abctechapi.handler;

import br.com.fiap.abctechapi.handler.exception.AssistanceNotFoundException;
import br.com.fiap.abctechapi.handler.exception.MaxAssistException;
import br.com.fiap.abctechapi.handler.exception.MinimumAssistRequiredException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(MinimumAssistRequiredException.class)
    public ResponseEntity<ErrorMessageResponse> errorMinAssistRequired(MinimumAssistRequiredException exception){
        return getErrorMessageResponseResponseEntity(exception.getMessage(), exception.getDescription(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MaxAssistException.class)
    public ResponseEntity<ErrorMessageResponse> errorMaxAssistException(MaxAssistException exception){
        return getErrorMessageResponseResponseEntity(exception.getMessage(), exception.getDescription(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AssistanceNotFoundException.class)
    public ResponseEntity<ErrorMessageResponse> errorAssistanceNotFoundException(AssistanceNotFoundException exception){
        return getErrorMessageResponseResponseEntity(exception.getMessage(), String.format("Assistance Id: %d", exception.getAssistanceId()), HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<ErrorMessageResponse> getErrorMessageResponseResponseEntity(String message, String description, HttpStatus statusCode ) {
        ErrorMessageResponse error = new ErrorMessageResponse();
        error.setMessage(message);
        error.setDescription(description);
        error.setTimestamp(new Date());
        error.setStatusCode(statusCode.value());
        return new ResponseEntity<ErrorMessageResponse>(error, statusCode);
    }
}
