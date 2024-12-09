package com.algostyle.Cloud_Vendor_API.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * - Gestionnaire global des exceptions pour l'API CloudVendor
 * - Cette classe capture et traite les exceptions spécifiques pour fournir des réponses HTTP appropriées
 */

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




    /**
     * Gère les exceptions de type {@link CloudVendorNotFoundException}
     *
     * @param cloudVendorNotFoundException L'exception levée lorsqu'un fournisseur Cloud n'est pas trouvé
     * @return Une réponse HTTP contenant les détails de l'erreur, avec le statut {@code 404 Not Found}
     */
    @ExceptionHandler(value = {CloudVendorNotFoundException.class})
    public ResponseEntity<Object> handleCloudVendorNotFoundException(CloudVendorNotFoundException cloudVendorNotFoundException){
        CloudVendorException cloudVendorException=new CloudVendorException(
                cloudVendorNotFoundException.getMessage(),  // Message décrivant l'erreur
                cloudVendorNotFoundException.getCause(),    // Cause sous-jacente (si dispnible)
                HttpStatus.NOT_FOUND    // Statut HTTP 404
        );
        return new ResponseEntity<>(cloudVendorException,HttpStatus.NOT_FOUND);
    }





    /**
     * Gère les exceptions de type {@link CloudVendorAlreadyExists}
     *
     * @param cloudVendorAlreadyExists L'exception levée lorsqu'un fournisseur cloud existe déjà
     * @return Une réponse HTTP contenant les détails de l'erreur, avec le statut {@code 409 Conflict}
     */
    @ExceptionHandler(value={CloudVendorAlreadyExists.class})
    public ResponseEntity<Object> handleCloudVendorAlreadyExistsException(CloudVendorAlreadyExists cloudVendorAlreadyExists){
        CloudVendorException cloudVendorException=new CloudVendorException(
                cloudVendorAlreadyExists.getMessage(), // Message décrivant l'erreur
                cloudVendorAlreadyExists.getCause(), // Cause sous-jacente (si disponible)
                HttpStatus.CONFLICT  // Statut HTTP 409
        );
        return new ResponseEntity<>(cloudVendorException,HttpStatus.CONFLICT);
    }
}
