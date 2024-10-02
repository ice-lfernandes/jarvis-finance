package br.com.jarvisfinance.application.api.advice;

import br.com.jarvisfinance.application.api.data.error.ErrorMessageResponse;
import br.com.jarvisfinance.application.api.data.error.ErrorMessageVO;
import br.com.jarvisfinance.application.api.data.error.ErrorResponse;
import br.com.jarvisfinance.domain.exception.DomainRuleException;
import br.com.jarvisfinance.infraestructure.data.exception.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleNotFoundException(HttpServletRequest request, EntityNotFoundException exception) {
        log.warn("stage=handle-not-found, uri-request={}, message={}", request.getRequestURI(), exception.getMessage());
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorMessageResponse> handleBadRequestException(HttpServletRequest request, MethodArgumentNotValidException exception) {
        List<ErrorMessageVO> listErrorsMessage = exception.getBindingResult().getFieldErrors().stream()
              .map(fieldError -> new ErrorMessageVO(
                    fieldError.getDefaultMessage() != null ? fieldError.getDefaultMessage() : "generic error",
                    fieldError.getField()
              )).toList();

        log.error("stage=handle-bad-request-exception, uri-request={}, errors={}", request.getRequestURI(), listErrorsMessage, exception);

        return ResponseEntity.badRequest().body(new ErrorMessageResponse(listErrorsMessage));
    }

    @ExceptionHandler(DomainRuleException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<ErrorResponse> handleDomainRuleException(HttpServletRequest request, DomainRuleException exception) {
        log.error("stage=handle-domain-rule-exception, uri-request={}, message={}", request.getRequestURI(), exception.getMessage(), exception);
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
              .body(new ErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY.toString(), exception.getMessage()));
    }

    @ExceptionHandler(SQLException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<ErrorResponse> handleSqlException(HttpServletRequest request, SQLException exception) {
        log.error("stage=handle-sql-exception, uri-request={}, message={}", request.getRequestURI(), exception.getMessage(), exception);
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
              .body(new ErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY.toString(), exception.getMessage()));
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleRuntimeException(HttpServletRequest request, RuntimeException exception) {
        log.error("stage=handle-runtime-exception, uri-request={}, message={}", request.getRequestURI(), exception.getMessage(), exception);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
              .body(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.toString(), exception.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleGenericException(HttpServletRequest request, Exception exception) {
        log.error("stage=handle-generic-exception, uri-request={}, message={}", request.getRequestURI(), exception.getMessage(), exception);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
              .body(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.toString(), exception.getMessage()));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(HttpServletRequest request,
                                                                            DataIntegrityViolationException exception) {
        var constraintException = (ConstraintViolationException) exception.getCause();
        log.error("stage=handle-constraint-violation-exception, uri-request={}, constraint-error={}", request.getRequestURI(),
              constraintException.getConstraintName(), exception);
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
              .body(new ErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY.toString(),
                    String.format("Constraint violation for %s", constraintException.getConstraintName())));
    }
}
