package com.algostyle.Cloud_Vendor_API.exception;

/**
 * Exception spécifique levée lorsqu'un fournisseur cloud n'est pas trouvé dans le système.
 */
public class CloudVendorNotFoundException extends RuntimeException{


    /**
     * Construit une nouvelle exception avec un message descriptif
     * @param message le message décrivant l'erreur
     */
    public CloudVendorNotFoundException(String message) {
        super(message);
    }


    /**
     * Construit une nouvelle exception avec un message descriptif et une clause sous-jacente
     *
     * @param message le message décrivant l'erreur
     * @param cause la cause sous-jacente de l'erreur (peut être {@code null})
     */
    public CloudVendorNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
