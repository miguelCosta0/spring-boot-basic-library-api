package library.config;

import java.util.ArrayList;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import library.exception.BadRequestException;
import library.exception.InternalServerException;
import library.exception.NotFoundException;
import library.valueObject.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException ex) {
        var response = new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        return ResponseEntity
                .status(response.getStatus())
                .body(response);
    }

    @ExceptionHandler(NotFoundException.class)
    ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException ex) {
        var response = new ErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        return ResponseEntity
                .status(response.getStatus())
                .body(response);
    }

    @ExceptionHandler(InternalServerException.class)
    ResponseEntity<ErrorResponse> handleInternalServerException(InternalServerException ex) {
        var response = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        return ResponseEntity
                .status(response.getStatus())
                .body(response);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(
            HttpMessageNotReadableException ex) {
        System.err.println(ex.getMessage());
        var response = new ErrorResponse(HttpStatus.BAD_REQUEST, "JSON parse error");
        return ResponseEntity
                .status(response.getStatus())
                .body(response);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException() {
        var response = new ErrorResponse(HttpStatus.BAD_REQUEST, "BAD REQUEST");
        return ResponseEntity
                .status(response.getStatus())
                .body(response);
    }

    @ExceptionHandler(DataAccessException.class)
    ResponseEntity<ErrorResponse> handleDataAccessException() {
        var response = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL SERVER ERROR");
        return ResponseEntity
                .status(response.getStatus())
                .body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex) {
        var errors = new ArrayList<String>();
        ex.getFieldErrors().forEach((e) -> {
            errors.add(e.getField() + " " + e.getDefaultMessage());
        });
        var response = new ErrorResponse(HttpStatus.BAD_REQUEST, errors);

        return ResponseEntity
                .status(response.getStatus())
                .body(response);
    }

}
