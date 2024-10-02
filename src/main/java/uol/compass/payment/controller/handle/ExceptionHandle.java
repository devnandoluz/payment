package uol.compass.payment.controller.handle;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uol.compass.payment.domain.exceptions.ChargeNotFoundException;
import uol.compass.payment.domain.exceptions.SellerNotFoundException;

@RestControllerAdvice
public class ExceptionHandle {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handle() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }

    @ExceptionHandler({ChargeNotFoundException.class, SellerNotFoundException.class})
    public ResponseEntity<ErrorResponse> handle(RuntimeException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(exception.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handle(IllegalArgumentException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(exception.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }
}
