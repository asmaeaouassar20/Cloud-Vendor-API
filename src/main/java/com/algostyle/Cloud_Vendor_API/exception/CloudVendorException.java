package com.algostyle.Cloud_Vendor_API.exception;

import org.springframework.http.HttpStatus;

/**
 * La classe "Throwable" est la superclasse de toutes les erreurs (Error)
 * et exceptions (Exception), permettant la gestion des situations anormales
 * ou des erreurs dans les programmes.
 */

/**
 * La classe "HttpStatus" représente les codes de statut HTTP, permettant de
 * définir et de gérer les réponses appropriées du serveur aux requêtes HTTP.
 */


public class CloudVendorException {
    private final String message;
    private final Throwable throwable;
    private final HttpStatus httpStatus;

    public CloudVendorException(String message, Throwable throwable, HttpStatus httpStatus) {
        this.message = message;
        this.throwable = throwable;
        this.httpStatus = httpStatus;
    }


    public String getMessage() {
        return message;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
