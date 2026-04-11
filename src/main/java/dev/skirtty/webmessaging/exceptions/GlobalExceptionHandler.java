package dev.skirtty.webmessaging.exceptions;

import dev.skirtty.webmessaging.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleNotFound (ResourceNotFoundException exc) {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(404, exc.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseDTO);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleNotValid (MethodArgumentNotValidException exc) {
        String errors = exc.getBindingResult().getFieldErrors().toString();
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(404, errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDTO);
    }

    @ExceptionHandler(ResourceExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handleAlreadyExists (ResourceExistsException exc) {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(404, exc.getMessage());
        return ResponseEntity.status(HttpStatus.IM_USED).body(errorResponseDTO);
    }

    @ExceptionHandler(ResourceIsNullException.class)
    public ResponseEntity<ErrorResponseDTO> handleIsNull (ResourceIsNullException exc) {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(404, exc.getMessage());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(errorResponseDTO);
    }
}
