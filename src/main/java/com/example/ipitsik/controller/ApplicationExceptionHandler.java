package com.example.ipitsik.controller;

import com.example.ipitsik.exception.ExchangeException;
import com.example.ipitsik.exception.PromotionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URISyntaxException;

@Slf4j
@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(PromotionException.class)
    public ResponseEntity<Object> handlePromotionException(PromotionException exception) {
        log.error(exception.toString());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ExceptionHandler(ExchangeException.class)
    public ResponseEntity<Object> handleExchangeException(ExchangeException exception) {
        log.error(exception.toString());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @ExceptionHandler(URISyntaxException.class)
    public ResponseEntity<Object> handleUriException(URISyntaxException exception) {
        log.error(exception.toString());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
