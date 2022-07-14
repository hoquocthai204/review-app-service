package net.sparkminds.review.controller.advance;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntotyNotFoundException(EntityNotFoundException ex){
        return ResponseEntity.notFound().build();
    }
    
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        
        Map<String, Object> errorBody = new HashMap<>();
        Map<String, List<String>> errors = new HashMap<>();
        
        ex.getBindingResult().getFieldErrors().forEach(fieldError->{
            String field = fieldError.getField();
            String message = fieldError.getDefaultMessage();
            
            if(errors.containsKey(field)) {
                errors.get(field).add(message);
            } else {
                errors.put(field, List.of(message));
            }
        });
        
        errorBody.put("errors", errors);
        
        return ResponseEntity.badRequest().body(errorBody);
    }
}