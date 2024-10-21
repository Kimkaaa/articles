package com.my.articles.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AopExceptionController {
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiResponse> badRequestError
            (BadRequestException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.builder()
                        .message(e.getMessage())
                        .build());
    }
}
