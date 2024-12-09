package com.algostyle.Cloud_Vendor_API.exception;


/**
 * Exception levée lorsque l'on tente d'ajouter un fournisseur de cloud qui existe déjà dans le système
 */
public class CloudVendorAlreadyExists extends RuntimeException{

    /**
     * Constructeur de l'exception avec un message personnalisé
     * @param message Le message d'erreur décrivant la cause de l'exception
     */
    public CloudVendorAlreadyExists(String message){
        super(message); // Appel du constructeur parent (RuntimeException) avec le message d'erreur
    }
}
