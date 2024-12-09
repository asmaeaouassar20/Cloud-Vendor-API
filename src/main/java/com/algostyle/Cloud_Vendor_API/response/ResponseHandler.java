package com.algostyle.Cloud_Vendor_API.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;


/**
 * Fournit des méthodes utilitaires pour construire des réponses HTTP formatées
 *
 * <p>
 *     Cette classe centralise la gestion des réponses HTTP dans l'application,
 *     facilitant l'envoi de réponses structurées avec un message, un code de statut HTTP et des  données sous from d'objet
 * </p>
 */
public class ResponseHandler {


    /**
     * Méthode utilitaire pour construire une réponse HTTP standardisée
     *
     * @param message Le message descriptif associé la réponse
     * @param httpStatus Le code de statut HTTP à associer à la réponse
     * @param responseObject L'objet contenant les données de la réponse
     * @return Une instance de {@link ResponseEntity} contenant le message, le statut HTTP et les données sous forme de map
     */
    public static ResponseEntity<Object> responseBuilder(
            String message, HttpStatus httpStatus,Object responseObject
    ) {
        Map<String,Object> response=new HashMap<>();
        response.put("message",message);
        response.put("httpstatus",httpStatus);
        response.put("data",responseObject);
        return new ResponseEntity<>(response,httpStatus);
    }
}
