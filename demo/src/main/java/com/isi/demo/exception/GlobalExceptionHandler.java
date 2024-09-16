package com.isi.demo.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import static org.springframework.http.HttpStatus.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Gestion de l'exception lorsque l'entité n'est pas trouvée
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleEntityNotFoundException(EntityNotFoundException exp) {
        return ResponseEntity
                .status(BusinessErrorCodes.ENTITY_NOT_FOUND.getHttpStatus())
                .body(
                        ExceptionResponse.builder()
                                .businessErrorCode(BusinessErrorCodes.ENTITY_NOT_FOUND.getCode())
                                .businessErrorDescription(BusinessErrorCodes.ENTITY_NOT_FOUND.getDescription())
                                .error(exp.getMessage())
                                .build()
                );
    }

    // Gestion des erreurs de validation des arguments (ex: champs non fournis ou incorrects)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleValidationExceptions(MethodArgumentNotValidException exp) {
        Set<String> errors = new HashSet<>();
        exp.getBindingResult().getAllErrors()
                .forEach(error -> {
                    //var fieldName = ((FieldError) error).getField();
                    var errorMessage = error.getDefaultMessage();
                    errors.add(errorMessage);
                });

        return ResponseEntity
                .status(BAD_REQUEST)
                .body(
                        ExceptionResponse.builder()
                                .validationErrors(errors)
                                .build()
                );
    }

    // Gestion des cas de conflits de données (ex : email en double)
    @ExceptionHandler(OperationNotPermittedException.class)
    public ResponseEntity<ExceptionResponse> handleStudentConflictException(OperationNotPermittedException exp) {
        return ResponseEntity
                .status(BusinessErrorCodes.DUPLICATE_EMAIL.getHttpStatus())
                .body(
                        ExceptionResponse.builder()
                                .businessErrorCode(BusinessErrorCodes.DUPLICATE_EMAIL.getCode())
                                .businessErrorDescription(BusinessErrorCodes.DUPLICATE_EMAIL.getDescription())
                                .error(exp.getMessage())
                                .build()
                );
    }

    // Gestion des cas de conflits de données (ex : le nom departement en double)
    @ExceptionHandler(OperationDepartementNotPermittedException.class)
    public ResponseEntity<ExceptionResponse> handleStudentConflictException(OperationDepartementNotPermittedException exp) {
        return ResponseEntity
                .status(BusinessErrorCodes.DUPLICATE_DEPARTMENT_NAME.getHttpStatus())
                .body(
                        ExceptionResponse.builder()
                                .businessErrorCode(BusinessErrorCodes.DUPLICATE_DEPARTMENT_NAME.getCode())
                                .businessErrorDescription(BusinessErrorCodes.DUPLICATE_DEPARTMENT_NAME.getDescription())
                                .error(exp.getMessage())
                                .build()
                );
    }

    //Gestion de cas de suppression de Departement
    @ExceptionHandler(DepartementDeletionException.class)
    public ResponseEntity<ExceptionResponse> handleStudentConflictException(DepartementDeletionException exp) {
        return ResponseEntity
                .status(BusinessErrorCodes.DEPARTMENT_DELETION_ERROR.getHttpStatus())
                .body(
                        ExceptionResponse.builder()
                                .businessErrorCode(BusinessErrorCodes.DEPARTMENT_DELETION_ERROR.getCode())
                                .businessErrorDescription(BusinessErrorCodes.DEPARTMENT_DELETION_ERROR.getDescription())
                                .error(exp.getMessage())
                                .build()
                );
    }


    // Gestion des autres exceptions générales
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleGeneralException(Exception exp) {
        exp.printStackTrace();
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        ExceptionResponse.builder()
                                .businessErrorDescription("Erreur interne, veuillez contacter l'administrateur")
                                .error(exp.getMessage())
                                .build()
                );
    }
}