package com.algostyle.Cloud_Vendor_API.exception;

import org.springframework.http.HttpStatus;


/**
 *  - Exception personnalisée pour gérer les erreurs spécifiques liées à notre application
 *  - Cette classe encapsule un message d'erreur, une exception sous-jacente et un code de statut HTTP, cela permet de standariser la gestion des erreurs dans l'application.
 */


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
    private final String message;  // Message décrivant l'erreur ou la situation anormale
    private final Throwable throwable;  // Exception sous-jacente permettant d'identifier la cause profonde de l'erreur
    private final HttpStatus httpStatus;    // Code de statut HTTP associé à cette erreur, utilisé pour indiquer la nature de la réponse


    /**
     * Constructeur principal de l'exception
     * @param message Le message décrivant l'erreur
     * @param throwable L'exception sous-jacente (peut être {@code null} si non applicable)
     * @param httpStatus Le code de statut HTTP associé à cette erreur
     */
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
