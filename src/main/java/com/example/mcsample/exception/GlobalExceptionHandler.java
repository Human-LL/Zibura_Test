package com.example.mcsample.exception;


import com.example.mcsample.dto.messages.AppMessage;
import com.example.mcsample.dto.response.ResponseHandler;
import com.example.mcsample.dto.response.ResponseObj;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.hibernate.HibernateException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@Log4j2
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private static final String BAD_REQUEST_ERROR = "Некорректный запрос";
    private static final String DB_ERROR = "Ошибка БД";
    private static final String VALIDATE_ERROR = "Ошибка валидации";
    private static final String NOT_FOUND_ERROR = "Данные не найдены";
    private static final String CONFLICT_ERROR = "Возник конфликт";

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<ResponseObj<Object>> handleNotFoundException(Exception exception) {
        log.error(NOT_FOUND_ERROR, exception.getMessage());
        return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND, List.of(AppMessage.error(NOT_FOUND_ERROR, exception.getMessage())));
    }

    @ExceptionHandler(ConflictException.class)
    //@ResponseStatus(value = HttpStatus.CONFLICT)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseObj<Object>> onConflictException(ConflictException exception) {
        log.error(CONFLICT_ERROR + ": " + exception.getMessage());
        return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND, List.of(AppMessage.error(NOT_FOUND_ERROR, exception.getMessage())));
    }

    @Override
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleHttpMessageNotReadable(@NonNull HttpMessageNotReadableException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error(BAD_REQUEST_ERROR + ": " + ex.getMessage());
        return ResponseHandler.generateErrorResponse(HttpStatus.NOT_FOUND, List.of(AppMessage.error(NOT_FOUND_ERROR, ex.getMessage())));
    }

    @Override
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<AppMessage> appMessageList = getAppMessageList(VALIDATE_ERROR, ex.getBindingResult());
        log.error(VALIDATE_ERROR, appMessageList.toString());
        return ResponseHandler.generateErrorResponse(HttpStatus.BAD_REQUEST, appMessageList);
    }

    @Override
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleBindException(@NonNull BindException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<AppMessage> appMessageList = getAppMessageList(VALIDATE_ERROR, ex.getBindingResult());
        log.error(VALIDATE_ERROR, appMessageList.toString());
        return ResponseHandler.generateErrorResponse(HttpStatus.BAD_REQUEST, appMessageList);
    }

    @ExceptionHandler(DataAccessException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ResponseObj<Object>> handleDataAccessException(DataAccessException exception) {
        log.error(DB_ERROR + ": " + exception.getMessage());
        exception.printStackTrace();
        return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND, List.of(AppMessage.error(NOT_FOUND_ERROR, exception.getMessage())));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ResponseObj<Object>> handleDataIntegrityViolationException(DataIntegrityViolationException exception) {
        String message = ObjectUtils.isEmpty(exception.getRootCause().getMessage()) ? exception.getMessage() : exception.getRootCause().getMessage();
        log.error(DB_ERROR + ": " + message);
        exception.printStackTrace();
        return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND, List.of(AppMessage.error(NOT_FOUND_ERROR, exception.getMessage())));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ResponseObj<Object>> handleEntityNotFoundException(DataIntegrityViolationException exception) {
        log.error(DB_ERROR + ": " + exception.getMessage());
        exception.printStackTrace();
        return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND, List.of(AppMessage.error(NOT_FOUND_ERROR, exception.getMessage())));
    }

    @ExceptionHandler(HibernateException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ResponseObj<Object>> handleHibernateException(HibernateException exception) {
        log.error(DB_ERROR + ": " + exception.getMessage());
        exception.printStackTrace();
        return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND, List.of(AppMessage.error(NOT_FOUND_ERROR, exception.getMessage())));
    }

    private List<AppMessage> getAppMessageList(String message, BindingResult bindingResult) {
        List<AppMessage> appMessageList = new ArrayList<>();
        bindingResult.getFieldErrors().stream().forEach(
                error -> appMessageList.add(AppMessage.error(message, error.getDefaultMessage(), error.getField())));
        return appMessageList;
    }
}
