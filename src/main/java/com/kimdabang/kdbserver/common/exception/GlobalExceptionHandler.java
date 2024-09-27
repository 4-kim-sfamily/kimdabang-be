package com.kimdabang.kdbserver.common.exception;

import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponseEntity> handleBusinessException(CustomException e){
        ErrorCode errorCode = e.getErrorCode();
        ErrorResponseEntity response = new ErrorResponseEntity(errorCode.getStatus(), errorCode.getCode(), errorCode.getDescription());

        return new ResponseEntity<>(response, HttpStatus.valueOf(errorCode.getStatus()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseEntity> handleValidationExceptions(MethodArgumentNotValidException e) {
        BindingResult result = e.getBindingResult();
        FieldError error = result.getFieldError();
        String errorMessage = error == null ? "알 수 없는 에러가 발생했습니다." : error.getField() + " 이(가) " + error.getDefaultMessage();
        ErrorResponseEntity response = new ErrorResponseEntity(HttpStatus.BAD_REQUEST.value(), errorMessage, "Validation Error");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponseEntity> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        ErrorCode errorCode = ErrorCode.DUPLICATED_LOGIN_ID;
        ErrorResponseEntity response = new ErrorResponseEntity(errorCode.getStatus(), e.getMessage(), "Duplicate Data");

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({BadCredentialsException.class, SignatureException.class})
    public ResponseEntity<ErrorResponseEntity> handleDataIntegrityViolationException(Exception e) {
        ErrorCode errorCode = ErrorCode.BAD_CREDENTIALS;
        ErrorResponseEntity response = new ErrorResponseEntity(errorCode.getStatus(), e.getMessage(), errorCode.getDescription());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseEntity> handleException(Exception e){
        log.info("handleException : ", e);

        ErrorCode errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
        ErrorResponseEntity response = new ErrorResponseEntity(errorCode.getStatus(), e.getMessage(), errorCode.getDescription());

        return new ResponseEntity<>(response, HttpStatus.valueOf(errorCode.getStatus()));
    }

}