package com.isi.demo.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum BusinessErrorCodes {

    ENTITY_NOT_FOUND(404, HttpStatus.NOT_FOUND, "Entité non trouvée"),
    DUPLICATE_EMAIL(409, HttpStatus.CONFLICT, "Email déjà utilisé"),
    INVALID_DATA(400, HttpStatus.BAD_REQUEST, "Données invalides"),
    DUPLICATE_DEPARTMENT_NAME(409, HttpStatus.CONFLICT, "Le nom du département existe déjà"),
    DEPARTMENT_DELETION_ERROR(400, HttpStatus.BAD_REQUEST, "Impossible de supprimer le département car il est associé à des étudiants");

    private final int code;
    private final String description;
    private final HttpStatus httpStatus;
    BusinessErrorCodes(int code, HttpStatus status, String description) {
        this.code = code;
        this.description = description;
        this.httpStatus = status;
    }
}

