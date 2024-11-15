package com.algostyle.Cloud_Vendor_API.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * la classe "ResponseEntity<>" est utilisée pour représenter une réponse HTTP
 * complète, incluant le code de statut, les en-têtes, et le corps de la réponse,
 * permettant ainsi un contrôle total sur la réponse envoyée par le serveur.
 */

/**
 * L'annotation "ExceptionHandler" est utilisée pour définir une méthode
 * qui gère une exception spécifique dans un contrôleur Spring, permettant
 * de personnaliser la réponse envoyée en cas d'erreur
 */

/**
 * L'annotation "ControllerAdvice" est utilisée pour centraliser le traitement
 * des exceptions et appliquer des conseils communs aux contrôleurs dans une
 * application Spring, facilitant ainsi la gestion globale des erreurs.
 */


@ControllerAdvice
public class CloudVendorExceptionHandlerController {

    @ExceptionHandler(value = {CloudVendorNotFoundException.class})
    public ResponseEntity<Object> handleCloudVendorNotFoundException(CloudVendorNotFoundException cloudVendorNotFoundException){
        CloudVendorException cloudVendorException=new CloudVendorException(
                cloudVendorNotFoundException.getMessage(),
                cloudVendorNotFoundException.getCause(),
                HttpStatus.NOT_FOUND
        );
        return new ResponseEntity<>(cloudVendorException,HttpStatus.NOT_FOUND);
    }
}
